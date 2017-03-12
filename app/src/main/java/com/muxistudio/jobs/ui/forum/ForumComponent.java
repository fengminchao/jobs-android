package com.muxistudio.jobs.ui.forum;

import com.muxistudio.jobs.injector.PerActivity;
import com.muxistudio.jobs.injector.components.ApplicationComponent;

import dagger.Component;

/**
 * Created by ybao on 17/1/3.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class)
public interface ForumComponent {

    void inject(NewPostActivity newPostActivity);

    void inject(PostDetailActivity postDetailActivity);
}
