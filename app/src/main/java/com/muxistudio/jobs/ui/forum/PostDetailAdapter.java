package com.muxistudio.jobs.ui.forum;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.muxistudio.jobs.R;
import com.muxistudio.jobs.bean.PostDetailResult;
import com.muxistudio.jobs.bean.ReplyData;
import com.muxistudio.jobs.util.CircleTransformation;
import com.muxistudio.jobs.util.TimeUtil;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ybao (ybaovv@gmail.com)
 * Date: 17/3/13
 */

public class PostDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private PostDetailResult.DataBean mPostDetailData;

    private Transformation circleTransformation = new CircleTransformation();

    public static final int ITEM_TYPE_TOPIC = 0;
    public static final int ITEM_TYPE_REPLY = 1;

    public PostDetailAdapter(PostDetailResult.DataBean postDetailData) {
        mPostDetailData = postDetailData;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM_TYPE_TOPIC;
        }
        return ITEM_TYPE_REPLY;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        switch (viewType) {
            case ITEM_TYPE_TOPIC:
                return new TopicViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_topic,
                                parent, false));
            case ITEM_TYPE_REPLY:
            default:
                return new ReplyViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_reply,
                                parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            TopicViewHolder topicViewHolder = (TopicViewHolder) holder;
            Picasso.with(mContext).load(mPostDetailData.topic.avator).transform(
                    circleTransformation).into(topicViewHolder.mIvAvatar);
            topicViewHolder.mTvTitle.setText(mPostDetailData.topic.title);
            topicViewHolder.mTvContent.setText(mPostDetailData.topic.content);
            topicViewHolder.mTvAuthor.setText(mPostDetailData.topic.name);
            topicViewHolder.mTvTime.setText(TimeUtil.toTimeInPost(new Date(mPostDetailData.topic.time)));
        }else {
            ReplyViewHolder replyViewHolder = (ReplyViewHolder) holder;
            ReplyData replyData = mPostDetailData.replys.get(position - 1);

            Picasso.with(mContext).load(mPostDetailData.replys.get(position - 1).avator).transform(
                    circleTransformation).into(replyViewHolder.mIvAvatar);
            replyViewHolder.mTvAuthor.setText(replyData.name);
            replyViewHolder.mTvContent.setText(replyData.content);
            replyViewHolder.mTvTime.setText(TimeUtil.toTimeInPost(new Date(replyData.time)));
        }
    }

    @Override
    public int getItemCount() {
        if (mPostDetailData.replys != null) {
            return mPostDetailData.replys.size() + 1;
        }
        return 0;
    }

    public static class TopicViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView mTvTitle;
        @BindView(R.id.iv_avatar)
        ImageView mIvAvatar;
        @BindView(R.id.tv_author)
        TextView mTvAuthor;
        @BindView(R.id.tv_content)
        TextView mTvContent;
        @BindView(R.id.tv_time)
        TextView mTvTime;

        public TopicViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class ReplyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_avatar)
        ImageView mIvAvatar;
        @BindView(R.id.tv_author)
        TextView mTvAuthor;
        @BindView(R.id.tv_content)
        TextView mTvContent;
        @BindView(R.id.tv_time)
        TextView mTvTime;

        public ReplyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
