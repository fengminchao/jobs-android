package com.muxistudio.jobs.inject;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by ybao on 16/10/16.
 */
@Scope
@Retention(RUNTIME)
public @interface PreActivity {
}
