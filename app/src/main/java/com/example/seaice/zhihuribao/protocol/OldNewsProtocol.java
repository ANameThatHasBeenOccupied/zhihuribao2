package com.example.seaice.zhihuribao.protocol;

import android.text.format.DateFormat;
import android.text.format.Time;
import com.example.seaice.zhihuribao.Utils.LogUtils;
import com.example.seaice.zhihuribao.Utils.UiUtils;
import com.example.seaice.zhihuribao.bean.HomeStoriesInfo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by seaice on 2016/8/26.
 */
public class OldNewsProtocol extends BaseProtocol<List<HomeStoriesInfo>> {

    private String HOMEURL = "http://news-at.zhihu.com/api/4/news/before/";
    private String HOMECACHE;

    private static int oldNewsDateCount = 0;//没加载一次增加，表示加载了之前的多少天了

    public OldNewsProtocol() {
        //1. 初始化加载的是哪一天
        //2. 初始化HOMECACHE,即保存json的本地文件路径
        initDateToGetNews();
    }

    @Override
    protected List<HomeStoriesInfo> paserJsonData(String json) {
        LogUtils.e("HomeProtocol paserJsonData");
        try {
            JSONObject jsonObject = new JSONObject(json);
            String date = jsonObject.getString("date");
            JSONArray jsonArrayStories = jsonObject.getJSONArray("stories");
            List<HomeStoriesInfo> homeStoriesInfos = new ArrayList<>();
            HomeStoriesInfo dateInfo = new HomeStoriesInfo();
            dateInfo.setDate(formatJsonDate(date));//格式化日期
            dateInfo.setIsDateTime(true);
            homeStoriesInfos.add(dateInfo);
            for (int i = 0; i < jsonArrayStories.length(); i++) {
                JSONObject object = jsonArrayStories.getJSONObject(i);
                int type = object.getInt("type");
                String id = object.getString("id");
                String ga_prefix = object.getString("ga_prefix");
                String title = object.getString("title");
                String images = object.getString("images");
                String image = images.substring(2, images.length() - 2);
                image = image.replace("\\", "");//去掉这个斜杠才能得到数据,take me a lot of time
                HomeStoriesInfo homeStoriesInfo = new HomeStoriesInfo(image, type, id, ga_prefix, title);
                homeStoriesInfo.setDate(formatJsonDate(date));
                homeStoriesInfo.setIsDateTime(false);
                LogUtils.e(homeStoriesInfo.toString());
                homeStoriesInfos.add(homeStoriesInfo);
            }
            return homeStoriesInfos;
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

    //下拉加载日报的日期
    private void initDateToGetNews() {
        Calendar calendar = Calendar.getInstance();
        Time time = new Time();
        time.setToNow();
        calendar.setTimeInMillis(time.toMillis(false) - 24 * 60 * 60 * 1000 * oldNewsDateCount);
        DateFormat dateFormat = new DateFormat();
        oldNewsDateCount++;
        String date = dateFormat.format("yyyyMMdd", calendar.getTimeInMillis()).toString();
        HOMEURL += date;//对应加载哪天的数据
        HOMECACHE += date;//保存以日期作为文件路径
    }
    //格式化日期，保存为xx年xx月xx日 星期几
    private String formatJsonDate(String date) {
        String sb = "";
        int year = Integer.valueOf(date.substring(0, 4));
        int month = Integer.valueOf(date.substring(4, 6));
        int day = Integer.valueOf(date.substring(6));
        sb += (year + "年" + month + "月" + day + "日");
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        String dayOfWeek = UiUtils.getDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK));
        sb += (" " + "星期" + dayOfWeek);
        return sb.toString();
    }
}
