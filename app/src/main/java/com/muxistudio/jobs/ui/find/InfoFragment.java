package com.muxistudio.jobs.ui.find;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.muxistudio.jobs.App;
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

  @Inject InfoPresenter mPresenter;

  public static InfoFragment newInstance(int type) {
    Bundle args = new Bundle();
    InfoFragment fragment = new InfoFragment();
    args.putInt(TYPE, type);
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    //DaggerInfoComponent.builder().applicationComponent()

  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mType = getArguments().getInt(TYPE);
    getComponent(MainComponent.class).inject((MainActivity) getActivity());
    mPresenter.attachView(this);
  }

  @Override public void renderInfoList(List<InfoData> infoDatas) {
    mRecyclerView.setHasFixedSize(true);
    InfoAdapter adapter = new InfoAdapter(infoDatas,mType);
    mRecyclerView.setAdapter(adapter);
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    mPresenter.detachView();
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_info, container, false);
    ButterKnife.bind(this, view);
    return view;
  }

}
