package com.muxistudio.jobs.ui.accout;

import android.view.MenuItem;
import com.muxistudio.jobs.R;
import com.muxistudio.jobs.api.UserStorge;
import com.muxistudio.jobs.injector.PerActivity;
import com.muxistudio.jobs.injector.components.DaggerActivityComponent;
import com.muxistudio.jobs.ui.BaseActivity;
import javax.inject.Inject;

/**
 * Created by ybao on 16/11/12.
 */
@PerActivity
public class AccountActivity extends BaseActivity{

  @Inject UserStorge mUserStorge;

  @Override protected void initView() {
    //setContentView(R.layout);
  }

  @Override protected void initInjector() {
    //Daggeracc.builder()
    //    .applicationComponent(getApplicationComponent())
    //    .i
  }

  @Override protected void onDestroy() {
    super.onDestroy();
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    return super.onOptionsItemSelected(item);
  }
}
