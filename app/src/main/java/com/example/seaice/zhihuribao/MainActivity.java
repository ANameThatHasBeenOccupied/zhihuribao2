package com.example.seaice.zhihuribao;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.seaice.zhihuribao.Utils.BaseApplication;
import com.example.seaice.zhihuribao.Utils.ThreadMgr;
import com.example.seaice.zhihuribao.Utils.UiUtils;
import com.example.seaice.zhihuribao.bean.GuideInfo;
import com.example.seaice.zhihuribao.protocol.GuideProtocol;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    private GuideProtocol guideProtocol;
    private GuideInfo guideInfo;

    @InjectView(R.id.iv_guide)
    ImageView iv_guide;

    @InjectView(R.id.tv_guide)
    TextView tv_guide;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        guideProtocol = new GuideProtocol();
        setImageGuide();
    }

    //从服务器加载资源
    private void loadDataFromProtocol(final Runnable r) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                guideInfo = guideProtocol.loadData();
                if (guideInfo != null)
                    UiUtils.runOnUiThread(r);
            }
        };
        ThreadMgr.getThreadPool().execute(runnable);
    }

    //设置imageview的放大效果
    private void setImageGuide() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                //guide image view
                Picasso.with(BaseApplication.getApplication()).load(guideInfo.getGuidePic()).into(iv_guide);
                Animation guideImageZoomAni = AnimationUtils.loadAnimation(MainActivity.this, R.anim.guide_imageview_zoom);
                guideImageZoomAni.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this, ContentActivity.class);
                        MainActivity.this.startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
                iv_guide.startAnimation(guideImageZoomAni);
                tv_guide.setText(guideInfo.getGuideName());
            }
        };
        loadDataFromProtocol(r);
    }
}
