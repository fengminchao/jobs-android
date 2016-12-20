package com.muxistudio.jobs.ui.main;

import com.muxistudio.jobs.injector.PerActivity;
import com.muxistudio.jobs.injector.components.ApplicationComponent;
import com.muxistudio.jobs.ui.about.AboutFragment;
import com.muxistudio.jobs.ui.collection.CollectionFragment;
import com.muxistudio.jobs.ui.schedule.ScheduleFragment;
import dagger.Component;
import dagger.Module;

/**
 * Created by ybao on 16/11/8.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = MainModule.class)
public interface MainComponent {

  void inject(MainActivity mainActivity);

  void inject(CollectionFragment collectionFragment);

  void inject(ScheduleFragment scheduleFragment);

  void inject(AboutFragment aboutFragment);
}
