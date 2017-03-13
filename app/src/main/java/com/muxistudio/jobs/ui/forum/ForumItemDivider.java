package com.muxistudio.jobs.ui.forum;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.muxistudio.jobs.App;
import com.muxistudio.jobs.R;

/**
 * Created by ybao (ybaovv@gmail.com)
 * Date: 17/3/13
 */

public class ForumItemDivider extends RecyclerView.ItemDecoration{

    private Drawable dividerTopic;
    private Drawable dividerReply;

    public ForumItemDivider() {
        super();
        dividerTopic = App.sContext.getResources().getDrawable(R.drawable.shape_post_setptal);
        dividerReply = App.sContext.getResources().getDrawable(R.drawable.shape_divider);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();

        View child1 = parent.getChildAt(0);
        RecyclerView.LayoutParams params1 = (RecyclerView.LayoutParams) child1.getLayoutParams();
        int top1 = child1.getBottom() + params1.bottomMargin;
        int bottom1 = top1 + dividerReply.getIntrinsicHeight();
        dividerTopic.setBounds(left,top1,right,bottom1);
        dividerTopic.draw(c);

        for (int i = 1; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + dividerReply.getIntrinsicHeight();

            dividerReply.setBounds(left, top, right, bottom);
            dividerReply.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
            RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
    }
}
