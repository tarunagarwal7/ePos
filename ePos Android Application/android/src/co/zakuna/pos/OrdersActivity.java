package co.zakuna.pos;

import co.zakuna.pos.util.Utils;

import org.androidannotations.annotations.*;
import org.androidannotations.annotations.res.*;

import android.support.v4.app.FragmentManager;
import android.widget.*;
import android.view.View;
import android.view.ViewGroup;
import android.view.MenuItem;
import android.content.Context;

import java.util.Date;
import java.text.SimpleDateFormat;

@EActivity(R.layout.activity_orders)
@OptionsMenu(R.menu.toolbar_orders)
public class OrdersActivity extends BaseActivity {
	@ViewById GridView orders;
	@StringRes(R.string.title_orders) String titleOrders;

	@OptionsMenuItem(R.id.cancel_search) MenuItem cancelSearch;
	@OptionsMenuItem MenuItem search;

	private OrderAdapter adapter;

	@Override
	protected void initialized() {
		super.initialized();
		actionBar.setTitle(titleOrders);
	}

	@AfterViews
	protected void createViewOrders() {
		adapter = new OrderAdapter(new String[]{""});
		orders.setAdapter(adapter);
	}

	@OptionsItem
	protected void search() {
		FragmentManager fm = getSupportFragmentManager();
		SearchDialogFragment fragment = SearchDialogFragment_.builder().build();
		fragment.show(fm, null);
		fragment.callback(new OnCallback() {
			@Override
			public void setOnCallback(Object object) {
				orders.setAdapter(null);
				search.setVisible(false);
				cancelSearch.setVisible(true);
				cancelSearch.setTitle(String.format(" %s", (String) object));
			}
		});
	}

	@OptionsItem(R.id.cancel_search)
	protected void cancelSearch() {
		createViewOrders();
		cancelSearch.setVisible(false);
		search.setVisible(true);
	}

	@ItemClick
	protected void orders(int position) {
		TakeoutActivity_.intent(this).title("1").start();
	}

	private class OrderAdapter extends BaseAdapter {
		private final Object[] object;
		private OrderAdapter(Object[] object) { this.object = object; }

		@Override public int getCount() { return object.length; }
		@Override public long getItemId(int pos) { return (long) pos; }
		@Override public Object getItem(int pos) { return object[pos]; }

		public ViewOrders getView(int pos, View recycle, ViewGroup parent) {
			ViewOrders viewOrders = recycle != null ? (ViewOrders) recycle :
				ViewOrders_.build(parent.getContext());
			viewOrders.setData(null);
			return viewOrders;
		}
	}
}


@EViewGroup(R.layout.row_orders)
class ViewOrders extends LinearLayout {
	@ViewById TextView title;
	@ViewById(R.id.text_table_number) TextView textTableNumber;
	@ViewById(R.id.text_amount) TextView textAmount;
	@ViewById(R.id.text_time) TextView textTime;

	public ViewOrders(Context c) {
		super(c);
	}

	public void setData(Object object) {
		title.setText(String.format("#%s-%s", "A", "0003"));
		textTableNumber.setText(String.format("%d", 1));
		textAmount.setText(Utils.currencyToString(20000000.0));
		textTime.setText(new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss")
				.format(new Date(System.currentTimeMillis())));
	}
}
