package cn.stu.ruiz.myapplication.utils;

import android.util.Log;

public class LogUtil {

    private volatile static int CURRENT_LOG_LEVEL = 0;

    private final static int VERBOSE = 1;
    private final static int DEBUG = 2;
    private final static int INFO = 3;
    private final static int WARN = 4;
    private final static int ERROR = 5;

    public static void e(String tag, String msg, Throwable exception) {
        if (CURRENT_LOG_LEVEL >= ERROR)
            Log.e(tag, msg, exception);
    }


    public static void v(String tag, String msg, Throwable exception) {
        if (CURRENT_LOG_LEVEL >= VERBOSE)
            Log.v(tag, msg,exception);
    }


    /**
     * 打开各级别日志
     */
    public static void setCurrentLogLevel(int currentLogLevel) {
        CURRENT_LOG_LEVEL = currentLogLevel;
    }
}
