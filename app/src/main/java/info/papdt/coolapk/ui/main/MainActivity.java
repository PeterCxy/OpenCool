package info.papdt.coolapk.ui.main;

import android.os.Bundle;

import info.papdt.coolapk.R;
import info.papdt.coolapk.api.BaseApi;
import info.papdt.coolapk.api.apk.ApkApi;
import info.papdt.coolapk.ui.common.ToolbarActivity;
import info.papdt.coolapk.util.GlobalContext;

public class MainActivity extends ToolbarActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		new Thread() {
			@Override
			public void run() {
				BaseApi.getInstance(ApkApi.class).getApkList("home", 1);
			}
		}.start();
	}

	@Override
	protected int getLayoutId() {
		return R.layout.main;
	}
}
