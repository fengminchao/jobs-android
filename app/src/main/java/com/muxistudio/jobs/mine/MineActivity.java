package com.muxistudio.jobs.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.muxistudio.jobs.R;
import com.muxistudio.jobs.base.BaseActivity;

/**
 * Created by ybao on 16/6/19.
 */
public class MineActivity extends BaseActivity {


    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.appbar_layout)
    AppBarLayout mAppbarLayout;
    @Bind(R.id.image_headview)
    ImageView mImageHeadview;
    @Bind(R.id.tv_name)
    TextView mTvName;
    @Bind(R.id.tv_place)
    TextView mTvPlace;
    @Bind(R.id.tv_info)
    TextView mTvInfo;
    @Bind(R.id.tv_resume)
    TextView mTvResume;
    @Bind(R.id.tv_follow)
    TextView mTvFollow;
    @Bind(R.id.tv_history)
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
