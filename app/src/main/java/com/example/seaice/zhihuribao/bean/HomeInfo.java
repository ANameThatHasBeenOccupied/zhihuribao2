package com.example.seaice.zhihuribao.bean;

import java.util.List;

/**
 * Created by seaice on 2016/8/18.
 */
public class HomeInfo {

    private String date;
    private List<HomeStoriesInfo> homeStoriesInfos;
    private List<HomeTopInfo> homeTopInfoList;

    public HomeInfo(String date, List<HomeStoriesInfo> homeStoriesInfos, List<HomeTopInfo> homeTopInfoList) {
        this.date = date;
        this.homeStoriesInfos = homeStoriesInfos;
        this.homeTopInfoList = homeTopInfoList;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<HomeStoriesInfo> getHomeStoriesInfos() {
        return homeStoriesInfos;
    }

    public void setHomeStoriesInfos(List<HomeStoriesInfo> homeStoriesInfos) {
        this.homeStoriesInfos = homeStoriesInfos;
    }

    public List<HomeTopInfo> getHomeTopInfoList() {
        return homeTopInfoList;
    }

    public void setHomeTopInfoList(List<HomeTopInfo> homeTopInfoList) {
        this.homeTopInfoList = homeTopInfoList;
    }

    @Override
    public String toString() {
        return "HomeInfo{" +
                "date='" + date + '\'' +
                ", homeStoriesInfos=" + homeStoriesInfos +
                ", homeTopInfoList=" + homeTopInfoList +
                '}';
    }
}
