package co.zakuna.pos;

import org.androidannotations.annotations.*;
import android.widget.*;

@EFragment(R.layout.dialog_fragment_transfer)
public class TransferTableDialogFragment extends BaseDialogFragment {
	@ViewById Spinner tables;
	
	@AfterViews
	protected void initialized() {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getActivity(), android.R.layout.simple_spinner_item, 
				new String[]{"table #1","table #2"});
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		tables.setAdapter(adapter);
	}

	@Click
	protected void confirm() {
		dismiss();
	}

	@Click
	protected void cancel() {
		dismiss();
	}
}
