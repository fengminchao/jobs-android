package com.muxistudio.jobs.ui.forum;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.muxistudio.jobs.R;
import com.muxistudio.jobs.api.UserStorge;
import com.muxistudio.jobs.api.user.UserApi;
import com.muxistudio.jobs.bean.PostData;
import com.muxistudio.jobs.ui.BaseFragment;
import com.muxistudio.jobs.ui.main.MainActivity;
import com.muxistudio.jobs.util.ToastUtil;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ybao on 17/1/1.
 */

public class ForumFragment extends BaseFragment {

  @Inject UserApi mUserApi;
  @Inject UserStorge mUserStorge;

  private PostListAdapter mPostListAdapter;
  private List<PostData> mPostDataList;

  @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
  @BindView(R.id.refresh_layout) SwipeRefreshLayout mRefreshLayout;
  @BindView(R.id.fab) FloatingActionButton mFab;

  @Override public void initInjector() {
    ((MainActivity) getActivity()).getComponent().inject(this);
  }

  @Override public int loadContentView() {
    return R.layout.fragment_forum;
  }

  @Override public void onStart() {
    super.onStart();
    loadData();
  }

  public void loadData() {
    mUserApi.getUserService()
        .getPostCollections(mUserStorge.getToken())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(postListResult -> {
          mPostDataList = postListResult.data;
          mPostListAdapter.notifyDataSetChanged();
        }, throwable -> {
          throwable.printStackTrace();
          ToastUtil.showShort("服务器异常");
        });
  }

  @Override protected void initView(View view) {
    ButterKnife.bind(this, view);
    setupRecyclerView();
    mFab.setOnClickListener(v -> {
      showNewPostUi();
    });
  }

  public void setupRecyclerView() {
    mPostDataList = new ArrayList<>();
    mPostListAdapter = new PostListAdapter(mPostDataList);
    mPostListAdapter.setOnItemClickListener(pid -> {
      showPostDetailUi(pid);
    });
    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    mRecyclerView.setAdapter(mPostListAdapter);
  }

  private void showPostDetailUi(int pid) {
    PostDetailActivity.start(getActivity(),pid);
  }

  private void showNewPostUi() {
    NewPostActivity.start(getActivity(),true);
  }
}
