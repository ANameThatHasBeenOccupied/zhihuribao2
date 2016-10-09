package com.example.seaice.zhihuribao.protocol;

import com.example.seaice.zhihuribao.bean.NewsBarInfo;
import com.google.gson.Gson;

/**
 * Created by seaice on 2016/8/31.
 */
public class NewsBarProtocol extends BaseProtocol<NewsBarInfo> {

    private String HOMEURL = "http://news-at.zhihu.com/api/4/story-extra/";
    private String HOMECACHE;

    public NewsBarProtocol(String id){
        HOMECACHE ="extra"+id;
        HOMEURL+=id;
    }

    @Override
    protected NewsBarInfo paserJsonData(String json) {
        Gson gson = new Gson();
        NewsBarInfo barInfo = gson.fromJson(json, NewsBarInfo.class);
        return barInfo;
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
