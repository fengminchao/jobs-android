package com.muxistudio.jobs.widget;

import java.util.List;

/**
 * Created by ybao on 16/12/12.
 */

public class FlowAdapter {

    private OnSelectedListener mOnSelectedListener;

    private List<String> tagList;
    private List<String> selectedList;

    public FlowAdapter(List<String> tagList) {
        this.tagList = tagList;
    }

    public int getCount() {
        return tagList.size();
    }

    public Object getItem(int i) {
        return i;
    }

    public long getItemId(int i) {
        return i;
    }

    public String getStr(int position) {
        return tagList.get(position);
    }


    public void setOnSelectedListener(OnSelectedListener onSelectedListener) {
        if (onSelectedListener != null) {
            mOnSelectedListener = onSelectedListener;
        }
    }

    public interface OnSelectedListener {

        void onSelected(String s);
    }
}