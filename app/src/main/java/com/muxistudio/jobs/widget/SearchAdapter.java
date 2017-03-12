package com.muxistudio.jobs.widget;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.muxistudio.jobs.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ybao on 16/12/10.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private List<String> suggestionList;

    private OnItemClickListener mOnItemClickListener;

    public SearchAdapter(List<String> queryList) {
        suggestionList = queryList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent,
                        false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTvQuery.setText(suggestionList.get(position));
        holder.mTvQuery.setOnClickListener(v -> {
            mOnItemClickListener.onItemClick(holder.mTvQuery.getText().toString());
        });
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return suggestionList.size();
    }

    interface OnItemClickListener {

        void onItemClick(String query);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_query)
        TextView mTvQuery;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
