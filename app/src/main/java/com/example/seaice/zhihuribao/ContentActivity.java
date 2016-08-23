package com.example.seaice.zhihuribao;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.seaice.zhihuribao.Utils.BaseApplication;
import com.example.seaice.zhihuribao.adapter.HomeRecylerViewAdapter;
import com.example.seaice.zhihuribao.bean.HomeInfo;
import com.example.seaice.zhihuribao.protocol.HomeProtocol;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ContentActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "ContentActivity";
    private HomeProtocol homeProtocol;
    private HomeInfo homeInfo;
    private ActionBarDrawerToggle mDrawerToggle;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.drawer)
    DrawerLayout drawerLayout;
    @InjectView(R.id.swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;
    @InjectView(R.id.recyle_view)
    RecyclerView recylerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        ButterKnife.inject(this);

        initData();
        initView();
    }

    //初始化数据相关
    private void initData() {
        homeProtocol = new HomeProtocol();
        new Thread(new Runnable() {
            @Override
            public void run() {
                homeInfo = homeProtocol.loadData();
                BaseApplication.getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setRecylerView();
                    }
                }, 500);
            }
        }).start();
    }

    //初始化界面相关
    private void initView() {
        setToolBar();
        setDrawerLayout();
        setSwipeLayout();
    }

    //设置recyler view
    private void setRecylerView() {
        HomeRecylerViewAdapter adapter = new HomeRecylerViewAdapter(homeInfo);
        LinearLayoutManager layoutManager = new LinearLayoutManager(BaseApplication.getApplication());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recylerView.setLayoutManager(layoutManager);
        recylerView.setAdapter(adapter);
    }

    //设置下拉刷新
    private void setSwipeLayout() {
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.blue);
    }

    //设置侧边栏
    private void setDrawerLayout() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        drawerLayout.setDrawerListener(mDrawerToggle);
    }

    //设置ToolBar
    private void setToolBar() {
        toolbar.setTitle(R.string.home_page);
        toolbar.setNavigationIcon(R.mipmap.ic_drawer_am);
        setSupportActionBar(toolbar);
    }

    //设置ToolBar的选项
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menu) {
        switch (menu.getItemId()) {
            case R.id.action_login:
                break;
            case R.id.action_night:
                break;
            case R.id.action_settiong:
                break;
            default:
                break;
        }
        return true;
    }

    //下拉刷新监听
    @Override
    public void onRefresh() {
        BaseApplication.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(BaseApplication.getApplication(), "刷新成功", Toast.LENGTH_SHORT).show();
            }
        }, 3000);
    }
}
