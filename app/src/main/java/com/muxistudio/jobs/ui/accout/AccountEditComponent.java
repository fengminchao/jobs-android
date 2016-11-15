package com.muxistudio.jobs.ui.accout;

import com.muxistudio.jobs.injector.PerActivity;
import com.muxistudio.jobs.injector.components.ApplicationComponent;
import dagger.Component;

/**
 * Created by ybao on 16/11/13.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class)
public interface AccountEditComponent {

  void inject(AccountEditActivity activity);
}
