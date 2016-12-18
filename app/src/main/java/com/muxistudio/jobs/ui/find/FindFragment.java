package com.muxistudio.jobs.ui.find;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.muxistudio.jobs.util.Logger;
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

  private List<Fragment> mFragmentList;

  public static FindFragment newInstance() {
    Bundle args = new Bundle();
    FindFragment fragment = new FindFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public int loadContentView() {
    return R.layout.fragment_find;
  }

  @Override protected void initView(View view) {
    ButterKnife.bind(this, view);
    setupViewPager();
  }

  @Override public void initInjector() {
    DaggerFindComponent.builder()
        .applicationComponent(((MainActivity) getActivity()).getApplicationComponent())
        .build();
  }

  private void setupViewPager() {
    List<String> titleList = new ArrayList<>();
    titleList.add("宣讲会");
    titleList.add("招聘会");
    titleList.add("网投");
    for (int i = 0; i < 3; i++) {
      mTabLayout.addTab(mTabLayout.newTab().setText(titleList.get(i)));
    }
    mTabLayout.setBackgroundColor(Color.WHITE);
    mTabLayout.setTabTextColors(Color.BLACK, getResources().getColor(R.color.colorAccent));
    mFragmentList = new ArrayList<>();
    mFragmentList.add(InfoFragment.newInstance(1));
    mFragmentList.add(InfoFragment.newInstance(2));
    mFragmentList.add(InfoFragment.newInstance(3));
    FragmentAdapter adapter =
        new FragmentAdapter(getChildFragmentManager(), mFragmentList, titleList);
    mViewPager.setAdapter(adapter);
    mViewPager.setOffscreenPageLimit(2);
    mTabLayout.setupWithViewPager(mViewPager);
    Logger.d("setup fragments");
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    getComponent(MainComponent.class).inject((MainActivity) getActivity());
  }

  public void loadQuery(String query){
    for (Fragment fragment : mFragmentList){
      ((InfoFragment) fragment).onSearchQuery(query);
    }
  }

  @Override public void onPrepareOptionsMenu(Menu menu) {
    super.onPrepareOptionsMenu(menu);
  }

  @Override public void onDetach() {
    super.onDetach();
    //Logger.d("findfragment is detach");
    //mViewPager.setAdapter(null);
    //mViewPager.setOffscreenPageLimit(1);
    //mTabLayout.removeAllTabs();
  }
}
