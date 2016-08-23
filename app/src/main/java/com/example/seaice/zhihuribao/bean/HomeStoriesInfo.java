package com.example.seaice.zhihuribao.bean;

import java.util.List;

/**
 * Created by seaice on 2016/8/18.
 */
public class HomeStoriesInfo {

    private String images;
    private int type;
    private String id;

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

    private String ga_prefix;

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

    private String title;

    @Override
    public String toString() {
        return "HomeStoriesInfo{" +
                "images=" + images +
                ", type=" + type +
                ", id='" + id + '\'' +
                ", ga_prefix='" + ga_prefix + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
