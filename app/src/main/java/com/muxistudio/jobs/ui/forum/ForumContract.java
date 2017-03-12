package com.muxistudio.jobs.ui.forum;

import com.muxistudio.jobs.bean.PostData;
import com.muxistudio.jobs.ui.BaseNormalView;
import com.muxistudio.jobs.ui.BasePresenter;

import java.util.List;

/**
 * Created by ybao on 17/1/2.
 */

public class ForumContract {

    interface View extends BaseNormalView {

        void renderPostList(List<PostData> postDatas, boolean clean);

        void showPostDetail(int pid);

        void showNewPostUi();

        void showRefreshView();

        void hideRefreshView();

    }

    interface Presenter extends BasePresenter<View> {

        void onPostItemClick(int position);

        void loadPostList();
    }
}
