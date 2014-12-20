package co.zakuna.pos;

import org.androidannotations.annotations.*;
import org.androidannotations.annotations.res.*;

import android.widget.*;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.ArrayList;

@EFragment(R.layout.dialog_fragment_guest)
public class GuestDialogFragment extends BaseDialogFragment {
	@ViewById GridView guests;

	private final int SIZE_GUEST = 12;

	private GuestAdapter adapter;
	private OnCallback callback;

	@AfterViews
	protected void initialized() {
		List<Integer> list = new ArrayList<Integer>();
		for(int i=1; i<=SIZE_GUEST; i++) list.add(i);
		adapter = new GuestAdapter(list);	
		guests.setAdapter(adapter);
	}

	public void callback(OnCallback callback) {
		this.callback = callback;
	}

	@ItemClick
	protected void guests(int position) {
		dismiss();
		callback.setOnCallback(adapter.getItem(position));
	}

	private class GuestAdapter extends BaseAdapter {
		private final List<Integer> list;
		public GuestAdapter(List<Integer> list) { this.list = list; }

		@Override public int getCount() { return list.size(); }
		@Override public long getItemId(int pos) { return (long) pos; }
		@Override public Integer getItem(int pos) { return list.get(pos); }

		public ViewRow getView(int pos, View recycle, ViewGroup parent) {
			ViewRow viewRow = recycle != null ? (ViewRow) recycle :
				ViewRow_.build(parent.getContext());
			viewRow.setData(Integer.toString(list.get(pos)));
			return viewRow;
		}
	}
}
