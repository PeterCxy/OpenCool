package net.typeblog.coolapk.api.user;

import com.google.gson.Gson;

import org.json.JSONObject;

import net.typeblog.coolapk.model.UserModel;
import net.typeblog.coolapk.util.HttpParameters;
import static net.typeblog.coolapk.api.Constants.*;
import static net.typeblog.coolapk.util.Constants.*;

public class UserApi extends BaseLoginStateApi
{
	public static Creator<UserApi> CREATOR = new Creator<UserApi>() {
		@Override
		public UserApi create() {
			return new UserApi();
		}
	};
	
	private UserApi() {
		super();
	}
	
	public UserModel getUserData(String uid) {
		checkCookie();
		
		HttpParameters params = new HttpParameters();
		params.put("uid", uid);
		params.put("listType", "userHome");
		params.put("page", "1");
		
		JSONObject result = requestJSON(HOST, GET_USER_DATA, params, null, HTTP_GET);
		
		if (result != null && result.optInt("status") > 0) {
			JSONObject data = result.optJSONObject("data");
			
			if (data != null) {
				return new Gson().fromJson(data.toString(), UserModel.class);
			}
		}
		
		return null;
	}
}
