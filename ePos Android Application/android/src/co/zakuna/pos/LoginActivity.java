package co.zakuna.pos;

import org.androidannotations.annotations.*;
import org.androidannotations.annotations.res.*;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.KeyboardView;
import android.widget.*;
import android.view.View;
import android.view.Gravity;
import android.text.TextUtils;
import android.content.Intent;
import android.content.Context;

import co.zakuna.pos.util.Utils;
import co.zakuna.pos.widget.KeypadView;
import co.zakuna.pos.session.SessionLoginImpl;

import java.util.List;
import java.util.ArrayList;

@NoTitle
@Fullscreen
@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActivity implements KeypadView.OnEnterKey {
	@ViewById KeyboardView keyboardView;
	@ViewById EditText inputLogin;
	@ViewById ImageButton clearText;
	@ViewById TextView version;
	@ViewById(R.id.layout_employee) RadioGroup layoutEmployee;

	@StringRes(R.string.error_msg_employee) String msgEmployee;
	@StringRes(R.string.error_msg_required) String msgRequired; 
	@StringRes(R.string.label_version_title) String versTitle;
	@StringRes(R.string.label_version_development) String versName;
	
	@ColorRes(R.color.text_color_hover) int textColorHover;
	@ColorRes(R.color.windowBackgroundColorPrimary) int textColorNormal;
	
	@DimensionRes(R.dimen.text_size_hover) float textSizeHover;
	@DimensionRes(R.dimen.text_size_normal) float textSizeNormal;

	@Bean SessionLoginImpl session;

	private final String EMPTY = "";
	private KeypadView keypadView;
	
	@Override
	protected void initialized() {
		super.initialized();
		keypadView = new KeypadView(LoginActivity.this, keyboardView, R.xml.keypad_login);
		keypadView.setOnEnterKey(this);
		keypadView.registerEditText(inputLogin);
		actionBar.hide();
	}

	@Override
	public void runEnter() {
		String input = inputLogin.getText().toString();
		int id = layoutEmployee.getCheckedRadioButtonId();

		if(id != -1) {
			if(!TextUtils.isEmpty(input)) {
				session.setSession(input);
				MainActivity_.intent(this).flags(Intent.FLAG_ACTIVITY_CLEAR_TOP).start();
				finish();
			} else {
				inputLogin.setError(msgRequired);
			}
		} else {
			Toast toast = Toast.makeText(this, msgEmployee, Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
		}
	}

	@TextChange
	protected void inputLogin(TextView t) {
		if(!TextUtils.isEmpty(t.getText().toString())) {
			clearText.setVisibility(View.VISIBLE);
			inputLogin.setError(null);
		} else {
			clearText.setVisibility(View.GONE);
		}
	}

	@Click
	protected void clearText() {
		inputLogin.setText(EMPTY);
	}

	@AfterViews
	protected void setVersion() {
		version.setText(String.format("%s %s %s", 
					versTitle, Utils.getDevice(this), versName));
	}

	@AfterViews
	protected void createEmployee() {
		TextEmployee[] textEmployee = new TextEmployee[2];
		
		for(int i=0; i<textEmployee.length; i++) {
			textEmployee[i] = TextEmployee_.build(this);
			if(i == 0) textEmployee[i].setData("Manager");
			if(i == 1) textEmployee[i].setData("Cashier");
			textEmployee[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton r, boolean isChecked) {
					RadioButton button = (RadioButton) r;
					if(isChecked) {
						button.setTextColor(textColorHover);
						button.setTextSize(textSizeHover);
						button.setTypeface(Typeface.DEFAULT_BOLD);
					} else {
						button.setTextColor(textColorNormal);
						button.setTextSize(textSizeNormal);
						button.setTypeface(null);
					}
				}
			});
			layoutEmployee.addView(textEmployee[i]);
		}
	}
}

@EView
class TextEmployee extends RadioButton {
	@ColorRes(R.color.windowBackgroundColorPrimary) int textColorNormal;
	@DimensionRes(R.dimen.text_size_normal) float textSizeNormal;
	@DrawableRes(R.drawable.radio_selector) Drawable drawableImage;

	private static final int sdk = android.os.Build.VERSION.SDK_INT;

	public TextEmployee(Context c) { 
		super(c); 
	}

	@AfterViews
	public void initialized() {
		setTextColor(textColorNormal);
		setTextSize(textSizeNormal);
		setButtonDrawable(android.R.color.transparent);
		
		if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
			setBackgroundDrawable(drawableImage);
		} else {
			setBackground(drawableImage);
		}
	}

	public void setData(Object employee) {
		setText((String) employee);
	}

}
