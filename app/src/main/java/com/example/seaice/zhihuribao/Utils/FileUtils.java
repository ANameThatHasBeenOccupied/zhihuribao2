package com.example.seaice.zhihuribao.Utils;

import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * Created by seaice on 2016/8/11.
 */
public class FileUtils {
    private static final String TAG = "FileUtils";
    private static final String CACHE = "cache";
    private static final String ROOT = "zhihuribao";


    public static File getDir(String str){
        StringBuilder path = new StringBuilder();
        if(isSDAvailable()){
            path.append(Environment.getExternalStorageDirectory().getAbsolutePath());
            path.append(File.separator);
            path.append(ROOT);
            path.append(File.separator);
            path.append(str);
        }else{
            File fileDir = BaseApplication.getApplication().getCacheDir();
            path.append(fileDir.getAbsolutePath());
            path.append(File.separator);
            path.append(str);
        }
        Log.e(TAG, path.toString());
        File file = new File(path.toString());
        if(!file.exists() || !file.isDirectory()){
            file.mkdirs();
            LogUtils.i("make dirs");
        }
        return file;
    }

    public static File getCacheDir(){
        return getDir(CACHE);
    }

    private static boolean isSDAvailable() {
        return Environment.isExternalStorageEmulated();
    }
}
