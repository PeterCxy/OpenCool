package info.papdt.coolapk.ui.apk;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import info.papdt.coolapk.api.BaseApi;
import info.papdt.coolapk.api.apk.ApkApi;
import info.papdt.coolapk.model.ApkListModel;
import info.papdt.coolapk.ui.adapter.ApkAdapter;
import info.papdt.coolapk.ui.common.BaseListFragment;
import info.papdt.coolapk.ui.common.MyGridLayoutManager;
import static info.papdt.coolapk.util.Utility.*;

public class CheckUpdateFragment extends BaseListFragment<ApkAdapter, GridLayoutManager>
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
			mPendingList = BaseApi.getInstance(ApkApi.class).checkUpdate(getAppVersionCodes());
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
