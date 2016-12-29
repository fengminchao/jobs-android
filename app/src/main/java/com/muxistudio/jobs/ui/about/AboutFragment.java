package com.muxistudio.jobs.ui.about;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.muxistudio.jobs.BuildConfig;
import com.muxistudio.jobs.R;
import com.muxistudio.jobs.ui.BaseFragment;
import com.muxistudio.jobs.ui.main.MainActivity;

/**
 * Created by ybao on 16/12/20.
 */

public class AboutFragment extends BaseFragment {

  @BindView(R.id.iv_logo) ImageView mIvLogo;
  @BindView(R.id.tv_version) TextView mTvVersion;
  @BindView(R.id.tv_team) TextView mTvTeam;

  public static AboutFragment newInstance() {
    Bundle args = new Bundle();
    AboutFragment fragment = new AboutFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void initInjector() {
    ((MainActivity) getActivity()).getComponent().inject(this);
  }

  @Override public int loadContentView() {
    return R.layout.fragment_about;
  }

  @Override protected void initView(View view) {
    ButterKnife.bind(this,view);
    mTvVersion.setText("Version:" + BuildConfig.VERSION_NAME);
  }

}
