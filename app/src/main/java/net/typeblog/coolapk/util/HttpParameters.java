package net.typeblog.coolapk.util;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Set;

public class HttpParameters extends HashMap<String, String>
{
	// URL Encode
	public String encode() {
		StringBuilder str = new StringBuilder();
		Set<String> keys = keySet();
		boolean first = true;

		for (String key : keys) {
			Object value = get(key);

			if (first) {
				first = false;
			} else {
				str.append("&");
			}

			try {
				str.append(URLEncoder.encode(key, "UTF-8")).append("=").append(URLEncoder.encode(value.toString(), "UTF-8"));
			} catch (UnsupportedEncodingException e) {

			}
		}

		return str.toString();
	}
	
	public RequestBody toRequestBody() {
		FormEncodingBuilder builder = new FormEncodingBuilder();
		
		Set<String> keys = keySet();
		
		for (String key : keys) {
			builder.add(key, get(key));
		}
		
		return builder.build();
	}
}
