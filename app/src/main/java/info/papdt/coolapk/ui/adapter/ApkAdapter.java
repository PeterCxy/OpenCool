package info.papdt.coolapk.ui.adapter;

import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import android.support.v7.widget.RecyclerView;

import info.papdt.coolapk.R;
import info.papdt.coolapk.cache.FileCacheManager;
import info.papdt.coolapk.model.ApkModel;
import info.papdt.coolapk.model.ApkListModel;
import info.papdt.coolapk.util.AsyncTask;
import info.papdt.coolapk.util.GlobalContext;
import static info.papdt.coolapk.util.Utility.*;

public class ApkAdapter extends RecyclerView.Adapter<ApkAdapter.ViewHolder>
{
	private ApkListModel mList;
	
	public ApkAdapter(ApkListModel list) {
		mList = list;
	}

	@Override
	public ApkAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = GlobalContext.getLayoutInflater().inflate(R.layout.app_card, parent, false);
		return new ViewHolder(v);
	}

	@Override
	public void onViewRecycled(ViewHolder h) {
		super.onViewRecycled(h);
		h.pos = -1;
	}

	@Override
	public void onBindViewHolder(ViewHolder h, int position) {
		ApkModel apk = mList.get(position);
		
		h.pos = position;
		h.icon.setImageBitmap(null);
		h.title.setText(apk.title);
		
		new IconTask().execute(h, position);
	}

	@Override
	public int getItemCount() {
		return mList.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		ImageView icon;
		TextView title;
		int pos = -1;
		
		ViewHolder(View v) {
			super(v);
			
			icon = $(v, R.id.app_icon);
			title = $(v, R.id.app_title);
		}
	}
	
	private class IconTask extends AsyncTask<Object, Void, Object[]> {

		@Override
		protected Object[] doInBackground(Object... params) {
			ViewHolder h = (ViewHolder) params[0];
			int pos = Integer.valueOf(params[1]);
			if (h.pos != pos) return null;
			Bitmap bmp = FileCacheManager.getInstance().getApkIcon(mList.get(pos));
			return new Object[]{h, pos, bmp};
		}

		@Override
		protected void onPostExecute(Object[] result) {
			super.onPostExecute(result);
			
			ViewHolder h = (ViewHolder) result[0];
			int pos = Integer.valueOf(result[1]);
			
			if (h.pos == pos) {
				h.icon.setImageBitmap((Bitmap) result[2]);
			}
		}
	}
}
