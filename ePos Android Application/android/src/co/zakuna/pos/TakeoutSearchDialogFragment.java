package co.zakuna.pos;

import org.androidannotations.annotations.*;

import android.support.v4.app.DialogFragment;
import android.graphics.drawable.ColorDrawable;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.text.TextWatcher;
import android.text.Editable;

import java.util.List;
import java.util.ArrayList;

@EFragment(R.layout.dialog_fragment_takeout_search)
public class TakeoutSearchDialogFragment extends DialogFragment implements TextWatcher {	
	@ViewById ListView list;
	@ViewById EditText search;

	private FilterAdapter adapter;

	@Override
	public Dialog onCreateDialog(Bundle state) {
		Dialog dialog = super.onCreateDialog(state);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.getWindow().getAttributes().windowAnimations = R.style.dialog_animation_search;
		dialog.getWindow().getAttributes().gravity = Gravity.RIGHT;
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		dialog.setCanceledOnTouchOutside(false);
		return dialog;
	}

	@AfterViews
	protected void initialized() {
		List<Object> listObject = new ArrayList<Object>();
		for(int i=0; i<50; i++) {
			listObject.add("Item "+(i+1));
		}
		adapter = new FilterAdapter(listObject);
		list.setAdapter(adapter);
		search.addTextChangedListener(this);
	}

	@Override
	public void afterTextChanged(Editable e) {
		adapter.filter(search.getText().toString().toLowerCase());
	}

	@Override public void beforeTextChanged(CharSequence c, int a1, int a2, int a3) {}
	@Override public void onTextChanged(CharSequence c, int a1, int a2, int a3) {}

	@ItemClick
	protected void list(int position) {
		dismiss();
	}

	@Click
	protected void back() {
		dismiss();
	}

	private class FilterAdapter extends BaseAdapter {
		private final List<Object> list;
		private final List<Object> listFilter;

		private FilterAdapter(List<Object> list) {
			this.list = list;
			this.listFilter = new ArrayList<Object>();
			this.listFilter.addAll(list);
		}

		@Override public int getCount() { return list.size(); }
		@Override public long getItemId(int pos) { return (long) pos; }
		@Override public Object getItem(int pos) { return list.get(pos); }

		public ViewFilter getView(int pos, View recycle, ViewGroup parent) {
			ViewFilter view = recycle != null ? (ViewFilter) recycle :
				ViewFilter_.build(parent.getContext());
			view.setData(list.get(pos));
			return view;
		}

		public void filter(String text) {
			text = text.toLowerCase();
			list.clear();
			if(text.length()== 0) {
				list.addAll(listFilter);
			} else {
				for(Object data: listFilter) {
					String strData = (String) data;
					if(strData.toLowerCase().contains(text)) {
						list.add(strData);
					}
				}
			}
			notifyDataSetChanged();
		}
	}

}

@EViewGroup(R.layout.row_search_item)
class ViewFilter extends LinearLayout {
	@ViewById TextView title;

	public ViewFilter(Context c) {
		super(c);
	}

	public void setData(Object entry) {
		if(entry != null) {
			title.setText((String) entry);
		}
	}
}
