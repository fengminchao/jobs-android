package com.muxistudio.jobs.ui.find;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.muxistudio.jobs.Logger;
import com.muxistudio.jobs.R;
import com.muxistudio.jobs.ui.BaseFragment;
import com.muxistudio.jobs.ui.main.MainActivity;
import com.muxistudio.jobs.ui.main.MainComponent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ybao on 16/11/6.
 */

public class FindFragment extends BaseFragment {

  @BindView(R.id.tab_layout) TabLayout mTabLayout;
  @BindView(R.id.view_pager) ViewPager mViewPager;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_find, container, false);
    ButterKnife.bind(this, view);
    initView();
    return view;
  }

  private void initView() {
    setupViewPager();
  }

  private void setupViewPager() {
    List<String> titles = new ArrayList<>();
    titles.add("宣讲会");
    titles.add("招聘会");
    titles.add("网投");
    for (int i = 0; i < 3; i++) {
      mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(i)));
    }
    mTabLayout.setBackgroundColor(Color.WHITE);
    mTabLayout.setTabTextColors(Color.BLACK, getResources().getColor(R.color.colorAccent));
    List<Fragment> fragments = new ArrayList<>();
    fragments.add(InfoFragment.newInstance(1));
    fragments.add(InfoFragment.newInstance(2));
    fragments.add(InfoFragment.newInstance(3));
    FragmentAdapter adapter = new FragmentAdapter(getActivity().getSupportFragmentManager(), fragments, titles);
    mViewPager.setAdapter(adapter);
    mTabLayout.setupWithViewPager(mViewPager);
    Logger.d("setup fragments");
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    getComponent(MainComponent.class).inject((MainActivity) getActivity());
  }

  @Override public void onPrepareOptionsMenu(Menu menu) {
    super.onPrepareOptionsMenu(menu);
  }
}
