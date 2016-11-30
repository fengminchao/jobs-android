package com.muxistudio.jobs.ui;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import butterknife.ButterKnife;
import com.muxistudio.jobs.R;

/**
 * Created by ybao on 16/10/21.
 */

public abstract class ToolbarActivity extends BaseActivity {

    private Toolbar mtoolbar;

    @Override public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        initToolbar();
    }

    protected void initToolbar() {
        mtoolbar = ButterKnife.findById(this, R.id.toolbar);
        if (mtoolbar != null) {
            mtoolbar.setTitle(getString(R.string.app_name));
            setSupportActionBar(mtoolbar);
            if (canBack()) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            this.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
