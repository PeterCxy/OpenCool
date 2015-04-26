package info.papdt.coolapk.api.apk;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;

import info.papdt.coolapk.api.BaseApi;
import info.papdt.coolapk.model.ApkModel;
import info.papdt.coolapk.util.HttpParameters;
import static info.papdt.coolapk.api.Constants.*;
import static info.papdt.coolapk.util.Constants.*;

public class ApkApi extends BaseApi
{
	public static final Creator<ApkApi> CREATOR = new Creator<ApkApi>() {
		@Override
		public ApkApi create() {
			return new ApkApi();
		}
	};
	
	private ApkApi() {
		super();
	}
	
	public ArrayList<ApkModel> getApkList(String type, int page) {
		HttpParameters params = new HttpParameters();
		params.put("page", String.valueOf(page));
		params.put("listType", type);
		params.put("firstItemId", "");
		params.put("lastItemId", "");

		JSONObject result = requestJSON(HOST, APK_GET_LIST, params, null, HTTP_GET);
		
		if (result != null && !(result.optInt("status") > 0)) {
			return new Gson().fromJson(result.optString("data"), new TypeToken<ArrayList<ApkModel>>(){}.getType());
		} else {
			return null;
		}
	}
	
}
