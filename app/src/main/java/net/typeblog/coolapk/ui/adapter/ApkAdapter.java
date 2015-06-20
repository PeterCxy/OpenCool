package net.typeblog.coolapk.ui.adapter;

import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import android.support.v7.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import net.typeblog.coolapk.R;
import net.typeblog.coolapk.model.ApkModel;
import net.typeblog.coolapk.model.ApkListModel;
import net.typeblog.coolapk.util.AsyncTask;
import net.typeblog.coolapk.util.GlobalContext;
import static net.typeblog.coolapk.util.Utility.*;

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
		h.title.setText(apk.title);
		
		Picasso.with(GlobalContext.get())
			.load(apk.logo)
			.fit()
			.centerInside()
			.into(h.icon);
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
}
