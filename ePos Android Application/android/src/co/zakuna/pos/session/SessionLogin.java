package co.zakuna.pos.session;

import org.androidannotations.annotations.sharedpreferences.*;

@SharedPref(value= SharedPref.Scope.UNIQUE)
public interface SessionLogin {
	@DefaultString("SESSION LOGIN") public String nameSession();
}
