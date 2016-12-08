package com.muxistudio.jobs.ui.collection;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.muxistudio.jobs.R;
import com.muxistudio.jobs.bean.CollectionData;
import com.muxistudio.jobs.db.Collection;
import com.muxistudio.jobs.util.TypeUtil;
import java.util.List;

/**
 * Created by ybao on 16/12/6.
 */

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.ViewHolder> {

  private List<Collection> mCollections;
  private OnItemClickListener mOnItemClickListener;

  public CollectionAdapter(List<Collection> collections) {
    mCollections = collections;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new ViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_collection, parent, false));
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    if (mCollections.get(position).getType().equals("宣讲会")) {
      holder.mTvLogo.setText("宣");
    } else if (mCollections.get(position).getType().equals("招聘会")) {
      holder.mTvLogo.setText("招");
    } else {
      holder.mTvLogo.setText("网");
    }
    holder.mTvTitle.setText(mCollections.get(position).getTitle());
    holder.mTvTime.setText(
        mCollections.get(position).getDate() + " " + mCollections.get(position).getTime());
    holder.mTvPlace.setText(
        mCollections.get(position).getSchool() + mCollections.get(position).getPlace());
    holder.mCardview.setOnClickListener(v -> {
      mOnItemClickListener.onItemClick(TypeUtil.toIntType(mCollections.get(position).getType()),
          mCollections.get(position).getId());
    });
  }

  @Override public int getItemCount() {
    return mCollections.size();
  }

  @Override public long getItemId(int position) {
    return mCollections.get(position).getId();
  }

  public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
    mOnItemClickListener = onItemClickListener;
  }

  public interface OnItemClickListener {

    void onItemClick(int type, int id);
  }

  class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_logo) TextView mTvLogo;
    @BindView(R.id.tv_title) TextView mTvTitle;
    @BindView(R.id.tv_time) TextView mTvTime;
    @BindView(R.id.tv_place) TextView mTvPlace;
    @BindView(R.id.content) RelativeLayout mContent;
    @BindView(R.id.cardview) CardView mCardview;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
