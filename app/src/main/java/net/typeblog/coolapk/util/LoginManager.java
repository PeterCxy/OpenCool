package net.typeblog.coolapk.util;

import android.content.Context;
import android.content.SharedPreferences;

import net.typeblog.coolapk.api.BaseApi;
import net.typeblog.coolapk.api.user.LoginApi;

public class LoginManager
{
	private static final String PREF = "login";
	private static final String USERNAME = "username";
	private static final String AUTH = "auth";
	private static final String UID = "uid";
	private static final String STATE = "state";
	
	private static LoginManager sInstance;
	
	public static final LoginManager getInstance() {
		if (sInstance == null) {
			sInstance = new LoginManager();
		}
		
		return sInstance;
	}
	
	private SharedPreferences mPref;
	
	private LoginManager() {
		mPref = GlobalContext.get().getSharedPreferences(PREF, Context.MODE_PRIVATE);
	}
	
	public boolean isLoggedIn() {
		return mPref.getBoolean(STATE, false);
	}
	
	public String getUid() {
		return mPref.getString(UID, "");
	}
	
	public String getAuth() {
		return mPref.getString(AUTH, "");
	}
	
	public String getUserName() {
		return mPref.getString(USERNAME, "");
	}
	
	public boolean doLogin(String login, String password) {
		LoginApi api = BaseApi.getInstance(LoginApi.class);
		api.setUserName(login);
		api.setPassword(password);
		
		if (api.login()) {
			return mPref.edit()
				.putString(UID, api.getUid())
				.putString(USERNAME, api.getName())
				.putString(AUTH, api.getAuth())
				.putBoolean(STATE, true)
				.commit();
		} else {
			return false;
		}
	}
}
