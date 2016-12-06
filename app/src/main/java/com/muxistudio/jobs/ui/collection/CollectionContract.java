package com.muxistudio.jobs.ui.collection;

import com.muxistudio.jobs.bean.CollectionData;
import com.muxistudio.jobs.ui.BasePresenter;
import com.muxistudio.jobs.ui.BaseView;
import java.util.List;

/**
 * Created by ybao on 16/12/6.
 */

public class CollectionContract {

  interface View extends BaseView{

    void renderCollection(List<CollectionData> collections);

    void showEmptyView();

    void showError(String msg);

    void showInfoDetailUi(int type,int id);

    //void removeItem(int );

    void insertItem(CollectionData collection);

    void showSnackerbar(String msg);

  }

  interface Presenter extends BasePresenter<View>{

    void loadCollections();

    void onItemRemoved(CollectionData collection);

    void onUndoClick(CollectionData collection);
  }
}
