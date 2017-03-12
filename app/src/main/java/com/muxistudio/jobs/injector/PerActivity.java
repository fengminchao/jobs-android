package com.muxistudio.jobs.injector;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

import javax.inject.Scope;

/**
 * Created by ybao on 16/10/16.
 */
@Scope
@Retention(RUNTIME)
public @interface PerActivity {
}
