package info.papdt.coolapk.ui.common;

import android.os.Build;
import android.os.Bundle;
import android.view.ViewGroup;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import info.papdt.coolapk.R;
import static info.papdt.coolapk.util.Utility.*;

public abstract class ToolbarActivity extends ActionBarActivity
{
	private Toolbar mToolbar;
	private ViewGroup mToolbarWrapper;
	
	protected abstract int getLayoutId();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getLayoutId());
		
		mToolbar = $(this, R.id.toolbar);
		mToolbarWrapper = $(this, R.id.toolbar_wrapper);
		
		if (mToolbar == null || mToolbarWrapper == null)
			throw new IllegalStateException("no toolbar");
		
		setSupportActionBar(mToolbar);
		
		if (Build.VERSION.SDK_INT >= 21) {
			mToolbarWrapper.setElevation(16.8f);
		}
	}
}
