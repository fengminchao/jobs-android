package com.muxistudio.jobs.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.muxistudio.jobs.App;
import com.muxistudio.jobs.injector.components.ApplicationComponent;
import com.muxistudio.jobs.injector.modules.ActivityModule;

/**
 * Created by ybao on 16/10/16.
 */

public abstract class BaseActivity extends AppCompatActivity{

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getApplicationComponent().inject(this);
        super.onCreate(savedInstanceState);
        initInjector();
        initView();
        mProgressDialog = new ProgressDialog(this);
    }

    protected abstract void initView();

    protected abstract void initInjector();


    public ApplicationComponent getApplicationComponent(){
        return ((App) getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule(){
        return new ActivityModule(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected void showLoadingDialog(String msg){
        if (! mProgressDialog.isShowing()) {
            mProgressDialog.show(this,null,msg);
        }
    }

    protected void hideLoadingDialog(){
        if (mProgressDialog.isShowing()){
            mProgressDialog.hide();
        }
    }

}
