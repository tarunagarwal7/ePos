package co.zakuna.pos;

import org.androidannotations.annotations.*;
import org.androidannotations.annotations.res.*;

import android.widget.*;
import android.content.Intent;

@EActivity(R.layout.activity_settings)
public class SettingsActivity extends BaseActivity implements OnCallback {
	@ViewById(R.id.item_detail_container) FrameLayout detailSettings;
	@FragmentById(R.id.item_list) SettingListFragment listFragment;
	
	@Extra String title;

	@Override
	protected void initialized() {
		super.initialized();
		actionBar.setTitle(title);
		listFragment.setActivatedOnItemClick(true);
		listFragment.callback(this);
	}

	@Override
	public void setOnCallback(Object object) {
		SettingDetailFragment fragment = SettingDetailFragment_.builder().data((String) object).build();
		getSupportFragmentManager().beginTransaction()
			.replace(R.id.item_detail_container, fragment)
			.commit();
	}
}
