package net.typeblog.coolapk.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtility
{
	public static String md5(String data) {
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
		
		return sb.toString();
	}
}
