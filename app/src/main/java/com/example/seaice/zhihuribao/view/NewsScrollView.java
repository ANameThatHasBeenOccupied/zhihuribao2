package com.example.seaice.zhihuribao.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by seaice on 2016/8/31.
 */
public class NewsScrollView extends ScrollView {

    private OnScrollListener listener;

    public NewsScrollView(Context context) {
        super(context);
    }

    public NewsScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NewsScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnScrollListener(OnScrollListener listener) {
        this.listener = listener;
    }

    public interface OnScrollListener {
        void onScroll(int scrollY);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (listener != null) {
            listener.onScroll(getScrollY());
        }
    }
}
