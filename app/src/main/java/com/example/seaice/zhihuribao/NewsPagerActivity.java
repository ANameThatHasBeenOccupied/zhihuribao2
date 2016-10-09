package com.example.seaice.zhihuribao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.seaice.zhihuribao.Utils.BaseApplication;
import com.example.seaice.zhihuribao.Utils.LogUtils;
import com.example.seaice.zhihuribao.Utils.ThreadMgr;
import com.example.seaice.zhihuribao.Utils.UiUtils;
import com.example.seaice.zhihuribao.bean.NewsBarInfo;
import com.example.seaice.zhihuribao.bean.NewsInfo;
import com.example.seaice.zhihuribao.protocol.NewsBarProtocol;
import com.example.seaice.zhihuribao.protocol.NewsItemProtocol;
import com.example.seaice.zhihuribao.view.NewsScrollView;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class NewsPagerActivity extends AppCompatActivity implements NewsScrollView.OnScrollListener {

    @InjectView(R.id.webView)
    WebView webView;
    @InjectView(R.id.iv_news)
    ImageView iv_news;
    @InjectView(R.id.tv_title)
    TextView tv_title;
    @InjectView(R.id.tv_autor)
    TextView tv_autor;
    @InjectView(R.id.bar)
    RelativeLayout rrBar;
    @InjectView(R.id.newsScrollView)
    NewsScrollView newsScrollView;
    @InjectView(R.id.iv_back)
    ImageView iv_back;
    @InjectView(R.id.iv_share)
    ImageView iv_share;
    @InjectView(R.id.tv_collected)
    ImageView tv_collected;
    @InjectView(R.id.tv_comment)
    TextView tv_comment;
    @InjectView(R.id.tv_praise)
    TextView tv_praise;

    private NewsInfo newsInfo;
    private NewsBarInfo barInfo;
    private String id;
    private boolean isFromThemePager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_info);
        ButterKnife.inject(this);

        initData();
        initView();
    }

    private void initView() {

    }

    private void initData() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        isFromThemePager = intent.getBooleanExtra("isThemePagerItem", false);
        LogUtils.i(id + isFromThemePager);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (isFromThemePager == false) {
                    NewsItemProtocol newsProtocol = new NewsItemProtocol(id);
                    newsInfo = newsProtocol.loadData();
                    NewsBarProtocol newsBarProtocol = new NewsBarProtocol(id);
                    barInfo = newsBarProtocol.loadData();
                    LogUtils.e(barInfo.toString());
                } else {

                }
                UiUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setDisplayheader();
                        setWebViewDiplay();
                        setNewsBarDisplay();
                    }
                });
            }
        };
        ThreadMgr.getThreadPool().execute(runnable);
    }

    //设置顶部bar显示
    private void setNewsBarDisplay() {
        tv_comment.setText(String.valueOf(barInfo.getComments()));
        tv_praise.setText(String.valueOf(barInfo.getPopularity()));
    }

    //显示文章的作者和题目
    private void setDisplayheader() {
        if (newsInfo == null) {
            LogUtils.e("newsInfo == null");
        }
        Picasso.with(BaseApplication.getApplication()).load(newsInfo.getImage()).into(iv_news);
        tv_title.setText(newsInfo.getTitle());
        tv_autor.setText(newsInfo.getImage_source());
    }

    //显示webView
    private void setWebViewDiplay() {
        String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/css/news.css\" type=\"text/css\">";
        String html = "<html><head>" + css + "</head><body>" + newsInfo.getBody() + "</body></html>";
        html = html.replace("<div class=\"img-place-holder\">", "");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webView.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null);
        newsScrollView.setOnScrollListener(this);
    }

    //滑动页面时，设置顶端bar的透明度
    @Override
    public void onScroll(int scrollY) {
        float barAlpha = iv_news.getHeight() - scrollY;
        if (barAlpha <= 0) {
            barAlpha = 0;
            rrBar.setVisibility(View.GONE);
            rrBar.setAlpha(barAlpha);
        } else {
            barAlpha = barAlpha / iv_news.getHeight();
            rrBar.setAlpha(barAlpha);
            rrBar.setVisibility(View.VISIBLE);
        }
    }

    public void pressBackBtn(View view) {
        finish();
    }

    //分享
    public void pressShareBtn(View view) {
        UiUtils.showToast("分享待实现");
    }

    //收藏
    public void pressCollectedBtn(View view) {
        UiUtils.showToast("收藏待实现");
    }

    //评论
    public void pressCommentBtn(View view) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        bundle.putInt("comments", barInfo.getComments());
        bundle.putInt("shortComment", barInfo.getShort_comments());
        bundle.putInt("longComment", barInfo.getLong_comments());
        intent.putExtras(bundle);
        intent.setClass(this, CommentPagerActivity.class);
        startActivity(intent);
    }

    //点赞
    public void pressPraiseBtn(View view) {
        UiUtils.showToast("点赞待实现");
    }

    //点击用户
    public void tvUserIcon(View view) {
        UiUtils.showToast("点击用户待实现");
    }

}
