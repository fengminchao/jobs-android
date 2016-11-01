package com.muxistudio.jobs.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.muxistudio.jobs.R;
import com.muxistudio.jobs.ui.setting.SettingActiviy;

/**
 * Created by ybao on 16/11/1.
 */

public class SplashActivity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(() -> {
            startActivity(new Intent(this, SettingActiviy.class));
        },1000);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_splash);

    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
