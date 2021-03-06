package com.muxistudio.jobs.ui.main;

import com.muxistudio.jobs.injector.PerActivity;
import com.muxistudio.jobs.injector.components.ApplicationComponent;
import com.muxistudio.jobs.ui.about.AboutFragment;
import com.muxistudio.jobs.ui.collection.CollectionFragment;
import com.muxistudio.jobs.ui.forum.ForumFragment;
import com.muxistudio.jobs.ui.schedule.ScheduleFragment;
import com.muxistudio.jobs.ui.setting.SettingFragment;

import dagger.Component;

/**
 * Created by ybao on 16/11/8.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = MainModule.class)
public interface MainComponent {

    void inject(MainActivity mainActivity);

    void inject(CollectionFragment collectionFragment);

    void inject(ScheduleFragment scheduleFragment);

    void inject(AboutFragment aboutFragment);

    void inject(ForumFragment forumFragment);

    void inject(SettingFragment settingFragment);

}
