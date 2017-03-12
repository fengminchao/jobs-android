package com.muxistudio.jobs.ui.web;

import android.content.Context;
import android.content.Intent;

import com.muxistudio.jobs.ui.ToolbarActivity;

/**
 * Created by ybao on 16/11/29.
 */

public class WebActivity extends ToolbarActivity {

    public static void start(Context context, String url) {
        Intent starter = new Intent(context, WebActivity.class);
        starter.putExtra("url", url);
        context.startActivity(starter);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initInjector() {

    }
}
