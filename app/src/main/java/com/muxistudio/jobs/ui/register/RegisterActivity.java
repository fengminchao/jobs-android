package com.muxistudio.jobs.ui.register;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import com.muxistudio.jobs.R;
import com.muxistudio.jobs.ui.ToolbarActivity;

/**
 * Created by ybao on 16/10/22.
 */

public class RegisterActivity extends ToolbarActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initInjector() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
