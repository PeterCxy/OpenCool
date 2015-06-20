package net.typeblog.coolapk.util;

import android.util.Log;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import org.apache.commons.lang3.StringEscapeUtils;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

import static net.typeblog.coolapk.BuildConfig.DEBUG;
import static net.typeblog.coolapk.util.Constants.*;

public class HttpUtility {
	private static final String TAG = HttpUtility.class.getSimpleName();
	
	public static String httpRequest(String host, String location, HttpParameters urlParams, HttpParameters postParams, String method, String cookie) throws Exception {
		Request.Builder builder = new Request.Builder()
						.addHeader("Cookie", cookie);
		
		if (!method.equals(HTTP_GET) && !method.equals(HTTP_POST))
			throw new IllegalArgumentException("Illegal request method " + method);
		
		boolean isGet = method.equals(Constants.HTTP_GET);
		
		String reqUrl = location;
		
		if (urlParams != null)
			reqUrl += "?" + urlParams.encode();
		
		reqUrl = host + reqUrl + "&sign=" + generateURLSignature(reqUrl);
		
		if (DEBUG)
			Log.d(TAG, "url = " + reqUrl);
			
		builder.url(reqUrl);
		
		if (!isGet) {
			builder.post(postParams.toRequestBody());
		} else {
			builder.get();
		}
		
		return new OkHttpClient().newCall(builder.build()).execute().body().string();
	}
	
	public static String generateURLSignature(String url) {
		
		if (DEBUG) {
			Log.d(TAG, "url to sign: " + url);
		}
		
		String data = SIGN_HEAD + url + SIGN_TAIL;
		
		
		
		return StringUtility.md5(data).substring(SIGN_START, SIGN_END);
	}
	
	public static InputStream getRemoteFileInput(String uri) {
		try {
			return new OkHttpClient().newCall(new Request.Builder()
				.url(uri)
				.get()
				.build()).execute().body().byteStream();
		} catch (Exception e) {
			return null;
		}
	}
	
	public static byte[] getRemoteFileBytes(String uri, ProgressCallback callback) {
		InputStream i = getRemoteFileInput(uri);
		byte[] ret = Utility.readInputStream(i, callback);
		
		try {
			i.close();
		} catch (Exception e) {
			
		}
		
		return ret;
	}
	
	public static void downloadRemoteFile(String uri, File f, ProgressCallback callback) {
		InputStream i = getRemoteFileInput(uri);
		Utility.writeInputStreamToFile(i, f, callback);
		
		try {
			i.close();
		} catch (Exception e) {
			f.delete();
		}
	}
}
