package net.typeblog.coolapk.ui.apk;

import net.typeblog.coolapk.api.BaseApi;
import net.typeblog.coolapk.api.apk.ApkApi;
import static net.typeblog.coolapk.util.Utility.*;

public class NewAndUpdatedFragment extends BaseApkListFragment
{
	private int mPage = 0;

	@Override
	protected void doLoad(boolean isNew) {
		if (isNew) {
			mPage = 0;
		}
		
		mPendingList = BaseApi.getInstance(ApkApi.class).getApkList("home", ++mPage);
	}
}
