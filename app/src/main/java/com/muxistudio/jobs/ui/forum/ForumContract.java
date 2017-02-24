package com.muxistudio.jobs.ui.forum;

import com.muxistudio.jobs.bean.PostData;
import com.muxistudio.jobs.bean.PostListResult;
import com.muxistudio.jobs.ui.BasePresenter;
import com.muxistudio.jobs.ui.BaseView;
import com.qiniu.android.http.PostArgs;
import java.util.List;

/**
 * Created by ybao on 17/1/2.
 */

public class ForumContract {

  interface View extends BaseView {

    void renderPostList(List<PostData> postDatas,boolean clean);

    void showPostDetail(int pid);

    void showNewPostUi();

  }

  interface Presenter extends BasePresenter<View>{



  }
}
