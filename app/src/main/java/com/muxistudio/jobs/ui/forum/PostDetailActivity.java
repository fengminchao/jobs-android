package com.muxistudio.jobs.ui.forum;

import android.content.Context;
import android.content.Intent;

import com.muxistudio.jobs.api.user.UserApi;
import com.muxistudio.jobs.injector.PerActivity;
import com.muxistudio.jobs.ui.ToolbarActivity;

import javax.inject.Inject;

/**
 * Created by ybao on 17/1/3.
 */

public class PostDetailActivity extends ToolbarActivity {

    @Inject
    UserApi mUserApi;

    public static void start(Context context, int pid) {
        Intent starter = new Intent(context, PostDetailActivity.class);
        starter.putExtra("pid", pid);
        context.startActivity(starter);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initInjector() {

    }
}
