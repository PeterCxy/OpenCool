package net.typeblog.coolapk.api.user;

import com.google.gson.Gson;

import org.json.JSONObject;

import net.typeblog.coolapk.api.BaseApi;
import net.typeblog.coolapk.util.HttpParameters;
import net.typeblog.coolapk.util.StringUtility;
import static net.typeblog.coolapk.api.Constants.*;
import static net.typeblog.coolapk.util.Constants.*;

public class LoginApi extends BaseApi
{
	public static final Creator<LoginApi> CREATOR = new Creator<LoginApi>() {
		@Override
		public LoginApi create() {
			return new LoginApi();
		}
	};
	
	private String mUserName;
	private String mPassword;
	
	private String mUid;
	private String mAuth;
	private String mName;
	
	private LoginApi() {
		super();
	}
	
	public LoginApi setUserName(String username) {
		mUserName = username;
		return this;
	}
	
	public LoginApi setPassword(String password) {
		mPassword = password;
		return this;
	}
	
	public boolean login() {
		HttpParameters params = new HttpParameters();
		params.put("login", mUserName);
		params.put("password", StringUtility.md5(mPassword));
		
		JSONObject result = requestJSON(HOST, LOGIN, null, params, HTTP_POST);
		
		if (result != null && result.optInt("status") > 0) {
			JSONObject info = result.optJSONObject("data");
			
			if (info == null) {
				return false;
			}
			
			mAuth = info.optString("auth");
			mUid = info.optString("uid");
			mName = info.optString("username");
			
			if (mAuth != null && mUid != null && mName != null) {
				return true;
			}
		}
		
		return false;
	}
	
	public String getAuth() {
		return mAuth;
	}
	
	public String getName() {
		return mName;
	}
	
	public String getUid() {
		return mUid;
	}
}
