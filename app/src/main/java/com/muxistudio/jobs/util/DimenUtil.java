package com.muxistudio.jobs.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;
import com.muxistudio.jobs.App;
import java.lang.reflect.Field;

/**
 * Created by ybao on 16/12/12.
 */

public class DimenUtil {

  public static Context sContext = App.sContext;

  public static Display getDisplay() {
    WindowManager windowManager = (WindowManager) sContext.getSystemService(Context.WINDOW_SERVICE);
    return windowManager.getDefaultDisplay();
  }

  //获取屏幕的高度,已除去虚拟按键的高度
  public static int getScreenWidth() {
    return getDisplay().getWidth();
  }

  public static int getScreenHeight() {
    return getDisplay().getHeight();
  }


  //获取屏幕顶部状态栏高度
  public static int getStatusBarHeight() {
    Class<?> c = null;
    Object obj = null;
    Field field = null;
    int x = 0, statusBarHeight = 0;
    try {
      c = Class.forName("com.android.internal.R$dimen");
      obj = c.newInstance();
      field = c.getField("status_bar_height");
      x = Integer.parseInt(field.get(obj).toString());
      statusBarHeight = App.sContext.getResources().getDimensionPixelSize(x);
    } catch (Exception e1) {
      e1.printStackTrace();
    }
    return statusBarHeight;
  }

  //获取底部虚拟键盘的高度,若无则返回0
  public static int getNavigationBarHeight() {
    Resources resources = App.sContext.getResources();
    int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
    if (resourceId > 0) {
      return resources.getDimensionPixelSize(resourceId);
    }
    return 0;
  }

  //获取actionbar的高度
  public static int getActionbarHeight(){
    int actionBarHeight = 0;
    TypedValue tv = new TypedValue();
    if (App.sContext.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
    {
      actionBarHeight =
          TypedValue.complexToDimensionPixelSize(tv.data,App.sContext.getResources().getDisplayMetrics());
    }
    return actionBarHeight;
  }

  /**
   * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
   */
  public static int px2dp(float pxValue) {
    final float scale = App.sContext.getResources().getDisplayMetrics().density;
    return (int) (pxValue / scale + 0.5f);
  }

  public static int dp2px(int dpValue){
    final float scale = App.sContext.getResources().getDisplayMetrics().density;
    return (int) (dpValue * scale + 0.5f);
  }

  /**
   * 转化为全角
   * @param input
   * @return
   */
  public static String toDBC(String input) {
    char[] c = input.toCharArray();
    for (int i = 0; i< c.length; i++) {
      if (c[i] == 12288) {
        c[i] = (char) 32;
        continue;
      }if (c[i]> 65280&& c[i]< 65375)
        c[i] = (char) (c[i] - 65248);
    }
    return new String(c);
  }

}