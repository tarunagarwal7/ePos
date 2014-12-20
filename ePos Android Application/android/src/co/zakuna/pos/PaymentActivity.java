package co.zakuna.pos;

import org.androidannotations.annotations.*;
import org.androidannotations.annotations.res.*;

import android.graphics.Typeface;
import android.content.Context;
import android.widget.*;
import android.view.View;
import android.view.ViewGroup;

@EActivity(R.layout.activity_payment)
public class PaymentActivity extends BaseActivity {
	@ViewById GridView rows;
	@ViewById(R.id.group_payment) RadioGroup groupPayment;	
	@StringRes(R.string.title_payment) String title;

	@ColorRes(R.color.text_color_hover) int textColorHover;
    @ColorRes(R.color.windowBackgroundColorPrimary) int textColorNormal;

    @DimensionRes(R.dimen.text_size_hover) float textSizeHover;
    @DimensionRes(R.dimen.text_size_normal) float textSizeNormal;
	
	@Override
	protected void initialized() {
		super.initialized();
		actionBar.setTitle(title);
		String[] data = new String[]{
			"7","8","9","Discount",
			"4","5","6","Taxes Include",
			"1","2","3", "Service Include",
			"0","00",".","Drawer",
			"Backspace","Clear","Exactly","Print"
		};	
		rows.setAdapter(new PaymentAdapter(data));
	}

	@AfterViews
	protected void createPayment() {
		TextEmployee[] pay = new TextEmployee[6];
		for(int i=0; i<pay.length; i++) {
			pay[i] = TextEmployee_.build(this);
			if(i == 0) pay[i].setData("Cash");
			if(i == 1) pay[i].setData("Master");
			if(i == 2) pay[i].setData("Visa");
			if(i == 3) pay[i].setData("Debit Card");
			if(i == 4) pay[i].setData("Cheque");
			if(i == 5) pay[i].setData("Gift Card");
			pay[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton r, boolean isChecked) {
                    RadioButton button = (RadioButton) r;
                    if(isChecked) {
                        button.setTextColor(textColorHover);
                        button.setTextSize(textSizeHover);
                        button.setTypeface(Typeface.DEFAULT_BOLD);
                    } else {
                        button.setTextColor(textColorNormal);
                        button.setTextSize(textSizeNormal);
						button.setTypeface(null);
                    }
                }
            });	
			groupPayment.addView(pay[i]);
		}
	}

	private class PaymentAdapter extends BaseAdapter {
        private final Object[] object;
        public PaymentAdapter(Object[] object) { this.object = object; }

        @Override public int getCount() { return object.length; }
        @Override public long getItemId(int pos) { return (long) pos; }
        @Override public Object getItem(int pos) { return object[pos]; }

        public ViewPay getView(int pos, View recycle, ViewGroup parent) {
            ViewPay viewRow = recycle != null ? (ViewPay) recycle :
                ViewPay_.build(parent.getContext());
            viewRow.setData(object[pos]);
            return viewRow;
        }
    }

}

@EViewGroup(R.layout.row_payment)
class ViewPay extends LinearLayout {
    @ViewById TextView title;

    public ViewPay(Context c) {
        super(c);
    }

    public void setData(Object entry) {
        title.setText((String) entry);
    }
}

