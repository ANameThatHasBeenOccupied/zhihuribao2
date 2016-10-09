package com.example.seaice.zhihuribao.protocol;

import com.example.seaice.zhihuribao.bean.NewsBarInfo;
import com.example.seaice.zhihuribao.bean.ThemeInfo;
import com.google.gson.Gson;

/**
 * Created by seaice on 2016/9/6.
 */
public class ThemeProtocol extends BaseProtocol<ThemeInfo> {

    private String HOMEURL = "http://news-at.zhihu.com/api/4/themes";
    private String HOMECACHE = "theme";

    @Override
    protected ThemeInfo paserJsonData(String json) {
        Gson gson = new Gson();
        ThemeInfo Info = gson.fromJson(json, ThemeInfo.class);
        return Info;
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
