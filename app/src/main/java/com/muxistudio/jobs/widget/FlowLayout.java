package com.muxistudio.jobs.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.muxistudio.jobs.R;
import com.muxistudio.jobs.util.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ybao on 16/12/12.
 */

public class FlowLayout extends ViewGroup {

    private float mHorizontalSpacing = 0f;
    private float mVerticalSpacing = 0f;

    private Context mContext;

    private List<TagView> mTagViews = new ArrayList<>();
    private List<TagView> mSelectedView = new ArrayList<>();

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        Logger.d("attr");
    }

    public void setHorizontalSpacing(float spacing) {
        mHorizontalSpacing = spacing;
    }

    public void setVerticalSpacing(float spacing) {
        mVerticalSpacing = spacing;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int selfWidth = resolveSize(0, widthMeasureSpec);

        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        int childLeft = paddingLeft;
        int childTop = paddingTop;
        int lineHeight = 0;

        Logger.d("onmeasure");
        Logger.d(getChildCount() + "");
        for (int i = 0, childCount = getChildCount(); i < childCount; i++) {
            View childView = getChildAt(i);
            LayoutParams childLayoutParams = childView.getLayoutParams();
            childView.measure(getChildMeasureSpec(widthMeasureSpec, paddingLeft + paddingRight,
                    childLayoutParams.width),
                    getChildMeasureSpec(heightMeasureSpec, paddingTop + paddingBottom,
                            childLayoutParams.height));
            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();

            lineHeight = Math.max(childHeight, lineHeight);

            if (childLeft + childWidth + paddingRight > selfWidth) {
                childLeft = paddingLeft;
                childTop += mVerticalSpacing + lineHeight;
                lineHeight = childHeight;
            } else {
                childLeft += childWidth + mHorizontalSpacing;
            }
        }

        int wantedHeight = childTop + lineHeight + paddingBottom;
        setMeasuredDimension(selfWidth, resolveSize(wantedHeight, heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        int mWidth = i2 - i;
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();

        int childLeft = paddingLeft;
        int childTop = paddingTop;

        Logger.d("onlayout");
        int lineHeight = 0;

        Logger.d(getChildCount() + "");
        for (int j = 0; j < getChildCount(); j++) {
            View childView = getChildAt(j);
            //int childWidth = getChildAt(j).getWidth();
            //int childHeight = getChildAt(j).getHeight();

            if (childView.getVisibility() == GONE) {
                continue;
            }

            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();

            lineHeight = Math.max(childHeight, lineHeight);

            //if(mWidth)
            if (childLeft + childWidth + paddingRight > mWidth) {
                childLeft = paddingLeft;
                childTop += mVerticalSpacing + lineHeight;
                lineHeight = childHeight;
            }

            childView.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight);
            childLeft += childWidth + mHorizontalSpacing;
        }
    }

    public List<String> getTags() {

        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < mTagViews.size(); i++) {
            if (mTagViews.get(i).isChecked()) {
                stringList.add(mTagViews.get(i).getText().toString());
            }
        }
        return stringList;
        //Observable.from(mTagViews)
        //    .map(mTagView -> mTagView.getText().toString())
        //    .toList()
        //    .subscribe(strings -> {
        //
        //    })
    }

    //设置初始选中状态为 true
    public void setSelectedTags() {
        for (TagView tagView : mTagViews) {
            tagView.setChecked(true);
        }
    }

    public void setAdapter(FlowAdapter flowAdapter) {
        Logger.d("set adapter");
        this.removeAllViews();
        //CheckBox
        for (int i = 0; i < flowAdapter.getCount(); i++) {
            TagView tagView = new TagView(mContext);
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
            tagView.setText(flowAdapter.getStr(i));
            tagView.setPadding(16, 4, 16, 4);
            tagView.setBackgroundResource(R.drawable.bg_tag);
            tagView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((TagView) view).toggle();
                    Logger.d(((TagView) view).isChecked() + "");
                }
            });
            //tagView.setOnClickListener(v -> {
            //  Logger.d(((TagView)v).isChecked() + "");
            //});
            this.addView(tagView, params);
            mTagViews.add(tagView);
        }
    }
}