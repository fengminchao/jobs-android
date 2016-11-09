package com.muxistudio.jobs.ui.find;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.muxistudio.jobs.ui.BaseFragment;

/**
 * Created by ybao on 16/11/9.
 */

public class EmployFragment extends BaseFragment {

  public static EmployFragment newInstance() {
     Bundle args = new Bundle();
     EmployFragment fragment = new EmployFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return super.onCreateView(inflater, container, savedInstanceState);
  }
}
