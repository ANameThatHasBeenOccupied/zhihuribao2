package com.example.seaice.zhihuribao.bean;

import java.util.List;

/**
 * Created by seaice on 2016/8/18.
 */
public class HomeStoriesInfo {

    private String images;
    private int type;
    private String id;
    private String title;
    private String ga_prefix;
    private boolean isDateTime;//是否是日期条目
    private String date;

    public boolean isDateTime() {
        return isDateTime;
    }

    public void setIsDateTime(boolean dateTime) {
        isDateTime = dateTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public HomeStoriesInfo(){}

    public HomeStoriesInfo(String images, int type, String id, String ga_prefix, String title) {
        this.images = images;
        this.type = type;
        this.id = id;
        this.ga_prefix = ga_prefix;
        this.title = title;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public String getImages() {
        return images;
    }

    public int getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public String getTitle() {
        return title;
    }


    @Override
    public String toString() {
        return "HomeStoriesInfo{" +
                "images='" + images + '\'' +
                ", type=" + type +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", ga_prefix='" + ga_prefix + '\'' +
                ", isDateTime=" + isDateTime +
                ", date='" + date + '\'' +
                '}';
    }
}
