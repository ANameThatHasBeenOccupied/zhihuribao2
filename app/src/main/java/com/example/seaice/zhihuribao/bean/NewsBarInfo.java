package com.example.seaice.zhihuribao.bean;

/**
 * Created by seaice on 2016/8/31.
 */
public class NewsBarInfo {
    private int long_comments;
    private int popularity;
    private int short_comments;
    private int comments;

    public NewsBarInfo(int long_comments, int popularity, int short_comments, int comments) {
        this.long_comments = long_comments;
        this.popularity = popularity;
        this.short_comments = short_comments;
        this.comments = comments;
    }

    public int getLong_comments() {
        return long_comments;
    }

    public void setLong_comments(int long_comments) {
        this.long_comments = long_comments;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public int getShort_comments() {
        return short_comments;
    }

    public void setShort_comments(int short_comments) {
        this.short_comments = short_comments;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "NewsBarInfo{" +
                "long_comments=" + long_comments +
                ", popularity=" + popularity +
                ", short_comments=" + short_comments +
                ", comments=" + comments +
                '}';
    }
}
