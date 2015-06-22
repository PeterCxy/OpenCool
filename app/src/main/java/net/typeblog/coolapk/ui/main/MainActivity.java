package net.typeblog.coolapk.ui.main;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import android.support.v4.widget.DrawerLayout;
import android.support.design.widget.NavigationView;

import com.squareup.picasso.Picasso;

import net.typeblog.coolapk.R;
import net.typeblog.coolapk.api.BaseApi;
import net.typeblog.coolapk.api.apk.ApkApi;
import net.typeblog.coolapk.api.user.UserApi;
import net.typeblog.coolapk.model.UserModel;

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
	private ImageView mAvatar;
	private TextView mUserName, mLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mDrawer = $(this, R.id.drawer);
		mNav = $(this, R.id.nav);
		mUserName = $(this, R.id.username);
		mLogin = $(this, R.id.login);
		mAvatar = $(this, R.id.avatar);
		
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
			//mLogin.setVisibility(View.GONE);
			mLogin.setOnClickListener(null);
			mLogin.setText("");
			
			mUserName.setText(manager.getUserName());
			
			new UserTask().execute();
		} else {
			mLogin.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					LoginDialogHelper.showLoginDialog(MainActivity.this);
				}
			});
		}
	}
	
	private class UserTask extends AsyncTask<Void, Void, UserModel> {
		String uid;
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			LoginManager login = LoginManager.getInstance();
			uid = login.getUid();
		}

		@Override
		protected UserModel doInBackground(Void... params) {
			return BaseApi.getInstance(UserApi.class).getUserData(uid);
		}

		@Override
		protected void onPostExecute(UserModel result) {
			super.onPostExecute(result);
			
			if (result != null) {
				Picasso.with(MainActivity.this)
					.load(result.userAvater)
					.fit()
					.centerCrop()
					.into(mAvatar);
					
				// Use the "Login" view to show user group
				// Too lazy to add a new view
				mLogin.setText(result.usergroupname);
			}
		}
	}
}
