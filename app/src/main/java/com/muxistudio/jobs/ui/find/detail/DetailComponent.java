package com.muxistudio.jobs.ui.find.detail;

import com.muxistudio.jobs.bean.CareerDetail;
import com.muxistudio.jobs.injector.PerActivity;
import com.muxistudio.jobs.injector.components.ApplicationComponent;
import com.muxistudio.jobs.ui.BaseActivity;
import dagger.Component;

/**
 * Created by ybao on 16/11/29.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class)
public interface DetailComponent {

  //void inject(BaseActivity baseActivity);

  void inject(CareerDetailActivity careerDetailActivity);

  void inject(EmployDetailActivity employDetailActivity);

  void inject(FulltimeDetailAcitivity fulltimeDetailAcitivity);
}
