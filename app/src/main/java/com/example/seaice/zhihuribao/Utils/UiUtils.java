package com.example.seaice.zhihuribao.Utils;

import android.content.res.Resources;
import android.util.Log;

/**
 * Created by seaice on 2016/8/12.
 */
public class UiUtils {
    private static final String TAG = "UiUtils";

    public static Resources getResource() {
        return BaseApplication.getApplication().getResources();
    }

    /**
     * 获取字符串数组
     *
     * @param tabNames
     * @return
     */
    public static String[] getStringArray(int tabNames) {
        return getResource().getStringArray(tabNames);
    }

    public static void runOnUiThread(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        //证明在主线程
        if (android.os.Process.myTid() == BaseApplication.getMainThreadId()) {
            Log.e(TAG, "主线程");
            runnable.run();
        } else {
            Log.e(TAG, "非主线程");
            BaseApplication.getHandler().post(runnable);
        }
    }

}
