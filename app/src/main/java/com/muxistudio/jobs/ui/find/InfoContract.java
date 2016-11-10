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

  }

  interface Presenter extends BasePresenter<View>{

    void getInfoDataList(int type);

    void onItemClick(int id);


  }
}
