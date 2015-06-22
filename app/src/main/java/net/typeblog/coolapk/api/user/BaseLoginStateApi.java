package net.typeblog.coolapk.api.user;

import net.typeblog.coolapk.api.BaseApi;
import net.typeblog.coolapk.util.LoginManager;

public abstract class BaseLoginStateApi extends BaseApi
{
	private LoginManager mManager;
	
	protected BaseLoginStateApi() {
		mManager = LoginManager.getInstance();
	}
	
	protected void checkCookie() {
		if (mManager.isLoggedIn()) {
			setCookie(mManager.buildCookie());
		}
	}
}
