package com.muxistudio.jobs.ui.find;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.muxistudio.jobs.R;
import com.muxistudio.jobs.ui.BaseFragment;

/**
 * Created by ybao on 16/11/8.
 */

public class InfoFragment extends BaseFragment{

  public static InfoFragment newInstance() {
     Bundle args = new Bundle();
     InfoFragment fragment = new InfoFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  public void refreshFragment(List<>)

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_info,container,false);
    return view;
  }



}
