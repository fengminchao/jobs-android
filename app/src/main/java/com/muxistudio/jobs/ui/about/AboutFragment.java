package com.muxistudio.jobs.ui.about;

import android.view.View;
import com.muxistudio.jobs.R;
import com.muxistudio.jobs.ui.BaseFragment;
import com.muxistudio.jobs.ui.main.MainActivity;

/**
 * Created by ybao on 16/12/20.
 */

public class AboutFragment extends BaseFragment{

  @Override public void initInjector() {
    ((MainActivity)getActivity()).getComponent().inject(this);
  }

  @Override public int loadContentView() {
    return R.layout.fragment_about;
  }

  @Override protected void initView(View view) {

  }
}
