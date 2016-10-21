package com.muxistudio.jobs.ui.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;

import com.muxistudio.jobs.App;
import com.muxistudio.jobs.R;
import com.muxistudio.jobs.inject.PerActivity;
import com.muxistudio.jobs.ui.BaseActivity;

/**
 * Created by ybao on 16/10/19.
 */
@PerActivity
public class LoginActivity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DaggerLoginComponent.builder()
                .applicationComponent(((App) getApplication()).geta)
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
