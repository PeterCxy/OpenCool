package net.typeblog.coolapk.ui.common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import net.typeblog.coolapk.R;
import net.typeblog.coolapk.util.AsyncTask;
import static net.typeblog.coolapk.util.Utility.*;

public abstract class BaseListFragment<A extends Adapter, M extends LayoutManager> extends Fragment
{
	protected abstract A onCreateAdapter();
	protected abstract void doLoad(boolean isNew);
	protected abstract void doApplyChanges(boolean isNew);
	protected abstract M onCreateLayoutManager();
	protected boolean allowMore() {
		return true;
	}
	
	protected RecyclerView mRecycler;
	protected SwipeRefreshLayout mRefresh;
	protected A mAdapter;
	protected M mManager;
	protected boolean mRefreshing = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.recycler, container, false);
		mRefresh = $(v, R.id.refresh);
		mRecycler = $(v, R.id.recycler);
		mManager = onCreateLayoutManager();
		mAdapter = onCreateAdapter();
		mRecycler.setLayoutManager(mManager);
		mRecycler.setAdapter(mAdapter);
		
		if (mManager instanceof GridLayoutManager) {
			mRecycler.setOnScrollListener(new RecyclerView.OnScrollListener() {
				@Override
				public void onScrolled(RecyclerView rv, int dx, int dy) {
					if (!mRefreshing && allowMore() && mManager.getItemCount() - 6 <= ((GridLayoutManager) mManager).findLastVisibleItemPosition()) {
						new RefreshTask().execute(false);
					}
				}
			});
		}
		
		mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				if (!mRefreshing) {
					new RefreshTask().execute(true);
				}
			}
		});
		
		new RefreshTask().execute(true);
		return v;
	}
	
	private class RefreshTask extends AsyncTask<Boolean, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mRefreshing = true;
			mRefresh.setRefreshing(true);
		}
		
		@Override
		protected Boolean doInBackground(Boolean... params) {
			doLoad(params[0]);
			return params[0];
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			
			mRefreshing = false;
			mRefresh.setRefreshing(false);
			doApplyChanges(result);
			mAdapter.notifyDataSetChanged();
		}

	}
}
