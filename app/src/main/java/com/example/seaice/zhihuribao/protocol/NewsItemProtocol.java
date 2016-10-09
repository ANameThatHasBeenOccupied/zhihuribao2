package com.example.seaice.zhihuribao.protocol;

import android.support.v4.view.NestedScrollingChild;

import com.example.seaice.zhihuribao.Utils.LogUtils;
import com.example.seaice.zhihuribao.bean.NewsInfo;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by seaice on 2016/8/30.
 */
public class NewsItemProtocol extends BaseProtocol<NewsInfo> {

    private String HOMEURL = "http://news-at.zhihu.com/api/4/news/";
    private String HOMECACHE;

    public NewsItemProtocol(String id){
        HOMECACHE = id;
        HOMEURL+=id;
        LogUtils.e("TEST"+HOMEURL);
    }

    @Override
    protected NewsInfo paserJsonData(String json) {
        LogUtils.e("NewsItemProtocol paserJsonData");
        try {
            JSONObject jsonObject = new JSONObject(json);
            String body = jsonObject.getString("body");
            String image_source = jsonObject.getString("image_source");
            String title = jsonObject.getString("title");
            String image = jsonObject.getString("image");
            String share_url=jsonObject.getString("share_url");
            String ga_prefix = jsonObject.getString("ga_prefix");
            String images = jsonObject.getString("images");
            int type = jsonObject.getInt("type");
            String id = jsonObject.getString("id");
            LogUtils.e(id);
            NewsInfo info = new NewsInfo(body, image_source, title,
                    image,share_url,ga_prefix, images,type,id);

            return info;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected String getCacheDir() {
        return HOMECACHE;
    }

    @Override
    protected String getUrl() {
        return HOMEURL;
    }
}
