package net.typeblog.coolapk.ui.main;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import android.support.v4.widget.DrawerLayout;
import android.support.design.widget.NavigationView;

import net.typeblog.coolapk.R;
import net.typeblog.coolapk.api.BaseApi;
import net.typeblog.coolapk.api.apk.ApkApi;
import net.typeblog.coolapk.ui.apk.CheckUpdateFragment;
import net.typeblog.coolapk.ui.common.ToolbarActivity;
import net.typeblog.coolapk.ui.dialog.LoginDialogHelper;
import net.typeblog.coolapk.util.GlobalContext;
import net.typeblog.coolapk.util.LoginManager;
import static net.typeblog.coolapk.util.Utility.*;

public class MainActivity extends ToolbarActivity
{
	private DrawerLayout mDrawer;
	private NavigationView mNav;
	private TextView mUserName, mLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mDrawer = $(this, R.id.drawer);
		mNav = $(this, R.id.nav);
		mUserName = $(this, R.id.username);
		mLogin = $(this, R.id.login);
		
		reloadUserInfo();
		
		getSupportFragmentManager().beginTransaction().replace(R.id.frame, new HomeFragment()).commit();
	}

	@Override
	protected int getLayoutId() {
		return R.layout.main;
	}
	
	public void reloadUserInfo() {
		LoginManager manager = LoginManager.getInstance();
		
		if (manager.isLoggedIn()) {
			mLogin.setVisibility(View.GONE);
			mLogin.setOnClickListener(null);
			
			mUserName.setText(manager.getUserName());
		} else {
			mLogin.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					LoginDialogHelper.showLoginDialog(MainActivity.this);
				}
			});
		}
	}
}
