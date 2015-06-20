package net.typeblog.coolapk.ui.common;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import net.typeblog.coolapk.R;
import net.typeblog.coolapk.ui.common.ToolbarActivity;
import static net.typeblog.coolapk.util.Utility.*;

public abstract class BasePagerFragment extends Fragment
{
	protected abstract PagerAdapter buildAdapter();
	
	private ViewPager mPager;
	private PagerAdapter mAdapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.pager, container, false);
		mPager = $(v, R.id.pager);
		
		if (getActivity() != null) {
			init();
		}
		
		return v;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		if (mPager != null) {
			init();
		}
	}
	
	private void init() {
		mAdapter = buildAdapter();

		mPager.setAdapter(mAdapter);

		if (getActivity() instanceof ToolbarActivity) {
			((ToolbarActivity) getActivity()).bindTabs(mPager);
		}
	}
	
}
