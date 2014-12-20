package co.zakuna.pos;

import org.androidannotations.annotations.*;

@EFragment(R.layout.dialog_fragment_void)
public class ReasonDialogFragment extends BaseDialogFragment {
	@Click
	protected void confirm() {
		dismiss();
	}

	@Click
	protected void cancel() {
		dismiss();
	}
}
