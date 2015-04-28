package net.typeblog.coolapk.util;

import android.util.Log;

import org.apache.commons.lang3.StringEscapeUtils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static net.typeblog.coolapk.BuildConfig.DEBUG;
import static net.typeblog.coolapk.util.Constants.*;

public class HttpUtility {
	private static final String TAG = HttpUtility.class.getSimpleName();
	
	public static String httpRequest(String host, String location, HttpParameters urlParams, HttpParameters postParams, String method, String cookie) throws Exception {
		if (!method.equals(HTTP_GET) && !method.equals(HTTP_POST))
			throw new IllegalArgumentException("Illegal request method " + method);
		
		boolean isGet = method.equals(Constants.HTTP_GET);
		
		String reqUrl = location;
		String sendParams = postParams != null ? postParams.encode() : urlParams.encode();
		
		if (urlParams != null)
			reqUrl += "?" + urlParams.encode();
		
		reqUrl = host + reqUrl + "&sign=" + generateURLSignature(reqUrl);
		
		if (DEBUG)
			Log.d(TAG, "url = " + reqUrl);
		
		URL u = new URL(reqUrl);
		HttpURLConnection conn = (HttpURLConnection) u.openConnection();
		
		conn.setRequestMethod(method);
		conn.setDoOutput(!isGet);
		conn.setUseCaches(false);
		conn.setConnectTimeout(10000);
		conn.setReadTimeout(10000);
		conn.setRequestProperty("Connection", "Keep-Alive");
		conn.setRequestProperty("Charset", "UTF-8");
		
		if (cookie != null)
			conn.setRequestProperty("Cookie", cookie);
		
		if (sendParams != null) {
			conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
			
			if (!isGet) {
				DataOutputStream o = new DataOutputStream(conn.getOutputStream());
				o.write(sendParams.getBytes("UTF-8"));
				o.flush();
				o.close();
			}
			
		} else {
			// TODO: Boundary message
			return null;
		}
		
		if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			if (DEBUG)
				Log.d(TAG, "http ok");
			
			InputStream in = conn.getInputStream();

			String en = conn.getContentEncoding();

			if (en != null && en.equals("gzip")) {
				in = new GZIPInputStream(in);
			}

			BufferedReader buffer = new BufferedReader(new InputStreamReader(in, "UTF-8"));

			String s;
			StringBuilder str = new StringBuilder();

			while ((s = buffer.readLine()) != null) {
				str.append(s);
			}

			return StringEscapeUtils.unescapeJson(str.toString());
		} else {
			if (DEBUG)
				Log.d(TAG, "http " + conn.getResponseCode());
			
			return null;
		}
	}
	
	public static String generateURLSignature(String url) {
		
		if (DEBUG) {
			Log.d(TAG, "url to sign: " + url);
		}
		
		String data = SIGN_HEAD + url + SIGN_TAIL;
		
		MessageDigest digest = null;
		
		try {
			digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			return "";
		}
		
		byte[] bytes = null;
		
		try {
			bytes = digest.digest(data.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			return "";
		}
		
		// Digest to hex
		StringBuilder sb = new StringBuilder();
		
		for (byte b : bytes) {
			sb.append(String.format("%02x", b & 0xff));
		}
		
		return sb.toString().substring(SIGN_START, SIGN_END);
	}
	
	public static InputStream getRemoteFileInput(String uri) {
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(uri).openConnection();
			conn.setRequestMethod(HTTP_GET);
			conn.setConnectTimeout(5000);
			return conn.getInputStream();
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
