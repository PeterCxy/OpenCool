package net.typeblog.coolapk.ui.apk;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.typeblog.coolapk.model.ApkListModel;
import net.typeblog.coolapk.ui.adapter.ApkAdapter;
import net.typeblog.coolapk.ui.common.BaseListFragment;
import static net.typeblog.coolapk.util.Utility.*;

public abstract class BaseApkListFragment extends BaseListFragment<ApkAdapter, GridLayoutManager>
{
	private ApkListModel mList;
	protected ApkListModel mPendingList;
	
	@Override
	protected ApkAdapter onCreateAdapter() {
		mList = new ApkListModel();
		return new ApkAdapter(mList);
	}
	
	@Override
	protected void doApplyChanges(boolean isNew) {
		if (isNew) {
			mList.clear();
		}

		if (mPendingList != null)
			mList.addAll(mPendingList);
		
		mPendingList = null;
	}

	@Override
	protected GridLayoutManager onCreateLayoutManager() {
		return new GridLayoutManager(getActivity(), 3);
	}
}
