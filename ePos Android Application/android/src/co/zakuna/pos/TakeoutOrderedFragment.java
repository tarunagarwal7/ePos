package co.zakuna.pos;

import org.androidannotations.annotations.*;
import org.androidannotations.annotations.res.*;
import android.support.v4.app.Fragment;
import android.widget.*;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import co.zakuna.pos.util.Utils;

@EFragment(R.layout.fragment_takeout_ordered)
public class TakeoutOrderedFragment extends Fragment {
	@ViewById ListView list;
	@ViewById(R.id.guest) TextView mGuest;
	
	@StringRes(R.string.label_name_guest) String strGuest;
	@StringArrayRes(R.array.pop_item_ordered) String[] popOrdered;

	@FragmentArg Integer guest;
	private OnCallback callback;

	@AfterViews
	protected void initialized() {
		list.setAdapter(new OrderedAdapter(new String[]{""}));
		mGuest.setText(String.format("%s: %s", strGuest, Integer.toString(guest)));
	}

	public void callback(OnCallback callback) {
		this.callback = callback;
	}

	@ItemClick
	protected void list(int pos) {
		TakeoutOrdersDialogFragment fragment = TakeoutOrdersDialogFragment_
			.builder().popOrders(popOrdered).build();
		fragment.show(getChildFragmentManager(), null);
		fragment.callback(new OnCallback() {
			@Override public void setOnCallback(Object object) {
				if(object != null) {
					String data = (String) object;
					executePopOrdered(data);
				}
			}
		});
	}

	private void executePopOrdered(String data) {
		if(popOrdered[1].equals(data)) {
			ReasonDialogFragment_.builder().build()
				.show(getChildFragmentManager(), null);
		}
		if(popOrdered[2].equals(data)) {
			TransferTableDialogFragment_.builder().build()
				.show(getChildFragmentManager(), null);
		}
		if(popOrdered[3].equals(data)) {
			PriceDialogFragment_.builder().build()
				.show(getChildFragmentManager(), null);
		}
	}

	@Click
	protected void payment() {
		PaymentActivity_.intent(getActivity()).start();
	}

	private class OrderedAdapter extends BaseAdapter {
        private final Object[] object;
        private OrderedAdapter(Object[] object) {
            this.object = object;
        }

        @Override public int getCount() { return object.length; }
        @Override public long getItemId(int pos) { return (long) pos; }
        @Override public Object getItem(int pos) { return object[pos]; }

        public ViewOrdered getView(int pos, View recycle, ViewGroup parent) {
            ViewOrdered view = recycle != null ? (ViewOrdered) recycle :
                ViewOrdered_.build(parent.getContext());
            view.setData(null);
            return view;
        }
    }
}

@EViewGroup(R.layout.row_ordering)
class ViewOrdered extends LinearLayout {
    @ViewById TextView name;
    @ViewById TextView price;
    @ViewById Button minus;
    @ViewById Button plus;

    private int value = 1;
    private double vPrice = 15000.0;

    public ViewOrdered(Context c) {
        super(c);
    }

    public void setData(Object entry) {
		minus.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
		plus.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

        name.setText("Item 11");
        price.setText(Utils.currencyToString(vPrice));
        plus.setText(String.format("%d x", value));
        minus.setText(String.format("%s ",
                    Utils.currencyToString(vPrice * value)));
    }
}

