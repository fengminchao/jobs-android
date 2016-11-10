package com.muxistudio.jobs.ui.career;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.muxistudio.jobs.R;
import com.muxistudio.jobs.bean.InfoData;
import java.util.List;

/**
 * Created by ybao on 16/11/10.
 */

public class CareerAdapter extends RecyclerView.Adapter<CareerAdapter.ViewHolder> {


  private List<InfoData> mInfoDatas;
  private int type;

  public CareerAdapter(List<InfoData> infoDatas, int type) {
    mInfoDatas = infoDatas;
    this.type = type;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_info, parent, false);

    return null;
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {

  }

  @Override public int getItemCount() {
    return 0;
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_logo) ImageView mIvLogo;
    @BindView(R.id.tv_title) TextView mTvTitle;
    @BindView(R.id.tv_time) TextView mTvTime;
    @BindView(R.id.tv_place) TextView mTvPlace;
    @BindView(R.id.tv_click) TextView mTvClick;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this,itemView);
    }
  }
}
