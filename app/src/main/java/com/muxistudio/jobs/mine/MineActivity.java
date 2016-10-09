package com.muxistudio.jobs.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.muxistudio.jobs.R;
import com.muxistudio.jobs.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ybao on 16/6/19.
 */
public class MineActivity extends BaseActivity {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.appbar_layout)
    AppBarLayout mAppbarLayout;
    @BindView(R.id.image_headview)
    ImageView mImageHeadview;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_place)
    TextView mTvPlace;
    @BindView(R.id.tv_info)
    TextView mTvInfo;
    @BindView(R.id.tv_resume)
    TextView mTvResume;
    @BindView(R.id.tv_follow)
    TextView mTvFollow;
    @BindView(R.id.tv_history)
    TextView mTvHistory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @OnClick(R.id.image_headview)
    public void onClick() {

    }
}
