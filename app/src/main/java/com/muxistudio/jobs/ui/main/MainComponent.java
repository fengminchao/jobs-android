package com.muxistudio.jobs.ui.main;

import com.muxistudio.jobs.injector.PerActivity;
import com.muxistudio.jobs.injector.components.ApplicationComponent;
import dagger.Component;
import dagger.Module;

/**
 * Created by ybao on 16/11/8.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = MainModule.class)
public interface MainComponent {

  void inject(MainActivity mainActivity);

}
