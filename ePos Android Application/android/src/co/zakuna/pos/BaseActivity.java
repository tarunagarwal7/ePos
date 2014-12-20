package co.zakuna.pos;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import org.androidannotations.annotations.*;

@EActivity
public class BaseActivity extends ActionBarActivity {
	protected ActionBar actionBar;

	@AfterViews
	protected void initialized() {
		actionBar = getSupportActionBar();
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowHomeEnabled(true);
	}
	
	@Override
	protected void onCreate(Bundle saveInstance) {
		super.onCreate(saveInstance);
		overridePendingTransition(
				R.anim.activity_open_translate, R.anim.activity_close_scale);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		overridePendingTransition(
				R.anim.activity_open_scale, R.anim.activity_close_translate);
	}

	@OptionsItem(android.R.id.home)
	protected void onGoTo() {
		onBackPressed();
	}

}
