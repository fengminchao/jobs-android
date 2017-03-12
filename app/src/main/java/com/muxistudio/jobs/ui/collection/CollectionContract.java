package com.muxistudio.jobs.ui.collection;

import com.muxistudio.jobs.db.Collection;
import com.muxistudio.jobs.ui.BasePresenter;
import com.muxistudio.jobs.ui.BaseView;

import java.util.List;

/**
 * Created by ybao on 16/12/6.
 */

public class CollectionContract {

    interface View extends BaseView {

        void renderCollection(List<Collection> collections);

        void showEmptyView();

        void showError(String msg);

        void showInfoDetailUi(int type, int id);

        //void removeItem(int );

        void insertItem(Collection collection);

        void showSnackerbar(String msg);

    }

    interface Presenter extends BasePresenter<View> {

        void loadCollections();

        void onItemRemoved(Collection collection);

        void onUndoClick(Collection collection);
    }
}
