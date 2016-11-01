package com.muxistudio.jobs.util;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.muxistudio.jobs.App;

/**
 * Created by ybao on 16/11/1.
 */

public class PreferUtil {

    public static final String IS_NIGHT_THEME = "is_night_theme";

    public static void putInt(String key,int value){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(App.sContext);
        sp.edit().putInt(key,value).apply();
    }

    public static int getInt(String key){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(App.sContext);
        return sp.getInt(key,0);
    }

    public static void putString(String key,String value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(App.sContext);
        sp.edit().putString(key,value).apply();
    }

    public static void putBoolean(String key,boolean b){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(App.sContext);
        sp.edit().putBoolean(key,b).apply();
    }

    public static boolean getBoolean(String key){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(App.sContext);
        return sp.getBoolean(key,false);
    }
}
