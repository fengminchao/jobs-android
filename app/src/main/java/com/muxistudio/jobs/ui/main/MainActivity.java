package com.muxistudio.jobs.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import com.muxistudio.jobs.R;
import com.muxistudio.jobs.ui.BaseActivity;

/**
 * Created by ybao on 16/10/21.
 */

public class MainActivity extends BaseActivity{

    public static void startActivity(Context context){
        Intent intent = new Intent(context,MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
