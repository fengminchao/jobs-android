package com.muxistudio.jobs.ui;

import android.support.v7.widget.Toolbar;

/**
 * Created by ybao on 16/10/21.
 */

public abstract class ToolbarActivity extends BaseActivity {

    private Toolbar mtoolbar;

    protected void initToolbar(Toolbar toolbar) {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            mtoolbar = toolbar;
            if (canBack()) {
                getSupportActionBar().setDisplayShowHomeEnabled(true);
            }
        }
    }

    public void setTitle(CharSequence c) {
        if (mtoolbar != null){
            mtoolbar.setTitle(c);
        }
    }

    protected boolean canBack() {
        return true;
    }
}
