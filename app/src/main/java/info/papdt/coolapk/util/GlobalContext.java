package info.papdt.coolapk.util;

import android.content.Context;
import android.content.SharedPreferences;

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
}
