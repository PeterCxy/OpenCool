package net.typeblog.coolapk.ui.common;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;

import net.typeblog.coolapk.R;
import static net.typeblog.coolapk.util.Utility.*;

public abstract class ToolbarActivity extends AppCompatActivity
{
	private Toolbar mToolbar;
	private AppBarLayout mAppBar;
	private TabLayout mTabLayout;
	
	protected abstract int getLayoutId();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getLayoutId());
		
		mToolbar = $(this, R.id.toolbar);
		mAppBar = $(this, R.id.appbar);
		mTabLayout = $(this, R.id.tab);
		
		if (mToolbar == null || mAppBar == null)
			throw new IllegalStateException("no toolbar");
		
		setSupportActionBar(mToolbar);
		
		if (Build.VERSION.SDK_INT >= 21) {
			mAppBar.setElevation(16.8f);
		}
	}
	
	public void bindTabs(ViewPager pager) {
		mTabLayout.setVisibility(View.VISIBLE);
		mTabLayout.setupWithViewPager(pager);
	}
	
	public void unbindTabs() {
		mTabLayout.setVisibility(View.GONE);
	}
}
