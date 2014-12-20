package co.zakuna.pos;

import org.androidannotations.annotations.*;
import org.androidannotations.annotations.res.*;
import android.support.v4.app.Fragment;
import android.widget.*;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import co.zakuna.pos.util.Utils;

@EFragment(R.layout.fragment_takeout_ordering)
public class TakeoutOrderingFragment extends Fragment {
	@ViewById ListView list;
	@ViewById(R.id.guest) TextView mGuest;
	
	@StringRes(R.string.label_name_guest) String strGuest;
	@StringArrayRes(R.array.pop_item_ordering) String[] popOrdering;	
	
	@FragmentArg Integer guest;
	private OnCallback callback;

	@AfterViews
	protected void intialized() {
		list.setAdapter(new OrderingAdapter(new String[]{"1","2"}));
		mGuest.setText(String.format("%s: %s", strGuest, Integer.toString(guest)));
	}

	public void callback(OnCallback callback) {
		this.callback = callback;
	}

	@Click
	public void confirm() {
		callback.setOnCallback(TakeoutOrderingFragment.this.getClass().getName());
	}

	@ItemClick
	protected void list(int pos) {
		TakeoutOrdersDialogFragment fragment = TakeoutOrdersDialogFragment_
			.builder().popOrders(popOrdering).build();
		fragment.show(getChildFragmentManager(), null);
		fragment.callback(new OnCallback() {
			@Override public void setOnCallback(Object object) {
				if(object != null) {
					String data = (String) object;
					executePopOrdering(data);
				}
			}
		});
	}

	private void executePopOrdering(String data) {
		if(popOrdering[0].equals(data)) {
			int start = list.getFirstVisiblePosition();
			for(int i=start, j=list.getLastVisiblePosition(); i<=j; i++) {
				View v = list.getChildAt(i-start);
				TextView tv = (TextView) v.findViewById(R.id.name);
				if("Item 11 2".equals(tv.getText().toString())) {
					list.getAdapter().getView(i, v, list);
					break;
				}
			}
		}
		if(popOrdering[1].equals(data)) {
			KitchenDialogFragment_.builder().build()
				.show(getChildFragmentManager(), null);
		}
		if(popOrdering[2].equals(data)) {
			QtyDialogFragment_.builder().build()
				.show(getChildFragmentManager(), null);
		}
	}

	private class OrderingAdapter extends BaseAdapter {
		private final Object[] object;
		private OrderingAdapter(Object[] object) {
			this.object = object;
		}
		
		@Override public int getCount() { return object.length; }
		@Override public long getItemId(int pos) { return (long) pos; }
		@Override public Object getItem(int pos) { return object[pos]; }

		public ViewOrdering getView(int pos, View recycle, ViewGroup parent) {
			ViewOrdering view = recycle != null ? (ViewOrdering) recycle :
				ViewOrdering_.build(parent.getContext());
			view.setData(object[pos]);
			return view;
		}
	}
}

@EViewGroup(R.layout.row_ordering)
class ViewOrdering extends LinearLayout {
	@ViewById TextView name;
	@ViewById TextView price;
	@ViewById TextView wait;
	@ViewById Button minus;
	@ViewById Button plus;

	private int value = 1;
	private double vPrice = 15000.0;

	public ViewOrdering(Context c) {
		super(c);	
	}

	public void setData(Object entry) {
		name.setText("Item 11");
		price.setText(Utils.currencyToString(vPrice));
		plus.setText(String.format("%d x", value));
	    minus.setText(String.format("%s ", 
	    			Utils.currencyToString(vPrice * value))); 
	}

	@Click
	protected void minus() {
		if(value != 0) {
			--value;
			plus.setText(String.format("%d x", value));
			minus.setText(String.format("%s ",
						Utils.currencyToString(vPrice * value))); 
		}
	}

	@Click
	protected void plus() {
		value++;
		plus.setText(String.format("%d x", value));
		minus.setText(String.format("%s ", 
					Utils.currencyToString(vPrice * value))); 
	}
}
