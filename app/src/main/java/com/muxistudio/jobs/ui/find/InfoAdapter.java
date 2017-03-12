package com.muxistudio.jobs.ui.find;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.muxistudio.jobs.Constant;
import com.muxistudio.jobs.R;
import com.muxistudio.jobs.bean.InfoData;
import com.muxistudio.jobs.util.Logger;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ybao on 16/11/10.
 */

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.ViewHolder> {

    private List<InfoData> mInfoDatas;
    //传进来的类型
    private int type;

    private Context mContext;

    private ItemClickListener mItemClickListener;

    public InfoAdapter(List<InfoData> infoDatas, int type) {
        mInfoDatas = infoDatas;
        this.type = type;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_info, parent,
                        false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mItemClickListener != null) {
            holder.mContent.setOnClickListener(
                    v -> mItemClickListener.onItemClick(mInfoDatas.get(position).id));
        }
        Logger.d(position + "");
        if (type == Constant.TYPE_ZP) {
            Picasso.with(mContext).load(R.drawable.ic_employ).into(holder.mIvLogo);
        } else {
            Picasso.with(mContext).load(Uri.parse(mInfoDatas.get(position).logoUrl)).into(
                    holder.mIvLogo);
        }
        holder.mTvTitle.setText(mInfoDatas.get(position).title);
        holder.mTvTime.setText(mInfoDatas.get(position).time);
        holder.mTvPlace.setText(mInfoDatas.get(position).place);
        if (mInfoDatas.get(position).clicks >= 0) {
            holder.mTvClick.setText(mInfoDatas.get(position).clicks + "");
        } else {
            holder.mIvClick.setVisibility(View.GONE);
        }
    }

    public void setOnItemClickListener(ItemClickListener listener) {
        mItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return mInfoDatas != null ? mInfoDatas.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_logo)
        ImageView mIvLogo;
        @BindView(R.id.tv_title)
        TextView mTvTitle;
        @BindView(R.id.tv_time)
        TextView mTvTime;
        @BindView(R.id.tv_place)
        TextView mTvPlace;
        @BindView(R.id.iv_click)
        ImageView mIvClick;
        @BindView(R.id.tv_click)
        TextView mTvClick;
        @BindView(R.id.layout_click)
        LinearLayout mLayoutClick;
        @BindView(R.id.content)
        RelativeLayout mContent;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
