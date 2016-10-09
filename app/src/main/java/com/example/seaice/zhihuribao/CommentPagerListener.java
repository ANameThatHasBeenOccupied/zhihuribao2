package com.example.seaice.zhihuribao;

import android.view.View;

import com.example.seaice.zhihuribao.bean.CommentsInfo;

public interface CommentPagerListener {
    void onCommentItemClick(View v, CommentsInfo.Comment comment);
}