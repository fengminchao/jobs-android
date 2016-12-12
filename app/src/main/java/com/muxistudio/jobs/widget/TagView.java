package com.muxistudio.jobs.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.TextView;

/**
 * Created by ybao on 16/12/12.
 */

public class TagView extends TextView implements Checkable {

  private boolean isChecked;
  private static final int[] CHECKED_STATE = new int[]{android.R.attr.state_checked};

  public TagView(Context context) {
    super(context);
  }

  public TagView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override protected int[] onCreateDrawableState(int extraSpace) {
    int[] states = super.onCreateDrawableState(extraSpace + 1);
    if (isChecked()){
      mergeDrawableStates(states,CHECKED_STATE);
    }
    return states;
  }

  @Override public void setChecked(boolean b) {
    if (isChecked != b){
      isChecked = b;
      refreshDrawableState();
    }
  }

  @Override public boolean isChecked() {
    return isChecked;
  }

  @Override public void toggle() {
    setChecked(!isChecked);
  }
}