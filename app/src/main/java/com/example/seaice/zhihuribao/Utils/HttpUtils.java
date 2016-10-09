package com.example.seaice.zhihuribao.Utils;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by seaice on 2016/8/11.
 */
public class HttpUtils {
    private static final String TAG = "HttpUtils";

    private static final OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
            .readTimeout(5000, TimeUnit.MILLISECONDS).build();

    public static OkHttpClient getInstance() {
        return mOkHttpClient;
    }

    /**
     * 该不会开启异步线程。
     *
     * @param request
     * @return
     * @throws IOException
     */
    public static Response execute(Request request) {
        try {
            return getInstance().newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 开启异步线程访问网络
     *
     * @param request
     * @param responseCallBack
     */
    public static void enqueue(Request request, Callback responseCallBack) {
        getInstance().newCall(request).enqueue(responseCallBack);
    }

    /**
     * 开启异步线程访问网络, 且不在意返回结果（实现空callback）
     *
     * @param request
     */
    public static Response enqueue(Request request) {
        mOkHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }

        });
        return null;
    }

    public static void loadImageFromUrl(String url, ImageView imageView){
        Picasso.with(BaseApplication.getApplication()).load(url).fit().into(imageView);
    }
}

