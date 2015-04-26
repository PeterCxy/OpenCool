package info.papdt.coolapk;

import android.app.Application;

import info.papdt.coolapk.util.GlobalContext;

public class MyApplication extends Application
{

	@Override
	public void onCreate() {
		super.onCreate();
		GlobalContext.set(this);
	}
}
