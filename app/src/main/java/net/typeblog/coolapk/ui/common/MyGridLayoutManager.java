package net.typeblog.coolapk.ui.common;

import android.content.Context;

import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MyGridLayoutManager extends GridLayoutManager
{
	public MyGridLayoutManager(Context context, int spans) {
		super(context, spans);
	}


	private int[] mMeasuredDimension = new int[2];

	@Override
	public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state,
						  int widthSpec, int heightSpec) {
		final int widthMode = View.MeasureSpec.getMode(widthSpec);
		final int heightMode = View.MeasureSpec.getMode(heightSpec);
		final int widthSize = View.MeasureSpec.getSize(widthSpec);
		final int heightSize = View.MeasureSpec.getSize(heightSpec);
		int width = 0;
		int height = 0;
		for (int i = 0; i < getItemCount(); i += getSpanCount()) {    
			/*if (getOrientation() == HORIZONTAL) {
				measureScrapChild(recycler, i,
								  View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
								  View.MeasureSpec.makeMeasureSpec(heightSize, heightMode),
								  mMeasuredDimension);

				width = width + mMeasuredDimension[0];
				if (i == 0) {
					height = mMeasuredDimension[1];
				}
			} else {*/
				measureScrapChild(recycler, i,
								  View.MeasureSpec.makeMeasureSpec(widthSize / getSpanCount(), widthMode),
								  View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
								  mMeasuredDimension);

				height += mMeasuredDimension[1];
				if (i < getSpanCount()) {
					//width += mMeasuredDimension[0];
				}
			//}
		}

		// If child view is more than screen size, there is no need to make it wrap content. We can use original onMeasure() so we can scroll view.
		/*if (height < heightSize && width < widthSize) {

			switch (widthMode) {
				case View.MeasureSpec.EXACTLY:
					width = widthSize;
				case View.MeasureSpec.AT_MOST:
				case View.MeasureSpec.UNSPECIFIED:
			}

			switch (heightMode) {
				case View.MeasureSpec.EXACTLY:
					height = heightSize;
				case View.MeasureSpec.AT_MOST:
				case View.MeasureSpec.UNSPECIFIED:
			}

			setMeasuredDimension(width, height);
		} else {
			super.onMeasure(recycler, state, widthSpec, heightSpec);
		}*/
		setMeasuredDimension(widthSize, height);
	}

	private void measureScrapChild(RecyclerView.Recycler recycler, int position, int widthSpec,
								   int heightSpec, int[] measuredDimension) {

		if (getItemCount() <= position) return;
		View view = recycler.getViewForPosition(position);

		// For adding Item Decor Insets to view
		//super.measureChildWithMargins(view, 0, 0);
		if (view != null) {
			RecyclerView.LayoutParams p = (RecyclerView.LayoutParams) view.getLayoutParams();
			int childWidthSpec = ViewGroup.getChildMeasureSpec(widthSpec,
															   getPaddingLeft() + getPaddingRight() + getDecoratedLeft(view) + getDecoratedRight(view), p.width);
            int childHeightSpec = ViewGroup.getChildMeasureSpec(heightSpec,
																getPaddingTop() + getPaddingBottom() + getDecoratedTop(view) + getDecoratedBottom(view) , p.height);
            view.measure(childWidthSpec, childHeightSpec);

            // Get decorated measurements
            measuredDimension[0] = getDecoratedMeasuredWidth(view) + p.leftMargin + p.rightMargin;
            measuredDimension[1] = getDecoratedMeasuredHeight(view) + p.bottomMargin + p.topMargin;
            recycler.recycleView(view);
        }
    }
}
