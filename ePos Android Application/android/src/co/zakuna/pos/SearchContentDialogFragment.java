package co.zakuna.pos;

import org.androidannotations.annotations.*;

import android.support.v4.app.Fragment;
import android.widget.*;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.ArrayList;

@EFragment(R.layout.view_grid)
public class SearchContentDialogFragment extends Fragment {
	@ViewById GridView rows;
	@FragmentArg int id;

	private SearchAdapter adapter;
	private OnCallback callback;

	@AfterViews
	protected void initialized() {
		List<String> list = new ArrayList<String>();
		for(int i=0; i<10; i++) list.add(String.format("Item %d%d", (id+1), i));
		adapter = new SearchAdapter(list);
		rows.setAdapter(adapter);
	}

	public void callback(OnCallback callback) {
		this.callback = callback;
	}

	@ItemClick
	protected void rows(final int position) {
		callback.setOnCallback(adapter.getItem(position));
	}

	private class SearchAdapter extends BaseAdapter {
		private final List<String> list;
		public SearchAdapter(List<String> list) { this.list = list; }

		@Override public int getCount() { return list.size(); }
		@Override public long getItemId(int pos) { return (long) pos; }
		@Override public String getItem(int pos) { return list.get(pos); }

		public ViewRow getView(int pos, View recycle, ViewGroup parent) {
			ViewRow viewRow = recycle != null ? (ViewRow) recycle :
				ViewRow_.build(parent.getContext());
			viewRow.setData(list.get(pos));
			return viewRow;
		}
	}
}
