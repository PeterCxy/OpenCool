package info.papdt.coolapk.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import android.support.v7.widget.RecyclerView;

import info.papdt.coolapk.R;
import info.papdt.coolapk.model.ApkModel;
import info.papdt.coolapk.model.ApkListModel;
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
	public void onBindViewHolder(ViewHolder h, int position) {
		ApkModel apk = mList.get(position);
		
		h.icon.setImageBitmap(null);
		h.title.setText(apk.title);
	}

	@Override
	public int getItemCount() {
		return mList.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		ImageView icon;
		TextView title;
		
		ViewHolder(View v) {
			super(v);
			
			icon = $(v, R.id.app_icon);
			title = $(v, R.id.app_title);
		}
	}
}
