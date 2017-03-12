package com.muxistudio.jobs.util;

import com.muxistudio.jobs.App;
import com.muxistudio.jobs.Constant;
import com.muxistudio.jobs.R;

/**
 * Created by ybao on 16/12/7.
 * 招聘类型转换工具类
 */

public class TypeUtil {

    public static String toStringType(int type) {
        switch (type) {
            case Constant.TYPE_XJH:
                return App.sContext.getString(R.string.find_career);
            case Constant.TYPE_ZP:
                return App.sContext.getString(R.string.find_employ);
            case Constant.TYPE_XZ:
                return App.sContext.getString(R.string.find_fulltime);
        }
        return "";
    }

    public static int toIntType(String type) {
        if (type.equals(App.sContext.getString(R.string.find_career))) {
            return Constant.TYPE_XJH;
        }
        if (type.equals(App.sContext.getString(R.string.find_employ))) {
            return Constant.TYPE_ZP;
        }
        if (type.equals(App.sContext.getString(R.string.find_fulltime))) {
            return Constant.TYPE_XZ;
        }
        return -1;
    }
}
