package co.zakuna.pos;

import org.androidannotations.annotations.*;
import org.androidannotations.annotations.res.*;

import android.view.MenuItem;

@EActivity(R.layout.activity_takeout_pane)
@OptionsMenu(R.menu.toolbar_takeout)
public class TakeoutActivity extends BaseActivity {
	@StringRes(R.string.title_takeout) String titleTakeout;
	@Extra String title;

	@OptionsMenuItem(R.id.void_order) MenuItem voidOrder;
	@OptionsMenuItem MenuItem reprint;

	@Override
	protected void initialized() {
		super.initialized();
		if(title !=  null) {
			actionBar.setTitle(title);
		} else {
			actionBar.setTitle(titleTakeout);
		}
	}

	@AfterViews
	protected void initOrders() {
		TakeoutOrdersFragment fragment = TakeoutOrdersFragment_.builder()
			.id(TakeoutOrdersFragment.KEY_LEFT).guest(title != null 
					? Integer.parseInt(title) : 1).build();
		getSupportFragmentManager().beginTransaction()
			.replace(R.id.orders, fragment)
			.commit();
	}

	@AfterViews
	protected void initMenuitem() {
		TakeoutOrdersFragment fragment = TakeoutOrdersFragment_.builder()
			.id(TakeoutOrdersFragment.KEY_RIGHT).build();
		fragment.callback(new OnCallback() {
			@Override public void setOnCallback(Object object) {
			}
		});
		getSupportFragmentManager().beginTransaction()
			.replace(R.id.products, fragment)
			.commit();
	}
	
	@OptionsItem
	protected void search() {
		TakeoutSearchDialogFragment_.builder().build()
			.show(getSupportFragmentManager(), null);
	}

	@OptionsItem(R.id.void_order)
	protected void voidOrder() {
		ReasonDialogFragment_.builder().build()
			.show(getSupportFragmentManager(), null);
	}

	@OptionsItem
	protected void reprint() {
	}

}
