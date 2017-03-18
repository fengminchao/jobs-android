package com.muxistudio.jobs.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.muxistudio.jobs.App;

/**
 * Created by ybao (ybaovv@gmail.com)
 * Date: 17/3/18
 */

public class NetUtil {

    public static boolean isNetWorkAvailable(){
        ConnectivityManager connectivityManager = (ConnectivityManager) App.sContext.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }
}
