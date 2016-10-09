package com.muxistudio.jobs.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.muxistudio.jobs.R;

import butterknife.BindView;


/**
 * Created by ybao on 16/5/19.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    @BindView(R.id.img_school_icon)
    ImageView mImgSchoolIcon;
    @BindView(R.id.tv_company)
    TextView mTvCompany;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.tv_place)
    TextView mTvPlace;
    @BindView(R.id.img_see)
    ImageView mImgSee;
    @BindView(R.id.tv_see)
    TextView mTvSee;
    @BindView(R.id.img_star)
    ImageView mImgStar;
    private Context mContext;

    public RecyclerViewAdapter(Context context) {
        super();
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_find, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 18;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public View mView;


        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }
    }

}