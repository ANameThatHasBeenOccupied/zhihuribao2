package com.example.seaice.zhihuribao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.example.seaice.zhihuribao.Utils.UiUtils;
import butterknife.ButterKnife;
//登陆页面
public class LoginPagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        ButterKnife.inject(this);

        initData();
        initView();
    }

    private void initData() {

    }

    private void initView() {

    }

    //登陆
    public void clickBtnLogin(View view) {
        UiUtils.showToast("待实现");
    }

    //通过新浪微博登陆
    public void clickBtnLoginBySinaAccout(View view) {
        UiUtils.showToast("待实现");
    }

    //通过腾讯微博
    public void clickBtnLoginByTencentAccout(View view) {
        UiUtils.showToast("待实现");
    }

    public void clickBtnBack(View view) {
        finish();
    }


}
