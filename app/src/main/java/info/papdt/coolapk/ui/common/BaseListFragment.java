package info.papdt.coolapk.ui.common;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.LayoutManager;

import info.papdt.coolapk.R;
import info.papdt.coolapk.util.AsyncTask;
import static info.papdt.coolapk.util.Utility.*;

public abstract class BaseListFragment<A extends Adapter, M extends LayoutManager> extends Fragment
{
	protected abstract A onCreateAdapter();
	protected abstract void doLoad(boolean isNew);
	protected abstract void doApplyChanges(boolean isNew);
	protected abstract M onCreateLayoutManager();
	
	protected RecyclerView mRecycler;
	protected A mAdapter;
	protected M mManager;
	protected boolean mRefreshing = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.recycler, container, false);
		mRecycler = $(v, R.id.recycler);
		mManager = onCreateLayoutManager();
		mAdapter = onCreateAdapter();
		mRecycler.setLayoutManager(mManager);
		mRecycler.setAdapter(mAdapter);
		new RefreshTask().execute(true);
		return v;
	}
	
	private class RefreshTask extends AsyncTask<Boolean, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mRefreshing = true;
		}
		
		@Override
		protected Boolean doInBackground(Boolean... params) {
			doLoad(params[0]);
			return params[0];
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			
			doApplyChanges(result);
			mAdapter.notifyDataSetChanged();
		}

	}
}
