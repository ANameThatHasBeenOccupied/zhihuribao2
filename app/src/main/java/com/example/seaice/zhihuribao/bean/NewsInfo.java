package com.example.seaice.zhihuribao.bean;

/**
 * Created by seaice on 2016/8/30.
 */
public class NewsInfo {
    private String body;
    private String image_source;
    private String title;
    private String image;
    private String share_url;
    private String ga_prefix;
    private String images;
    private int type;
    private String id;

    public NewsInfo(String body, String image_source, String title, String image, String share_url, String ga_prefix, String images, int type, String id) {
        this.body = body;
        this.image_source = image_source;
        this.title = title;
        this.image = image;
        this.share_url = share_url;
        this.ga_prefix = ga_prefix;
        this.images = images;
        this.type = type;
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
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

    @Override
    public String toString() {
        return "NewsInfo{" +
                "body='" + body + '\'' +
                ", image_source='" + image_source + '\'' +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", share_url='" + share_url + '\'' +
                ", ga_prefix='" + ga_prefix + '\'' +
                ", images='" + images + '\'' +
                ", type=" + type +
                ", id='" + id + '\'' +
                '}';
    }
}
