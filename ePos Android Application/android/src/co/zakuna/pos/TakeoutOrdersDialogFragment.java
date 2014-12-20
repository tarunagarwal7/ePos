package co.zakuna.pos;

import org.androidannotations.annotations.*;

import android.widget.*;
import android.view.View;
import android.view.ViewGroup;

@EFragment(R.layout.view_list)
public class TakeoutOrdersDialogFragment extends BaseDialogFragment {
	@ViewById ListView list;
	@FragmentArg String[] popOrders;

	private OnCallback callback;

	@AfterViews
	protected void initialized() {
		list.setAdapter(new DialogListAdapter(popOrders));	
	}

	public void callback(OnCallback callback) {
		this.callback = callback;
	}

	@ItemClick
	protected void list(int pos) {
		callback.setOnCallback(popOrders[pos]);
		dismiss();
	}

	private class DialogListAdapter extends BaseAdapter {
		private final Object[] object;
		private DialogListAdapter(Object[] object) {
			this.object = object;
		}

		@Override public int getCount() { return popOrders.length; }
		@Override public long getItemId(int pos) { return  (long) pos; }
		@Override public Object getItem(int pos) { return popOrders[pos]; }

		public ViewRow getView(int pos, View recycle, ViewGroup parent) {
			ViewRow view = recycle != null ? (ViewRow) recycle :
				ViewRow_.build(parent.getContext());
			view.setData(popOrders[pos]);
			return view;
		}
	}
}
