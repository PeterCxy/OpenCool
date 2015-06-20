package net.typeblog.coolapk.api.apk;

import android.os.Build;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.Map;

import net.typeblog.coolapk.api.BaseApi;
import net.typeblog.coolapk.model.ApkModel;
import net.typeblog.coolapk.model.ApkListModel;
import net.typeblog.coolapk.util.HttpParameters;
import static net.typeblog.coolapk.BuildConfig.DEBUG;
import static net.typeblog.coolapk.api.Constants.*;
import static net.typeblog.coolapk.util.Constants.*;

public class ApkApi extends BaseApi
{
	private static final String TAG = ApkApi.class.getSimpleName();
	
	public static final Creator<ApkApi> CREATOR = new Creator<ApkApi>() {
		@Override
		public ApkApi create() {
			return new ApkApi();
		}
	};
	
	private ApkApi() {
		super();
	}
	
	public ApkListModel getApkList(String type, int page) {
		HttpParameters params = new HttpParameters();
		params.put("page", String.valueOf(page));
		params.put("listType", type);
		params.put("firstItemId", "");
		params.put("lastItemId", "");

		JSONObject result = requestJSON(HOST, APK_GET_LIST, params, null, HTTP_GET);
		
		if (result != null && result.optInt("status") > 0) {
			return new Gson().fromJson(result.toString(), ApkListModel.class);
		} else {
			return null;
		}
	}
	
	// appInfo: name -> versionCode
	public ApkListModel checkUpdate(Map<String, Integer> appInfo) {
		HttpParameters params = new HttpParameters();
		params.put("sdk", Build.VERSION.SDK);
		params.put("beta", "0");
		params.put("pkgs", buildPkgInfoStr(appInfo));
		
		JSONObject result = requestJSON(HOST, APK_CHECK_UPDATE, null, params, HTTP_POST);
		
		if (result != null && result.optInt("status") > 0) {
			return new Gson().fromJson(result.toString(), ApkListModel.class);
		} else {
			
			if (DEBUG) {
				Log.e(TAG, "IllegalResult " + (result != null ? result.toString() : ""));
			}
			
			return null;
		}
	}
	
	private String buildPkgInfoStr(Map<String, Integer> appInfo) {
		StringBuilder sb = new StringBuilder();
		
		for (Map.Entry<String, Integer> entry : appInfo.entrySet()) {
			sb.append(entry.getKey()).append(":")
				.append(String.valueOf(entry.getValue())).append(", ");
		}
		
		return "[" + sb.toString().trim().substring(0, sb.length() - 2) + "]";
	}
}
