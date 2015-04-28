package net.typeblog.coolapk.ui.apk;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.typeblog.coolapk.api.BaseApi;
import net.typeblog.coolapk.api.apk.ApkApi;
import net.typeblog.coolapk.model.ApkListModel;
import net.typeblog.coolapk.ui.adapter.ApkAdapter;
import net.typeblog.coolapk.ui.common.BaseListFragment;
import net.typeblog.coolapk.ui.common.MyGridLayoutManager;
import static net.typeblog.coolapk.util.Utility.*;

public class NewAndUpdatedFragment extends BaseListFragment<ApkAdapter, GridLayoutManager>
{
	private ApkListModel mList;
	private ApkListModel mPendingList;

	@Override
	protected ApkAdapter onCreateAdapter() {
		mList = new ApkListModel();
		return new ApkAdapter(mList);
	}

	@Override
	protected void doLoad(boolean isNew) {
		if (isNew) {
			mPendingList = BaseApi.getInstance(ApkApi.class).getApkList("home", 1);
		} else {
			// TODO
		}
	}

	@Override
	protected void doApplyChanges(boolean isNew) {
		if (isNew) {
			mList.clear();
		}

		mList.addAll(mPendingList);
		mPendingList = null;
	}

	@Override
	protected GridLayoutManager onCreateLayoutManager() {
		return new MyGridLayoutManager(getActivity(), 3);
	}
}
