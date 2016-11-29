package com.muxistudio.jobs.ui.find;

import com.muxistudio.jobs.bean.InfoData;
import com.muxistudio.jobs.ui.BasePresenter;
import com.muxistudio.jobs.ui.BaseView;
import java.util.List;

/**
 * Created by ybao on 16/11/10.
 */

public class InfoContract{

  interface View extends BaseView{

    void renderInfoList(List<InfoData> infoDatas);

    void renderMoreData(List<InfoData> infoDatas);

    void showEnd();

    void showLoading();

    void hideLoading();

    void enterDetailPage(int id,int type);
  }

  interface Presenter extends BasePresenter<View>{

    void onItemClick(int id);

    void loadData(int page,int type,boolean clean);

  }
}
