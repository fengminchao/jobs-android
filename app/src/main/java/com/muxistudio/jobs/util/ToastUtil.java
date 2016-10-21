package com.muxistudio.jobs.util;

import android.widget.Toast;

import com.muxistudio.jobs.App;

/**
 * Created by ybao on 16/10/21.
 */

public class ToastUtil {

    public static void toastLong(String msg){
        Toast.makeText(App.sContext,msg,Toast.LENGTH_LONG).show();
    }

    public static void toastShort(String msg){
        Toast.makeText(App.sContext,msg,Toast.LENGTH_SHORT).show();
    }
}