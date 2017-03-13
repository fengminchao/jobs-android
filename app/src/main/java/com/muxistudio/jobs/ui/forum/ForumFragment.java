package com.muxistudio.jobs.ui.forum;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.muxistudio.jobs.R;
import com.muxistudio.jobs.bean.PostData;
import com.muxistudio.jobs.ui.BaseFragment;
import com.muxistudio.jobs.ui.main.MainActivity;
import com.muxistudio.jobs.util.Logger;
import com.muxistudio.jobs.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ybao on 17/1/1.
 */

public class ForumFragment extends BaseFragment implements ForumContract.View {

    @Inject
    ForumPresenter mForumPresenter;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    private PostListAdapter mPostListAdapter;
    private List<PostData> mPostDataList;

    public static ForumFragment newInstance() {
        ForumFragment fragment = new ForumFragment();
        return fragment;
    }

    @Override
    public void initInjector() {
        ((MainActivity) getActivity()).getComponent().inject(this);
    }

    @Override
    public int loadContentView() {
        return R.layout.fragment_forum;
    }

    @Override
    public void onResume() {
        super.onResume();
        mForumPresenter.loadPostList();
        Logger.d("onresume");
    }

    @Override
    public void renderPostList(List<PostData> postDatas, boolean clean) {
        if (clean) {
            mPostDataList.clear();
            mPostDataList.addAll(postDatas);
        } else {
            mPostDataList.addAll(postDatas);
        }
        mPostListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showPostDetail(int pid) {
        PostDetailActivity.start(getContext(), pid);
    }

    @Override
    public void showError(String errorMsg) {
        ToastUtil.showShort(errorMsg);
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
        setupRecyclerView();
        mFab.setOnClickListener(v -> {
            showNewPostUi();
        });
        mRefreshLayout.setOnRefreshListener(() -> mForumPresenter.loadPostList());
        mForumPresenter.attachView(this);
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
        PostDetailActivity.start(getActivity(), pid);
    }

    public void showNewPostUi() {
        EditPostActivity.start(getActivity(), true,null,-1);
    }

    @Override
    public void showRefreshView() {
        if (!mRefreshLayout.isRefreshing()) {
            mRefreshLayout.post(() -> mRefreshLayout.setRefreshing(true));
        }
    }

    @Override
    public void hideRefreshView() {
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.post(() -> mRefreshLayout.setRefreshing(false));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mForumPresenter.detachView();
    }
}
