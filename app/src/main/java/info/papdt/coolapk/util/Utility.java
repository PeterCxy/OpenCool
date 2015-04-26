package info.papdt.coolapk.util;

import android.app.Activity;
import android.view.View;

public class Utility
{
	public static <T> T $(Activity activity, int id) {
		return (T) activity.findViewById(id);
	}
	
	public static <T> T $(View v, int id) {
		return (T) v.findViewById(id);
	}
}
