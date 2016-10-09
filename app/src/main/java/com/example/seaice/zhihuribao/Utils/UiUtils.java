package com.example.seaice.zhihuribao.Utils;

import android.content.res.Resources;
import android.util.Log;
import android.widget.Toast;

import com.example.seaice.zhihuribao.R;

import java.util.Calendar;

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

    public static String getString(int str){
        return getResource().getString(str);
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

    public static String getDayOfWeek(int day) {
        String dayOfWeek = "";
        switch (day) {
            case Calendar.MONDAY:
                dayOfWeek = "一";
                break;
            case Calendar.TUESDAY:
                dayOfWeek = "二";
                break;
            case Calendar.WEDNESDAY:
                dayOfWeek = "三";
                break;
            case Calendar.THURSDAY:
                dayOfWeek = "四";
                break;
            case Calendar.FRIDAY:
                dayOfWeek = "五";
                break;
            case Calendar.SATURDAY:
                dayOfWeek = "六";
                break;
            case Calendar.SUNDAY:
                dayOfWeek = "日";
                break;
            default:
                break;

        }
        return dayOfWeek;
    }

    public static void showToast(String str){
        Toast.makeText(BaseApplication.getApplication(), str, Toast.LENGTH_SHORT).show();
    }
}
