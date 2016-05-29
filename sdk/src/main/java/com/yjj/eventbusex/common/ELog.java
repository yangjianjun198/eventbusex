package com.yjj.eventbusex.common;

import android.util.Log;

/**
 * Created by jianjunyjj on 16/5/28.
 */
public class ELog {
    private static boolean DEBUG = true;

    public static void d(String tag, String format,Object ...args) {

        if (DEBUG) {
            Log.d(tag, String.format(format,args));
        }
    }

    public static void e(String tag, String format,Object ...args) {

        if (DEBUG) {
            Log.e(tag, String.format(format,args));
        }
    }

    public static void i(String tag, String format,Object ...args) {

        if (DEBUG) {
            Log.i(tag, String.format(format,args));
        }
    }

    public static void v(String tag, String format,Object ...args) {

        if (DEBUG) {
            Log.v(tag, String.format(format,args));
        }
    }

}
