package co.zakuna.pos;

import org.androidannotations.annotations.*;

import android.widget.*;
import android.text.InputType;
import android.text.TextUtils;

@EFragment(R.layout.dialog_fragment_qty)
public class QtyDialogFragment extends BaseDialogFragment {
	@ViewById TextView title;
	@ViewById EditText text;

	private int value = 0;

	@AfterViews
	protected void initialized() {
		title.setText("Item 11");
		text.setText("1");
	}

	@Click
	protected void save() {
		dismiss();
	}

	@Click
	protected void cancel() {
		dismiss();
	}

	private int getValue() {
		return TextUtils.isEmpty(text.getText().toString()) 
        	? 0 : Integer.parseInt(text.getText().toString());
	}

	@Click
	protected void minus() {
		if(value != 0) {
			value = getValue();
			--value;
			text.setText(String.format("%d", value));
		}
	}

	@Click
	protected void plus() {
		value = getValue();
		value++;
		text.setText(String.format("%d", value));
	}
}
