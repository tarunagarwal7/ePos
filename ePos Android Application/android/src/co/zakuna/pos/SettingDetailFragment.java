package co.zakuna.pos;

import org.androidannotations.annotations.*;
import org.androidannotations.annotations.res.*;

import android.widget.*;
import android.support.v4.app.Fragment;

@EFragment(R.layout.fragment_setting_detail)
public class SettingDetailFragment extends Fragment {
	@ViewById(R.id.item_detail) TextView itemDetail;
	@FragmentArg String data;

	@AfterViews
	protected void initialized() {
		itemDetail.setText(data);
	}

}
