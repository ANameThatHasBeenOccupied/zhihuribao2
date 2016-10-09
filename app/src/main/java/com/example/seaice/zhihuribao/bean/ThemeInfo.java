package com.example.seaice.zhihuribao.bean;

import java.util.List;

/**
 * Created by seaice on 2016/9/6.
 */
public class ThemeInfo {
    private int limit;
    private List<ThemeItem> subscribed;
    private List<ThemeItem> others;

    public ThemeInfo(int limit, List<ThemeItem> subscribed, List<ThemeItem> others) {
        this.limit = limit;
        this.subscribed = subscribed;
        this.others = others;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<ThemeItem> getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(List<ThemeItem> subscribed) {
        this.subscribed = subscribed;
    }

    public List<ThemeItem> getOthers() {
        return others;
    }

    public void setOthers(List<ThemeItem> others) {
        this.others = others;
    }

    @Override
    public String toString() {
        return "ThemeInfo{" +
                "limit=" + limit +
                ", subscribed='" + subscribed + '\'' +
                ", others=" + others +
                '}';
    }

    public class ThemeItem {
        private String color;
        private String thumbnail;
        private String description;
        private String id;
        private String name;

        public ThemeItem(String color, String thumbnail, String description, String id, String name) {
            this.color = color;
            this.thumbnail = thumbnail;
            this.description = description;
            this.id = id;
            this.name = name;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "ThemeItem{" +
                    "color='" + color + '\'' +
                    ", thumbnail='" + thumbnail + '\'' +
                    ", description='" + description + '\'' +
                    ", id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
