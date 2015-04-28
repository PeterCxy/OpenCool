package net.typeblog.coolapk.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;

public class GlobalContext
{
	private static Context sContext = null;
	
	public static void set(Context context) {
		sContext = context;
	}
	
	public static Context get() {
		return sContext;
	}
	
	public static SharedPreferences getSharedPreferences(String name) {
		return sContext.getSharedPreferences(name, Context.MODE_WORLD_READABLE);
	}
	
	public static PackageManager getPackageManager() {
		return sContext.getPackageManager();
	}
	
	public static LayoutInflater getLayoutInflater() {
		return (LayoutInflater) sContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
}
