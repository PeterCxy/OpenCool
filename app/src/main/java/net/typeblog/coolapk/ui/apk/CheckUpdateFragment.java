package net.typeblog.coolapk.ui.apk;

import net.typeblog.coolapk.api.BaseApi;
import net.typeblog.coolapk.api.apk.ApkApi;
import static net.typeblog.coolapk.util.Utility.*;

public class CheckUpdateFragment extends BaseApkListFragment
{

	@Override
	protected void doLoad(boolean isNew) {
		if (isNew) {
			mPendingList = BaseApi.getInstance(ApkApi.class).checkUpdate(getAppVersionCodes());
		} else {
			// TODO
		}
	}

	@Override
	protected boolean allowMore() {
		return false;
	}

}
