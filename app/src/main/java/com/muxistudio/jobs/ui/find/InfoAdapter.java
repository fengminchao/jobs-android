package com.muxistudio.jobs.ui.find;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.muxistudio.jobs.R;
import com.muxistudio.jobs.bean.CareerList;
import com.muxistudio.jobs.bean.InfoData;
import java.util.List;

/**
 * Created by ybao on 16/11/10.
 */

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.ViewHolder> {

  private List<InfoData> mInfoDatas;
  //传进来的类型
  private int type;

  private ItemClickListener mItemClickListener;

  public InfoAdapter(List<InfoData> infoDatas, int type) {
    mInfoDatas = infoDatas;
    this.type = type;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(
        R.layout.item_info,parent,false
    ));
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    holder.mContent.setOnClickListener(
        v -> mItemClickListener.onItemClick(mInfoDatas.get(position).id));
  }


  public void setOnItemClickListener(ItemClickListener listener) {
    mItemClickListener = listener;
  }

  @Override public int getItemCount() {
    return mInfoDatas != null ? mInfoDatas.size() : 0;
  }

  static class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_logo) ImageView mIvLogo;
    @BindView(R.id.tv_title) TextView mTvTitle;
    @BindView(R.id.tv_time) TextView mTvTime;
    @BindView(R.id.tv_place) TextView mTvPlace;
    @BindView(R.id.tv_click) TextView mTvClick;
    @BindView(R.id.content) RelativeLayout mContent;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
