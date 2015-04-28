package net.typeblog.coolapk.cache;

import java.lang.ref.WeakReference;
import java.util.HashMap;

public class MemoryCacheManager
{
	private static final HashMap<String, WeakReference<Object>> mMap = new HashMap<String, WeakReference<Object>>();
	
	public static void put(String key, Object cache) {
		mMap.put(key, new WeakReference<Object>(cache));
	}
	
	public static <T> T get(String key) {
		if (!mMap.containsKey(key)) {
			return null;
		} else {
			return (T) mMap.get(key).get();
		}
	}
}
