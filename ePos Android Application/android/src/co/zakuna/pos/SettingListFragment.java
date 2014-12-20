package co.zakuna.pos;

import org.androidannotations.annotations.*;
import org.androidannotations.annotations.res.*;

import android.support.v4.app.ListFragment;
import android.widget.*;
import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import java.util.List;
import java.util.ArrayList;
import java.util.TreeSet;

@EFragment
public class SettingListFragment extends ListFragment {
	@StringArrayRes(R.array.settings_header) String[] settingHeader;
	@StringArrayRes(R.array.settings_item_personal) String[] itemPersonal;
	@StringArrayRes(R.array.settings_item_connection) String[] itemConn;
	@StringArrayRes(R.array.settings_item_info) String[] itemInfo;

	private final static String STATE_ACT_POS = "SettingListFragment::Position";
	private int activedPosition = ListView.INVALID_POSITION;
	private OnCallback callback;
	private SettingAdapter adapter;

	@Override
	public void onDetach() {
		super.onDetach();
	}

	@Override
	public void onAttach(Activity a) {
		super.onAttach(a);
		if(!(a instanceof OnCallback)) {
			throw new IllegalStateException("Error");
		}
	}

	@Override
	public void onCreate(Bundle state) {
		super.onCreate(state);
		adapter = new SettingAdapter(getActivity());
		for(int i=0; i<settingHeader.length; i++) {
			adapter.addHeader(settingHeader[i]);
			if(i==0) setItemInHeader(itemPersonal);
			if(i==1) setItemInHeader(itemConn);
			if(i==2) setItemInHeader(itemInfo);
		}
		setListAdapter(adapter);
	}

	private void setItemInHeader(String[] items) {
		for(int i=0; i<items.length; i++) {
			adapter.addItem(items[i]);
		}
	}

	@Override
	public void onViewCreated(View v, Bundle state) {
		super.onViewCreated(v, state);
		if(state != null && state.containsKey(STATE_ACT_POS)) {
			setActivatedPosition(state.getInt(STATE_ACT_POS));
		}
	}

	@Override
	public void onSaveInstanceState(Bundle state) {
		super.onSaveInstanceState(state);
		if(activedPosition != ListView.INVALID_POSITION) {
			state.putInt(STATE_ACT_POS, activedPosition);
		}
	}

	public void callback(OnCallback callback) {
		this.callback = callback;
	}

	@Override
	public void onListItemClick(ListView list, View v, int i, long id) {
		super.onListItemClick(list, v, i, id);
		TextView text = (TextView) v.findViewById(R.id.text);
		callback.setOnCallback(text.getText().toString());
	}

	public void setActivatedOnItemClick(boolean actived) {
		getListView().setChoiceMode(actived 
				? ListView.CHOICE_MODE_SINGLE 
				: ListView.CHOICE_MODE_NONE);
	}

	private void setActivatedPosition(int position) {
		if(position == ListView.INVALID_POSITION) {
			getListView().setItemChecked(activedPosition, false);
		} else {
			getListView().setItemChecked(position, true);
		}
		activedPosition = position;
	}

	private static class ViewHolder {
		public TextView textView;
	}

	private class SettingAdapter extends BaseAdapter {
		private final static int TYPE_ITEM = 0;
		private final static int TYPE_HEADER = 1;
		private final Context context;
		
		private List<String> item = new ArrayList<String>();
		private TreeSet<Integer> header = new TreeSet<Integer>();

		public SettingAdapter(Context c) {
			this.context = c;
		}

		public void addItem(final String dataItem) {
			item.add(dataItem);
			notifyDataSetChanged();
		}

		public void addHeader(final String dataHeader) {
			item.add(dataHeader);
			header.add(item.size() -1);
			notifyDataSetChanged();
		}

		@Override
		public int getItemViewType(int pos) {
			return header.contains(pos) ? TYPE_HEADER : TYPE_ITEM;
		}

		@Override public int getViewTypeCount() { return 8; }
		@Override public int getCount() { return item.size(); }
		@Override public String getItem(int pos) { return item.get(pos); }
		@Override public long getItemId(int pos) { return pos; }

		@Override
		public boolean isEnabled(int pos) {
			return header.contains(pos)? false : true;
		}

		@Override
		public View getView(int pos, View v, ViewGroup parent) {
			LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			ViewHolder holder = null;
			int rowType = getItemViewType(pos);

			if(holder == null) {
				holder = new ViewHolder();
				switch(rowType) {
					case TYPE_ITEM:
						v = mInflater.inflate(R.layout.row_setting_item_list, null);
						holder.textView = (TextView) v.findViewById(R.id.text);
						break;
					case TYPE_HEADER:
						v = mInflater.inflate(R.layout.row_setting_header_list, null);
						holder.textView = (TextView) v.findViewById(R.id.text);
						break;
				}
				v.setTag(holder);
			} else {
				holder = (ViewHolder) v.getTag();
			}
			holder.textView.setText(item.get(pos));
			return v;

		}

	}
}
