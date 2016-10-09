package com.example.seaice.zhihuribao.bean;

import java.util.List;

/**
 * Created by seaice on 2016/9/7.
 */
public class ThemeItemInfo {
    private List<ThemeStory> stories;
    private String description;
    private String background;
    private String color;
    private String name;
    private String image;
    private List<Editor> editors;
    private String image_source;

    public ThemeItemInfo(List<ThemeStory> stories, String description, String background, String color, String name, String image, List<Editor> editors, String image_source) {
        this.stories = stories;
        this.description = description;
        this.background = background;
        this.color = color;
        this.name = name;
        this.image = image;
        this.editors = editors;
        this.image_source = image_source;
    }

    public List<ThemeStory> getStories() {
        return stories;
    }

    public void setStories(List<ThemeStory> stories) {
        this.stories = stories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Editor> getEditors() {
        return editors;
    }

    public void setEditors(List<Editor> editors) {
        this.editors = editors;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    @Override
    public String toString() {
        return "ThemeItemInfo{" +
                "stories=" + stories +
                ", description='" + description + '\'' +
                ", background='" + background + '\'' +
                ", color='" + color + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", editors=" + editors +
                ", image_source='" + image_source + '\'' +
                '}';
    }

    public class ThemeStory {
        private List<String> images;
        private int type;
        private String id;
        private String title;

        public ThemeStory(List<String> images, int type, String id, String title) {
            this.images = images;
            this.type = type;
            this.id = id;
            this.title = title;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "ThemeStory{" +
                    "images=" + images +
                    ", type=" + type +
                    ", id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }
    }

    public class Editor {
        private String url;
        private String bio;
        private String id;
        private String avatar;
        private String name;

        public Editor(String url, String bio, String id, String avatar, String name) {
            this.url = url;
            this.bio = bio;
            this.id = id;
            this.avatar = avatar;
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getBio() {
            return bio;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Editor{" +
                    "url='" + url + '\'' +
                    ", bio='" + bio + '\'' +
                    ", id='" + id + '\'' +
                    ", avatar='" + avatar + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
