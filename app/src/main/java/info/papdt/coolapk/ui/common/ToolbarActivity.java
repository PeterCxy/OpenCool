package info.papdt.coolapk.ui.common;

import android.os.Build;
import android.os.Bundle;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import info.papdt.coolapk.R;
import static info.papdt.coolapk.util.Utility.*;

public abstract class ToolbarActivity extends ActionBarActivity
{
	private Toolbar mToolbar;
	
	protected abstract int getLayoutId();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getLayoutId());
		
		mToolbar = $(this, R.id.toolbar);
		
		if (mToolbar == null)
			throw new IllegalStateException("no toolbar");
		
		setSupportActionBar(mToolbar);
		
		if (Build.VERSION.SDK_INT >= 21) {
			mToolbar.setElevation(8.4f);
		}
	}
}
