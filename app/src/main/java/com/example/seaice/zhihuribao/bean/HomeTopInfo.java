package com.example.seaice.zhihuribao.bean;

import java.util.List;

/**
 * Created by seaice on 2016/8/18.
 */
public class HomeTopInfo {
    private String image;
    private int type;
    private String id;
    private String ga_prefix;
    private String title;

    public HomeTopInfo(String image, int type, String id, String ga_prefix, String title) {
        this.image = image;
        this.type = type;
        this.id = id;
        this.ga_prefix = ga_prefix;
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "HomeTopInfo{" +
                "image='" + image + '\'' +
                ", type=" + type +
                ", id='" + id + '\'' +
                ", ga_prefix='" + ga_prefix + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
