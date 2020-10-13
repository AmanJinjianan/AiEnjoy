package com.aman.aienjoy.ui.main.utils;

import android.util.Log;

/**
 * @Describe：Log日志级别打印相关工具类
 */
public class LogUtil {

    private LogUtil(){}

    /**
     * 打印的信息日志信息
     */
    private final static String INFO = "☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻: ";

    /**
     * 打印的错误日志信息
     */
    private final static String ERROR = "✖✖✖✖✖✖✖✖✖✖✖✖✖✖✖✖✖✖✖✖✖✖✖✖✖✖: ";

    /**
     * 打印的调试日志信息
     */
    private final static String DEBUG = "☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠: ";

    /**
     * 打印的全面日志信息
     */
    private final static String VERBOSE = "▂▂▂▃▃▄▄▅▅▆▆▆▇▇: ";

    /**
     * 打印的警告日志信息
     */
    private final static String WARN = "!!!!!!!!!!!!!!!!!!!!!!!!!!: ";

    /**
     * 打印information日志
     * @param tag 标签
     * @param msg 日志信息
     */
    public static void i(String tag, String msg){
        Log.i(tag, INFO + msg);
    }

    /**
     * 打印information日志
     * @param tag    标签
     * @param msg    日志信息
     * @param throwable    异常
     */
    public static void i(String tag, String msg, Throwable throwable){
        Log.i(tag, INFO + msg, throwable);
    }

    /**
     * 打印verbose日志
     * @param tag    标签
     * @param msg    日志信息
     */
    public static void v(String tag, String msg){
        Log.v(tag, VERBOSE + msg);
    }

    /**
     * 打印verbose日志
     * @param tag    标签
     * @param msg    日志信息
     * @param throwable    异常
     */
    public static void v(String tag, String msg, Throwable throwable){
        Log.v(tag, VERBOSE + msg, throwable);
    }

    /**
     * 打印debug信息
     * @param tag    标签信息
     * @param msg    日志信息
     */
    public static void d(String tag, String msg){
        Log.d(tag, DEBUG + msg);
    }

    /**
     * 打印debug日志
     * @param tag    标签信息
     * @param msg    日志信息
     * @param throwable    异常
     */
    public static void d(String tag, String msg, Throwable throwable){
        Log.d(tag, DEBUG + msg, throwable);
    }

    /**
     * 打印warn日志
     * @param tag    标签信息
     * @param msg    日志信息
     */
    public static void w(String tag, String msg){
        Log.w(tag, WARN + msg);
    }

    /**
     * 打印warn日志
     * @param tag    标签信息
     * @param msg    日志信息
     * @param throwable    异常
     */
    public static void w(String tag, String msg, Throwable throwable){
        Log.w(tag, WARN + msg, throwable);
    }

    /**
     * 打印error日志
     * @param tag
     * @param msg    标签
     */
    public static void e(String tag, String msg){
        Log.e(tag, ERROR + msg);
    }

    /**
     * 打印error日志
     * @param tag    标签
     * @param msg    日志信息
     * @param throwable    异常
     */
    public static void e(String tag, String msg, Throwable throwable){
        Log.e(tag, ERROR + msg, throwable);
    }

}
