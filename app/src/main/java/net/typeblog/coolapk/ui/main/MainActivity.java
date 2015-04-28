package net.typeblog.coolapk.ui.main;

import android.os.Bundle;

import net.typeblog.coolapk.R;
import net.typeblog.coolapk.api.BaseApi;
import net.typeblog.coolapk.api.apk.ApkApi;
import net.typeblog.coolapk.ui.common.ToolbarActivity;
import net.typeblog.coolapk.util.GlobalContext;
import static net.typeblog.coolapk.util.Utility.*;

public class MainActivity extends ToolbarActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getFragmentManager().beginTransaction().replace(R.id.frame, new HomeFragment()).commit();
	}

	@Override
	protected int getLayoutId() {
		return R.layout.main;
	}
}
