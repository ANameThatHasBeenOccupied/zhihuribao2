package com.example.seaice.zhihuribao.protocol;

import com.example.seaice.zhihuribao.Utils.LogUtils;
import com.example.seaice.zhihuribao.bean.HomeInfo;
import com.example.seaice.zhihuribao.bean.HomeStoriesInfo;
import com.example.seaice.zhihuribao.bean.HomeTopInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by seaice on 2016/8/18.
 */
public class HomeProtocol extends BaseProtocol<HomeInfo> {

    private static final String HOMEURL = "http://news-at.zhihu.com/api/4/news/latest";
    private static final String HOMECACHE = "home";


    @Override
    protected HomeInfo paserJsonData(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            String date = jsonObject.getString("date");
            JSONArray jsonArrayStories = jsonObject.getJSONArray("stories");
            List<HomeStoriesInfo> homeStoriesInfos = new ArrayList<HomeStoriesInfo>();
            for (int i = 0; i < jsonArrayStories.length(); i++) {
                JSONObject object = jsonArrayStories.getJSONObject(i);
                int type = object.getInt("type");
                String id = object.getString("id");
                String ga_prefix = object.getString("ga_prefix");
                String title = object.getString("title");
                String images = object.getString("images");
                String image = images.substring(2, images.length()-2);
                image = image.replace("\\", "");//去掉这个斜杠才能得到数据,take me a lot of time
                HomeStoriesInfo homeStoriesInfo = new HomeStoriesInfo(image, type,id,ga_prefix,title);
                LogUtils.e(homeStoriesInfo.toString());
                homeStoriesInfos.add(homeStoriesInfo);
            }
            JSONArray jsonArrayTop = jsonObject.getJSONArray("top_stories");
            List<HomeTopInfo> homeTopInfoList = new ArrayList<HomeTopInfo>();
            for (int i = 0; i < jsonArrayTop.length(); i++) {
                JSONObject object = jsonArrayTop.getJSONObject(i);
                String image = object.getString("image");
                int type = object.getInt("type");
                String id = object.getString("id");
                String ga_prefix = object.getString("ga_prefix");
                String title = object.getString("title");
                HomeTopInfo homeTopInfo = new HomeTopInfo(image,type,id,ga_prefix,title);
                homeTopInfoList.add(homeTopInfo);
            }
            HomeInfo homeInfo = new HomeInfo(date, homeStoriesInfos,homeTopInfoList);
            return homeInfo;
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
