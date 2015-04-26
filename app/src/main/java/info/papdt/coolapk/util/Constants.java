package info.papdt.coolapk.util;

public class Constants
{
	// Http
	public static final String HTTP_GET = "GET";
	public static final String HTTP_POST = "POST";
	
	// Signature stuff
	public static final String SIGN_HEAD = "coolmarket";
	public static final String SIGN_TAIL = "d02c18fced6fd3135bc280636d4b8eed";
	public static final int SIGN_START = 0xA, SIGN_END = 0x14; // Coolapk uses part of MD5
}
