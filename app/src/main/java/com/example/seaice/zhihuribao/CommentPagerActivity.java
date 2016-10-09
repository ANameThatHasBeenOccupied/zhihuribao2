package com.example.seaice.zhihuribao;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.example.seaice.zhihuribao.Utils.BaseApplication;
import com.example.seaice.zhihuribao.Utils.LogUtils;
import com.example.seaice.zhihuribao.Utils.ThreadMgr;
import com.example.seaice.zhihuribao.Utils.UiUtils;
import com.example.seaice.zhihuribao.adapter.CommentRecylerViewAdapter;
import com.example.seaice.zhihuribao.bean.CommentsInfo;
import com.example.seaice.zhihuribao.protocol.CommentProtocol;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class CommentPagerActivity extends AppCompatActivity
        implements CommentPagerListener, View.OnClickListener {

    private String id;
    private int commentsNum;

    private CommentsInfo mLongCommentsInfo;
    private CommentsInfo mShortCommentInfo;

    private CommentRecylerViewAdapter adapter;
    private PopupWindow popMenuWin;

    @InjectView(R.id.tv_comments)
    TextView tv_comments;
    @InjectView(R.id.long_lv)
    RecyclerView long_lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.inject(this);

        initData();
        initView();
    }

    private void initView() {
        tv_comments.setText(String.valueOf(commentsNum + "个评论"));
    }

    private void initData() {
        Bundle bundle = this.getIntent().getExtras();
        id = bundle.getString("id");
        commentsNum = bundle.getInt("comments");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                CommentProtocol protocol = new CommentProtocol(id, true);
                mLongCommentsInfo = protocol.loadData();
                CommentProtocol shortProtocol = new CommentProtocol(id, false);
                mShortCommentInfo = shortProtocol.loadData();
                if (mLongCommentsInfo != null) {
                    UiUtils.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            LogUtils.e(mLongCommentsInfo.toString());
                            setLongLv();
                        }
                    });
                }
            }
        };
        ThreadMgr.getThreadPool().execute(runnable);
    }

    //设置recylerView
    private void setLongLv() {
        if (mLongCommentsInfo.getComments().size() > 0 || mShortCommentInfo.getComments().size() > 0) {
            adapter = new CommentRecylerViewAdapter(mLongCommentsInfo, mShortCommentInfo);
            adapter.setItemClickListener(this);
            LinearLayoutManager layoutManager = new LinearLayoutManager(BaseApplication.getApplication());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            long_lv.setLayoutManager(layoutManager);
            long_lv.addItemDecoration(new SimpleDividerItemDecoration(BaseApplication.getApplication()));
            long_lv.setAdapter(adapter);
        }
    }

    //返回键
    public void pressBackBtn(View view) {
        finish();
    }

    //写评论
    public void writeCommentBtn(View view) {
        Intent intent = new Intent();
        intent.setClass(this, LoginPagerActivity.class);
        startActivity(intent);
    }

    //comment item click listener
    @Override
    public void onCommentItemClick(View v, CommentsInfo.Comment comment) {
        showPopupMenu(v);
    }

    //显示弹出菜单
    private void showPopupMenu(View v) {
        dismissPopupMenu();
        View contentView = View.inflate(BaseApplication.getApplication(), R.layout.popup_menu_win, null);
        int screenWidth = getWindowManager().getDefaultDisplay().getWidth();
        int screenHeight = getWindowManager().getDefaultDisplay().getHeight();
        ScaleAnimation sa = new ScaleAnimation(0.5f, 1.0f, 0.5f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        sa.setDuration(300);
        popMenuWin = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popMenuWin.setFocusable(true);
        popMenuWin.setOutsideTouchable(true);
        int[] location = new int[2];
        v.getLocationInWindow(location);
        popMenuWin.showAtLocation(v, Gravity.LEFT + Gravity.TOP, screenWidth/2, screenHeight / 2);
        popMenuWin.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackgroundAlpha(1.0f);
            }
        });
        setBackgroundAlpha(0.5f);
        contentView.startAnimation(sa);

        Button btn_agree = ButterKnife.findById(contentView, R.id.pop_agree);
        Button btn_jubao = ButterKnife.findById(contentView, R.id.pop_jubao);
        Button btn_fuzhi = ButterKnife.findById(contentView, R.id.pop_fuzhi);
        Button btn_reply = ButterKnife.findById(contentView, R.id.pop_reply);
        btn_agree.setOnClickListener(this);
        btn_jubao.setOnClickListener(this);
        btn_fuzhi.setOnClickListener(this);
        btn_reply.setOnClickListener(this);
    }

    //取消显示框
    private void dismissPopupMenu() {
        if (popMenuWin != null && popMenuWin.isShowing()) {
            popMenuWin.dismiss();
            popMenuWin = null;
            setBackgroundAlpha(1.0f);
        }
    }

    //弹出框后背景变为半透明
    private void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pop_agree:
                UiUtils.showToast("待实现");
                break;
            case R.id.pop_jubao:
                UiUtils.showToast("待实现");
                break;
            case R.id.pop_fuzhi:
                UiUtils.showToast("待实现");
                break;
            case R.id.pop_reply:
                UiUtils.showToast("待实现");
                break;
            default:break;
        }
    }

    //添加recyclerview的divider
    public class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration {
        private Drawable mDivider;

        public SimpleDividerItemDecoration(Context context) {
            mDivider = context.getResources().getDrawable(R.drawable.line_divider);
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }
    }

    @Override
    protected void onDestroy() {
        dismissPopupMenu();
        super.onDestroy();
    }
}
