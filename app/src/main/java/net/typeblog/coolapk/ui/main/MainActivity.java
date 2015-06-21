package net.typeblog.coolapk.ui.main;

import android.os.Bundle;

import android.support.v4.widget.DrawerLayout;

import net.typeblog.coolapk.R;
import net.typeblog.coolapk.api.BaseApi;
import net.typeblog.coolapk.api.apk.ApkApi;
import net.typeblog.coolapk.ui.apk.CheckUpdateFragment;
import net.typeblog.coolapk.ui.common.ToolbarActivity;
import net.typeblog.coolapk.util.GlobalContext;
import static net.typeblog.coolapk.util.Utility.*;

public class MainActivity extends ToolbarActivity
{
	private DrawerLayout mDrawer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mDrawer = $(this, R.id.drawer);
		
		getSupportFragmentManager().beginTransaction().replace(R.id.frame, new HomeFragment()).commit();
	}

	@Override
	protected int getLayoutId() {
		return R.layout.main;
	}
}
