package com.muxistudio.jobs.ui.forum;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.muxistudio.jobs.R;
import com.muxistudio.jobs.bean.PostData;
import com.muxistudio.jobs.util.CircleTransformation;
import com.muxistudio.jobs.util.TimeUtil;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ybao on 17/1/3.
 */

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.ViewHolder> {

    private List<PostData> mPostDataList;
    private OnItemClickListener mOnItemClickListener;
    private Context mContext;

    public PostListAdapter(List<PostData> postDatas) {
        mPostDataList = postDatas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_forum, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTvTitle.setText(mPostDataList.get(position).title);
        holder.mTvContent.setText(mPostDataList.get(position).content);
        holder.mTvReply.setText(String.format("回复：%d",mPostDataList.get(position).reply));
        holder.mTvTime.setText(TimeUtil.toTimeInPost(new Date(mPostDataList.get(position).time)));
        holder.mTvName.setText(mPostDataList.get(position).name);
        if (TextUtils.isEmpty(mPostDataList.get(position).avator)) {
            Picasso.with(mContext).load(R.drawable.default_avatar).transform(
                    new CircleTransformation()).into(holder.mIvAvator);
        } else {
            Picasso.with(mContext).load(mPostDataList.get(position).avator).transform(
                    new CircleTransformation()).into(holder.mIvAvator);
        }
        holder.mCardView.setOnClickListener(v -> {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(mPostDataList.get(position).pid);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPostDataList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    interface OnItemClickListener {

        void onItemClick(int pid);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView mTvTitle;
        @BindView(R.id.tv_content)
        TextView mTvContent;
        @BindView(R.id.iv_avator)
        ImageView mIvAvator;
        @BindView(R.id.tv_name)
        TextView mTvName;
        @BindView(R.id.tv_reply)
        TextView mTvReply;
        @BindView(R.id.tv_time)
        TextView mTvTime;
        @BindView(R.id.cardview)
        CardView mCardView;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
