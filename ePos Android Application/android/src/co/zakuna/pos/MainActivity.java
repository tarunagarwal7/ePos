package co.zakuna.pos;

import org.androidannotations.annotations.*;
import org.androidannotations.annotations.res.*;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.content.Context;
import android.widget.*;

import android.view.View;
import android.view.ViewGroup;
import android.view.MenuItem;

import co.zakuna.pos.session.SessionLoginImpl;

@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.toolbar_main)
public class MainActivity extends BaseActivity {
	@ViewById GridView rows;
	@Bean SessionLoginImpl session;

	private OrdersAdapter adapter;
	private Intent intent;

	@Override
	protected void initialized() {
		super.initialized();
		actionBar.setDisplayHomeAsUpEnabled(false);
		actionBar.setIcon(R.drawable.ic_icon);
		actionBar.setTitle(null);
	}

	@Override
	public void onResume() {
		super.onResume();
		if(!session.isSession()) {
			LoginActivity_.intent(this)
				.flags(Intent.FLAG_ACTIVITY_CLEAR_TOP).start();
			finish();
		}
	}

	@AfterViews
	protected void createViewOrder() {
		String[] entry = new String[]{"1","2","3","4","5","6","7","8","9"};
		adapter = new OrdersAdapter(entry);
		rows.setAdapter(adapter);
	}

	@ItemClick
	protected void rows(final int position) {
		FragmentManager fm = getSupportFragmentManager();
		GuestDialogFragment fragment = GuestDialogFragment_.builder().build();
		fragment.callback(new OnCallback() {
			@Override
			public void setOnCallback(Object object) {
				TakeoutActivity_.intent(MainActivity.this)
					.title((String) adapter.getItem(position)).start();
			}
		});
		fragment.show(fm, null);
	}

	@OptionsItem
	protected void orders() {
		intent = OrdersActivity_.intent(this).get();
		startActivity(intent);
	}

	@OptionsItem
	protected void takeout() {
		intent = TakeoutActivity_.intent(this).get();
		startActivity(intent);
	}

	@OptionsItem
	protected void drawer() {
	}

	@OptionsItem(R.id.action_settings)
	protected void settings(MenuItem item) {
		SettingsActivity_.intent(this).title(item.getTitle().toString()).start();
	}

	@OptionsItem(R.id.action_logout)
	protected void logout() {
		session.clear();
		MainActivity_.intent(this).flags(Intent.FLAG_ACTIVITY_CLEAR_TOP).start();
		finish();
	}

	private class OrdersAdapter extends BaseAdapter {
		private final Object[] object;
		public OrdersAdapter(Object[] object) { this.object = object; }

		@Override public int getCount() { return object.length; }
		@Override public long getItemId(int pos) { return (long) pos; }
		@Override public Object getItem(int pos) { return object[pos]; }

		public ViewRow getView(int pos, View recycle, ViewGroup parent) {
			ViewRow viewRow = recycle != null ? (ViewRow) recycle : 
				ViewRow_.build(parent.getContext());
			viewRow.setData(object[pos]);
			return viewRow;
		}
	}
}

@EViewGroup(R.layout.rows)
class ViewRow extends LinearLayout {
	@ViewById TextView title;

	public ViewRow(Context c) {
		super(c);
	}

	public void setData(Object entry) {
		title.setText((String) entry);	
	}
}
