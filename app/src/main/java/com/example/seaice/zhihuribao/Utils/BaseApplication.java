package com.example.seaice.zhihuribao.Utils;

import android.app.Application;
import android.os.Handler;

import org.litepal.LitePalApplication;

/**
 * Created by seaice on 2016/8/11.
 */
public class BaseApplication extends Application {
    private static BaseApplication application;

    private static int mainThreadId;
    private static Handler handler;

    @Override
    public void onCreate(){
        super.onCreate();
        application = this;
        mainThreadId = android.os.Process.myTid();
        handler = new Handler();
        LitePalApplication.initialize(this);
    }

    public static BaseApplication getApplication(){
        return application;
    }

    public static BaseApplication getContext(){
        return application;
    }

    public static int getMainThreadId(){
        return mainThreadId;
    }

    public static Handler getHandler(){
        return handler;
    }
}
