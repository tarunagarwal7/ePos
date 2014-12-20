package co.zakuna.pos;

import org.androidannotations.annotations.*;

import android.support.v4.app.Fragment;
import android.widget.*;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.ArrayList;

@EFragment(R.layout.view_grid)
public class TakeoutProductsFragment extends Fragment {
	@ViewById GridView rows;
	@FragmentArg int id;

	private ProductAdapter adapter;
	private OnCallback callback;

	@AfterViews
	protected void initialized() {
		List<String> list = new ArrayList<String>();
		for(int i=0; i<10; i++) list.add(String.format("Item %d%d", (id+1), i));
		adapter = new ProductAdapter(list);
		rows.setAdapter(adapter);
	}

	public void callback(OnCallback callback) {
		this.callback = callback;
	}

	@ItemClick
	protected void rows(final int position) {
		callback.setOnCallback(adapter.getItem(position));
	}

	private class ProductAdapter extends BaseAdapter {
		private final List<String> list;
		public ProductAdapter(List<String> list) { this.list = list; }

		@Override public int getCount() { return list.size(); }
		@Override public long getItemId(int pos) { return (long) pos; }
		@Override public String getItem(int pos) { return list.get(pos); }

		public ViewRowProduct getView(int pos, View recycle, ViewGroup parent) {
			ViewRowProduct viewRow = recycle != null ? (ViewRowProduct) recycle :
				ViewRowProduct_.build(parent.getContext());
			viewRow.setData(list.get(pos));
			return viewRow;
		}
	}
}

@EViewGroup(R.layout.row_products)
class ViewRowProduct extends LinearLayout {
	@ViewById TextView title;
	@ViewById TextView price;

	public ViewRowProduct(Context c) {
		super(c);
	}

	public void setData(Object entry) {
		title.setText((String) entry);
		price.setText("Rp. 15.000,-");
	}
}
