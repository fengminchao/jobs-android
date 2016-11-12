package com.muxistudio.jobs.ui.find;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.muxistudio.jobs.App;
import com.muxistudio.jobs.Logger;
import com.muxistudio.jobs.R;
import com.muxistudio.jobs.bean.InfoData;
import com.muxistudio.jobs.ui.BaseFragment;
import com.muxistudio.jobs.ui.main.MainActivity;
import com.muxistudio.jobs.ui.main.MainComponent;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by ybao on 16/11/8.
 */

public class InfoFragment extends BaseFragment implements InfoContract.View {

  @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
  @BindView(R.id.refresh_layout) SwipeRefreshLayout mRefreshLayout;
  //类型为宣讲会，招聘会，或网投
  private int mType;
  private static final String TYPE = "type";


  private LinearLayoutManager mLayoutManager;

  @Inject InfoPresenter mPresenter;

  public static InfoFragment newInstance(int type) {
    Bundle args = new Bundle();
    InfoFragment fragment = new InfoFragment();
    args.putInt(TYPE, type);
    fragment.setArguments(args);
    return fragment;
  }

  @Override protected void initView(View view) {
    ButterKnife.bind(this,view);
    mType = getArguments().getInt("type");
    initRecyclerView();
    mPresenter.attachView(this);
    mPresenter.getInfoDataList(mType);
  }

  @Override public void initInjector() {
        DaggerInfoComponent.builder()
        .applicationComponent(((MainActivity) getActivity()).getApplicationComponent())
        .build()
        .inject(this);
  }

  @Override public int loadContentView() {
    return R.layout.fragment_info;
  }

  @Override public void renderInfoList(List<InfoData> infoDatas) {
    InfoAdapter adapter = new InfoAdapter(infoDatas, mType);
    mRecyclerView.setAdapter(adapter);
    Logger.d(mType + " begin render list");
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    mPresenter.detachView();
  }

  private void initRecyclerView() {
    mRecyclerView.setHasFixedSize(true);
    mLayoutManager = new LinearLayoutManager(App.sContext);
    mRecyclerView.setLayoutManager(mLayoutManager);
    Logger.d("recyclerview init");
  }
}
