package net.typeblog.coolapk.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import net.typeblog.coolapk.R;
import net.typeblog.coolapk.ui.apk.CheckUpdateFragment;
import net.typeblog.coolapk.ui.apk.NewAndUpdatedFragment;
import net.typeblog.coolapk.ui.common.BasePagerFragment;
import static net.typeblog.coolapk.util.Utility.*;

public class HomeFragment extends BasePagerFragment
{
	private Fragment[] mFragments = {
		new CheckUpdateFragment(),
		new NewAndUpdatedFragment()
	};

	@Override
	protected PagerAdapter buildAdapter() {
		final String[] titles = getResources().getStringArray(R.array.home_tabs);
		return new FragmentStatePagerAdapter(getChildFragmentManager()) {
			@Override
			public int getCount() {
				return mFragments.length;
			}

			@Override
			public Fragment getItem(int pos) {
				return mFragments[pos];
			}
			
			@Override
			public CharSequence getPageTitle(int pos) {
				return titles[pos];
			}
		};
	}
}
