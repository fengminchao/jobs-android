package com.muxistudio.jobs.ui.collection;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.muxistudio.jobs.Constant;
import com.muxistudio.jobs.R;
import com.muxistudio.jobs.bean.CollectionData;
import com.muxistudio.jobs.ui.BaseFragment;
import com.muxistudio.jobs.ui.find.detail.CareerDetailActivity;
import com.muxistudio.jobs.ui.find.detail.EmployDetailActivity;
import com.muxistudio.jobs.ui.find.detail.FulltimeDetailAcitivity;
import com.muxistudio.jobs.ui.main.MainComponent;
import com.muxistudio.jobs.util.ToastUtil;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by ybao on 16/12/5.
 */

public class CollectionFragment extends BaseFragment implements CollectionContract.View {

  @BindView(R.id.rv) RecyclerView mRv;

  @Inject CollectionPresenter mPresenter;

  private List<CollectionData> mCollections;
  private CollectionAdapter mAdapter;
  private CollectionData lastRmCollection;
  //上一次删除的collection位置
  private int lastRmCollectionPos;

  public static CollectionFragment newInstance() {
    CollectionFragment fragment = new CollectionFragment();
    return fragment;
  }

  @Override public void initInjector() {
    getComponent(MainComponent.class).inject(this);
  }

  @Override public int loadContentView() {
    return R.layout.fragment_collection;
  }

  @Override protected void initView(View view) {
    ButterKnife.bind(this,view);
    mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
    mRv.setHasFixedSize(true);
    new ItemTouchHelper(new ItemTouchHelper.Callback() {
      @Override
      public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return makeFlag(ItemTouchHelper.ACTION_STATE_SWIPE,
            ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
      }

      @Override public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
          RecyclerView.ViewHolder target) {
        return false;
      }

      @Override public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int pos = viewHolder.getAdapterPosition();
        lastRmCollection = mCollections.get(pos);
        lastRmCollectionPos = pos;
        mCollections.remove(pos);
        mAdapter.notifyItemRemoved(pos);
      }
    }).attachToRecyclerView(mRv);
    mPresenter.attachView(this);
  }

  @Override public void renderCollection(List<CollectionData> collections) {
    mCollections = collections;
    mAdapter = new CollectionAdapter(mCollections);
    mRv.setAdapter(mAdapter);
  }

  @Override public void showEmptyView() {
    // TODO: 16/12/6 add empty view
    ToastUtil.toastShort("empty");
  }

  @Override public void showError(String msg) {
    ToastUtil.toastShort(msg);
  }

  @Override public void showInfoDetailUi(int type, int id) {
    switch (type) {
      case Constant.TYPE_XJH:
        CareerDetailActivity.start(getActivity(), id);
        break;
      case Constant.TYPE_ZP:
        EmployDetailActivity.start(getActivity(), id);
        break;
      case Constant.TYPE_XZ:
        FulltimeDetailAcitivity.start(getActivity(), id);
        break;
    }
  }

  @Override public void insertItem(CollectionData collection) {
    if (lastRmCollectionPos < 0 || lastRmCollectionPos > mCollections.size()) {
      return;
    }
    mCollections.add(lastRmCollectionPos, collection);
    mAdapter.notifyItemInserted(lastRmCollectionPos);
  }

  @Override public void showSnackerbar(String msg) {
    Snackbar.make(mRv, msg, Snackbar.LENGTH_LONG).setAction("撤销", v -> {
      mPresenter.onUndoClick(lastRmCollection);
    }).show();
  }
}
