package com.muxistudio.jobs.ui.find;

import com.muxistudio.jobs.injector.PerActivity;
import com.muxistudio.jobs.injector.components.ApplicationComponent;

import dagger.Component;

/**
 * Created by ybao on 16/11/12.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class)
public interface FindComponent {

}
