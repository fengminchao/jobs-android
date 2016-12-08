package com.muxistudio.jobs.ui.find;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.muxistudio.jobs.App;
import com.muxistudio.jobs.Constant;
import com.muxistudio.jobs.ui.find.detail.CareerDetailActivity;
import com.muxistudio.jobs.ui.find.detail.EmployDetailActivity;
import com.muxistudio.jobs.ui.find.detail.FulltimeDetailAcitivity;
import com.muxistudio.jobs.util.Logger;
import com.muxistudio.jobs.R;
import com.muxistudio.jobs.bean.InfoData;
import com.muxistudio.jobs.ui.BaseFragment;
import com.muxistudio.jobs.ui.main.MainActivity;
import com.muxistudio.jobs.util.ToastUtil;
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
  private InfoAdapter mInfoAdapter;
  private List<InfoData> mInfoDataList;

  private int mPage = 1;

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
    ButterKnife.bind(this, view);
    mType = getArguments().getInt("type");
    Logger.d(mType + "");
    initRecyclerView();
    mPresenter.attachView(this);
    mPresenter.loadData(mType, mPage,true);
    mRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
    mRefreshLayout.setOnRefreshListener(() -> {
      mPage = 1;
      mPresenter.loadData(mType,1,true);
    });
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
    mInfoDataList = infoDatas;
    mInfoAdapter = new InfoAdapter(mInfoDataList, mType);
    mInfoAdapter.setOnItemClickListener(id -> {
      Logger.d(mType + "");
      switch (mType){
        case Constant.TYPE_XJH:
          CareerDetailActivity.start(getActivity(),id);
          break;
        case Constant.TYPE_ZP:
          EmployDetailActivity.start(getActivity(),id);
          break;
        case Constant.TYPE_XZ:
          FulltimeDetailAcitivity.start(getActivity(),id);
          break;
      }
    });
    mRecyclerView.setAdapter(mInfoAdapter);
    Logger.d(mType + " begin render list");
  }

  @Override public void renderMoreData(List<InfoData> infoDatas) {
    mInfoDataList.addAll(infoDatas);
    mInfoAdapter.notifyDataSetChanged();
  }

  @Override public void showEnd() {
    ToastUtil.showShort("已没有更多内容");
  }

  @Override public void showLoading() {
    if (!mRefreshLayout.isRefreshing()) {
      mRefreshLayout.post(() -> mRefreshLayout.setRefreshing(true));
    }
  }

  @Override public void hideLoading() {
    if (mRefreshLayout.isRefreshing()) {
      mRefreshLayout.post(() -> mRefreshLayout.setRefreshing(false));
    }
  }

  @Override public void enterDetailPage(int id, int type) {

  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    mPresenter.detachView();
  }

  private void initRecyclerView() {
    mRecyclerView.setHasFixedSize(true);
    mLayoutManager = new LinearLayoutManager(App.sContext);
    mRecyclerView.setLayoutManager(mLayoutManager);
    mRecyclerView.setOnScrollListener(getOnBottomListener(mLayoutManager));
    Logger.d("recyclerview init");
  }

  private RecyclerView.OnScrollListener getOnBottomListener(LinearLayoutManager layoutManager) {
    return new RecyclerView.OnScrollListener() {

      @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        boolean isBottom = layoutManager.findLastCompletelyVisibleItemPosition()
            >= mInfoAdapter.getItemCount() - 1;
        if (isBottom) {
          mPresenter.loadData(mType, ++mPage,false);
        }
      }
    };
  }
}
