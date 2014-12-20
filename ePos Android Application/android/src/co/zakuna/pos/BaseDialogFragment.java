package co.zakuna.pos;

import android.os.Bundle;
import android.view.Window;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.DialogFragment;
import android.app.Dialog;

public class BaseDialogFragment extends DialogFragment {
	@Override
	public Dialog onCreateDialog(Bundle saveInstance) {
		Dialog dialog = super.onCreateDialog(saveInstance);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.getWindow().getAttributes().windowAnimations = R.style.dialog_animation;
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		//dialog.setCanceledOnTouchOutside(false);
		return dialog;
	}
}
