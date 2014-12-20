package co.zakuna.pos;

import org.androidannotations.annotations.*;
import org.androidannotations.annotations.res.*;

import android.widget.*;

@EFragment(R.layout.dialog_fragment_void)
public class KitchenDialogFragment extends BaseDialogFragment {
	@ViewById TextView title;
	@ViewById Button cancel;
	@ViewById EditText text;

	@StringRes(R.string.title_kitchen_note) String titleNote;
	@StringRes(R.string.action_reset) String titleReset;

	@AfterViews
	protected void initialized() {
		title.setText(titleNote);
		cancel.setText(titleReset);
	}

	@Click
	protected void confirm() {
		dismiss();
	}

	@Click
	protected void cancel() {
		text.setText("");
	}
}
