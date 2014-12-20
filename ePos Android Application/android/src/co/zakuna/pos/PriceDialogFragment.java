package co.zakuna.pos;

import org.androidannotations.annotations.*;

import android.widget.*;
import android.text.InputType;

@EFragment(R.layout.dialog_fragment_price)
public class PriceDialogFragment extends BaseDialogFragment {
	@ViewById TextView title;

	@AfterViews
	protected void initialized() {
		title.setText("Item 11");
	}

	@Click
	protected void save() {
		dismiss();
	}

	@Click
	protected void cancel() {
		dismiss();
	}
}
