package net.typeblog.coolapk;

import android.app.Application;

import net.typeblog.coolapk.util.GlobalContext;

public class MyApplication extends Application
{

	@Override
	public void onCreate() {
		super.onCreate();
		GlobalContext.set(this);
	}
}
