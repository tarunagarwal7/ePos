package co.zakuna.pos.session;

import org.androidannotations.annotations.sharedpreferences.*;
import org.androidannotations.annotations.*;

@EBean
public class SessionLoginImpl {
	@Pref SessionLogin_ session;
	
	public void setSession(String value) {
		session.edit()
			.nameSession()
			.put(value)
			.apply();
	}

	public String getSession() {
		return session.nameSession().get();
	}

	public boolean isSession() {
		return session.nameSession().exists();
	}

	public void clear() {
		session.clear();
	}

}
