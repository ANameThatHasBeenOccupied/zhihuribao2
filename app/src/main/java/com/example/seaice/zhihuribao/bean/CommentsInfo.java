package com.example.seaice.zhihuribao.bean;

import java.util.List;

/**
 * Created by seaice on 2016/8/31.
 */
public class CommentsInfo {
    private List<Comment> comments;

    public CommentsInfo(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "comments=" + comments +
                '}';
    }

    public static class Comment{
        private String author;
        private String content;
        private String avatar;
        private String time;
        private ReplyTo reply_to;
        private String id;
        private String likes;

        public Comment(String author, String content, String avatar, String time, String id, String likes, ReplyTo reply_to) {
            this.author = author;
            this.content = content;
            this.avatar = avatar;
            this.time = time;
            this.reply_to = reply_to;
            this.id = id;
            this.likes = likes;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLikes() {
            return likes;
        }

        public void setLikes(String likes) {
            this.likes = likes;
        }

        public ReplyTo getReply_to() {
            return reply_to;
        }

        public void setReply_to(ReplyTo reply_to) {
            this.reply_to = reply_to;
        }

        @Override
        public String toString() {
            return "Comment{" +
                    "author='" + author + '\'' +
                    ", content='" + content + '\'' +
                    ", avatar='" + avatar + '\'' +
                    ", time='" + time + '\'' +
                    ", id='" + id + '\'' +
                    ", likes='" + likes + '\'' +
                    ", reply_to=" + reply_to +
                    '}';
        }
    }

    public static class ReplyTo{
        private String content;
        private int status;
        private String id;
        private String author;

        public ReplyTo(String content, int status, String id, String author) {
            this.content = content;
            this.status = status;
            this.id = id;
            this.author = author;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        @Override
        public String toString() {
            return "ReplyTo{" +
                    "content='" + content + '\'' +
                    ", status=" + status +
                    ", id='" + id + '\'' +
                    ", author='" + author + '\'' +
                    '}';
        }
    }
}
