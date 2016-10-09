package com.example.seaice.zhihuribao.protocol;

import android.util.Log;

import com.example.seaice.zhihuribao.Utils.FileUtils;
import com.example.seaice.zhihuribao.Utils.HttpUtils;
import com.example.seaice.zhihuribao.Utils.LogUtils;
import com.example.seaice.zhihuribao.bean.GuideInfo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by seaice on 2016/8/18.
 */
public abstract class BaseProtocol<T> {

    //解析json数据
    protected abstract T paserJsonData(String json);
    //获取连接路径
    protected abstract String getCacheDir();
    //获取新闻链接网址
    protected abstract String getUrl();

    /**
     * 1.先从本地获取json数据
     * 2.如果为空，再从服务器上获取
     * 3.若获取到json,保存到本地
     * 4.获取到json不为空，解析json数据
     */
    public T loadData() {
        LogUtils.e("protocol load data!!");
        String json = loadDataFromLocal();
        if (json == null) {
            LogUtils.e("从服务器获取json");
            json = loadDataFromServer();
            if (json != null) {
                saveDataToLocal(json);
            }
        } else {
            LogUtils.e("已经从本地获取");
        }
        if (json != null) {
            return paserJsonData(json);
        }
        return null;
    }

    //1.把整个json文件写到一个本地文件中
    //2.把每条数据摘出来保存到数据库中
    private String loadDataFromLocal() {
        File dir = FileUtils.getCacheDir();
        File file = new File(dir, getCacheDir());
        LogUtils.e("loadDataFromLocal = " + file.getAbsolutePath().toString());
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            long saveTime = Long.parseLong(br.readLine());
            if (System.currentTimeMillis() > saveTime) {
                return null;
            } else {
                String str;
                StringWriter sw = new StringWriter();
                while ((str = br.readLine()) != null) {
                    sw.write(str);
                }
                LogUtils.i("本地获取数据");
                return sw.toString();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    //把数据保存到本地
    private void saveDataToLocal(String json) {
        //第一行写时间，然后保存起来
        File dir = FileUtils.getCacheDir();
        File file = new File(dir, getCacheDir());
        LogUtils.e("saveDataToLocal = " + file.getAbsolutePath().toString());
        BufferedWriter bw = null;
        try {
            FileWriter fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            bw.write(System.currentTimeMillis() + 1000 * 100 + "");
            bw.newLine();
            bw.write(json);
            bw.flush();
            fw.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //从服务器加载数据
    protected String loadDataFromServer() {
        String result = null;
        Request request = new Request.Builder().url(getUrl()).build();
        Response response = null;
        response = HttpUtils.execute(request);
        if (response != null && response.isSuccessful()) {
            try {
                result = response.body().string();
                LogUtils.e(result);
                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
