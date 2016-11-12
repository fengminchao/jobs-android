//package com.muxistudio.jobs.ui.career;
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import com.muxistudio.jobs.R;
//import com.muxistudio.jobs.ui.BaseFragment;
//
///**
// * Created by ybao on 16/11/9.
// */
//
//public class CareerFragment extends BaseFragment {
//
//  @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
//  @BindView(R.id.refresh_layout) SwipeRefreshLayout mRefreshLayout;
//
//  @Nullable @Override
//  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
//      @Nullable Bundle savedInstanceState) {
//    View view = inflater.inflate(R.layout.fragment_info, container, false);
//    ButterKnife.bind(this, view);
//    return view;
//  }
//
//  public void initRecyclerView(){
//    mRecyclerView.setHasFixedSize(true);
//
//  }
//}
