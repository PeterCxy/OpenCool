package info.papdt.coolapk.util;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.View;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utility
{
	public static <T> T $(Activity activity, int id) {
		return (T) activity.findViewById(id);
	}
	
	public static <T> T $(View v, int id) {
		return (T) v.findViewById(id);
	}
	
	public static Map<String, Integer> getAppVersionCodes() {
		List<ApplicationInfo> apps = getAllInstalledApp();
		
		Map<String, Integer> ret = new HashMap<String, Integer>();
		
		for (ApplicationInfo appInfo : apps) {
			PackageInfo pkgInfo = getPackageInfo(appInfo.packageName);
			
			if (pkgInfo != null) {
				ret.put(pkgInfo.packageName, pkgInfo.versionCode);
			}
		}
		
		return ret;
	}
	
	public static List<ApplicationInfo> getAllInstalledApp() {
		PackageManager pm = GlobalContext.getPackageManager();
		return pm.getInstalledApplications(PackageManager.GET_META_DATA);
	}
	
	public static PackageInfo getPackageInfo(String pkgName) {
		PackageManager pm = GlobalContext.getPackageManager();
		
		try {
			return pm.getPackageInfo(pkgName, PackageManager.GET_META_DATA);
		} catch (Exception e) {
			return null;
		}
	}
}
