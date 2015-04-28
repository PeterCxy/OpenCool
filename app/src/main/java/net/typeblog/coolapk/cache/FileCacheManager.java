package net.typeblog.coolapk.cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import net.typeblog.coolapk.model.ApkModel;
import net.typeblog.coolapk.util.GlobalContext;
import net.typeblog.coolapk.util.HttpUtility;
import static net.typeblog.coolapk.util.Utility.*;

public class FileCacheManager
{
	private static final String APK_ICON = "apk_icon";
	
	private static FileCacheManager sInstance;
	
	private File mCacheDir;
	
	public static FileCacheManager getInstance() {
		if (sInstance == null) {
			sInstance = new FileCacheManager();
		}
		
		return sInstance;
	}
	
	private FileCacheManager() {
		mCacheDir = GlobalContext.get().getExternalCacheDir();
	}
	
	public Bitmap getApkIcon(ApkModel apk) {
		File dir = new File(mCacheDir.getAbsolutePath() + "/" + APK_ICON);
		if (!dir.exists())
			dir.mkdirs();
		
		File f = new File(dir.getAbsolutePath() + "/" + apk.apkname);
		if (!f.exists()) {
			HttpUtility.downloadRemoteFile(apk.logo, f, null);
		}
		
		if (f.exists()) {
			return BitmapFactory.decodeFile(f.getAbsolutePath());
		} else {
			return null;
		}
	}
}
