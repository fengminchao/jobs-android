package com.muxistudio.jobs.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.muxistudio.jobs.R;
import com.muxistudio.jobs.base.BaseActivity;

/**
 * Created by ybao on 16/6/19.
 */
public class SettingActivity extends BaseActivity {


    ImageView mImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        mImageView = (ImageView) findViewById(R.id.image_view);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
