package com.example.seaice.zhihuribao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.seaice.zhihuribao.Utils.BaseApplication;
import com.example.seaice.zhihuribao.Utils.LogUtils;
import com.example.seaice.zhihuribao.Utils.ThreadMgr;
import com.example.seaice.zhihuribao.Utils.UiUtils;
import com.example.seaice.zhihuribao.adapter.HomeRecylerViewAdapter;
import com.example.seaice.zhihuribao.adapter.MenuRecylerViewAdapter;
import com.example.seaice.zhihuribao.adapter.ThemeItemViewAdapter;
import com.example.seaice.zhihuribao.bean.HomeInfo;
import com.example.seaice.zhihuribao.bean.HomeStoriesInfo;
import com.example.seaice.zhihuribao.bean.HomeTopInfo;
import com.example.seaice.zhihuribao.bean.ThemeInfo;
import com.example.seaice.zhihuribao.bean.ThemeItemInfo;
import com.example.seaice.zhihuribao.protocol.HomeProtocol;
import com.example.seaice.zhihuribao.protocol.OldNewsProtocol;
import com.example.seaice.zhihuribao.protocol.ThemeItemProtocol;
import com.example.seaice.zhihuribao.protocol.ThemeProtocol;
import com.example.seaice.zhihuribao.view.LoadDataScrollController;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class HomePagerActivity extends AppCompatActivity implements HomePagerClickListener {

    private static final String TAG = "ContentActivity";
    private ActionBarDrawerToggle mDrawerToggle;
    private LoadDataScrollController loadDataCtl;
    private HomeInfo homeInfo;
    private HomeRecylerViewAdapter recylerViewAdapter;
    private ThemeInfo themeInfo;
    private ThemeItemInfo themeItemInfo;
    private MenuRecylerViewAdapter menuRecylerViewAdapter;
    private ThemeItemViewAdapter themeItemViewAdapter;
    private Toolbar.OnMenuItemClickListener ToolBarMenuItemListener;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.drawer)
    DrawerLayout drawerLayout;
    @InjectView(R.id.swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;
    @InjectView(R.id.recyle_view)
    RecyclerView recylerView;
    @InjectView(R.id.menu_recyler_view)
    RecyclerView menuRecylerView;

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
        loadHomePagerData();
    }

    //初始化界面相关
    private void initView() {
        setHomePagerToolBar();
        setToolBarListener();
        setDrawerLayout();
        setSwipeLayout();
    }

    //更新选中主题页面的ToolBar显示
    private void setThemePagerToolBar() {
        toolbar.setTitle(themeItemInfo.getName());
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.toolbar_theme_add);
    }

    //设置主页面ToolBar
    private void setHomePagerToolBar() {
        toolbar.setTitle(R.string.home_page);
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.toolbar_menu);

    }

    //设置ToolBar的点击事件
    private void setToolBarListener() {
        ToolBarMenuItemListener = new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.theme_add:
                        UiUtils.showToast("theme add待实现");
                        break;
                    case R.id.theme_remove:
                        UiUtils.showToast("theme_remove add待实现");
                        break;
                    case R.id.message:
                        UiUtils.showToast("message待实现");
                        break;
                    case R.id.setting_night_mode:
                        UiUtils.showToast("setting_night_mode add待实现");
                        break;
                    case R.id.setting_option:
                        UiUtils.showToast("setting_option add待实现");
                        break;
                    default:
                        break;
                }
                return false;
            }
        };
        toolbar.setOnMenuItemClickListener(ToolBarMenuItemListener);
    }

    //设置下拉刷新
    private void setSwipeLayout() {
        swipeRefreshLayout.setColorSchemeResources(R.color.blue);
    }

    //设置侧边栏
    private void setDrawerLayout() {
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        loadSlideMenuData();
    }

    //设置首页recylerview
    private void setHomeRecylerView() {
        recylerViewAdapter = new HomeRecylerViewAdapter(homeInfo);
        recylerViewAdapter.setOnItemClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(BaseApplication.getApplication());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recylerView.setLayoutManager(layoutManager);
        recylerView.setAdapter(recylerViewAdapter);
        setScrollController();
        setHomePagerToolBar();
    }

    //设置主题页面recylerView
    private void setThemeRecylerView() {
        recylerView.removeOnScrollListener(loadDataCtl);
        themeItemViewAdapter = new ThemeItemViewAdapter(themeItemInfo);
        themeItemViewAdapter.setHomePagerClickListener(this);
        recylerView.setAdapter(themeItemViewAdapter);
        setThemePagerToolBar();
    }

    //设置下拉和上拉刷新
    private void setScrollController() {
        loadDataCtl = new LoadDataScrollController(this);
        swipeRefreshLayout.setOnRefreshListener(loadDataCtl);
        recylerView.addOnScrollListener(loadDataCtl);
    }

    //设置侧边栏显示
    private void setSlideMenuRecylerView() {
        menuRecylerViewAdapter = new MenuRecylerViewAdapter(themeInfo);
        menuRecylerViewAdapter.setRecylerViewListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(BaseApplication.getApplication());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        menuRecylerView.setLayoutManager(layoutManager);
        menuRecylerView.setAdapter(menuRecylerViewAdapter);
    }

    //主页面下拉刷新
    @Override
    public void onHomePagerRefresh() {
        BaseApplication.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(BaseApplication.getApplication(), "刷新成功", Toast.LENGTH_SHORT).show();
            }
        }, 3000);
    }

    //更新Home页面ToolBar的title
    @Override
    public void onHomePagerUpdateToolBarTitle(int pos) {
        if (pos == 0) {
            toolbar.setTitle(UiUtils.getString(R.string.home_page));
        } else {
            toolbar.setTitle(recylerViewAdapter.getTitle(pos - 1));
        }
    }

    //点击主编事件响应
    @Override
    public void onHomePagerEditorClick() {
        UiUtils.showToast("editor click!!");
    }

    //HomeRecylerView条目点击的回调
    @Override
    public void onHomePagerItemClick(boolean isThemePagerItem, int position) {
        String id = null;
        if(isThemePagerItem == false){
            HomeStoriesInfo info = homeInfo.getHomeStoriesInfos().get(position);
            id = info.getId();
            LogUtils.e("ID 1 = "+id);
        }else{
            id = themeItemInfo.getStories().get(position).getId();
            LogUtils.e("ID = "+id);
        }
        Intent intent = new Intent();
        intent.putExtra("id", id);
        intent.putExtra("isThemePagerItem", false);
        intent.setClass(this, NewsPagerActivity.class);
        startActivity(intent);
    }

    //首页viewpager点击的回调
    @Override
    public void onHomeViewPagerClick(View view, int position) {
        HomeTopInfo info = homeInfo.getHomeTopInfoList().get(position);
        String id = info.getId();
        Intent intent = new Intent();
        intent.putExtra("id", id);
        intent.putExtra("isThemePagerItem", false);
        intent.setClass(this, NewsPagerActivity.class);
        startActivity(intent);
    }

    //点击侧边某个主题
    @Override
    public void onSlideMenuItemClick(View view, int position) {
        if (position == 0) {//首页
            loadHomePagerData();
        } else {
            loadThemePagerData(position);
        }
        drawerLayout.closeDrawer(Gravity.LEFT);
    }

    //点击Follow
    @Override
    public void onSlideMenuAddClick(View view, int position) {
        UiUtils.showToast("theme add 待实现");
    }

    //主页面加载更多的回调
    @Override
    public void onHomePagerloadMore() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                OldNewsProtocol protocol = new OldNewsProtocol();
                final List<HomeStoriesInfo> homeInfo = protocol.loadData();
                if (homeInfo != null) {
                    UiUtils.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            loadDataCtl.setLoadingDataStatus(false);
                            recylerViewAdapter.add(homeInfo);
                        }
                    });
                }
            }
        };
        ThreadMgr.getThreadPool().execute(runnable);
    }

    //加载主页面数据
    private void loadHomePagerData() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                HomeProtocol homeProtocol = new HomeProtocol("latest");
                homeInfo = homeProtocol.loadData();
                BaseApplication.getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setHomeRecylerView();
                    }
                }, 500);
            }
        };
        ThreadMgr.getThreadPool().execute(runnable);
    }

    //加载指定主题页面
    private void loadThemePagerData(int position) {
        final String themeUrlId = themeInfo.getOthers().get(position - 1).getId();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                ThemeItemProtocol protocol = new ThemeItemProtocol(themeUrlId);
                themeItemInfo = protocol.loadData();
                UiUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setThemeRecylerView();
                    }
                });
            }
        };
        ThreadMgr.getThreadPool().execute(runnable);
    }

    //加载主题侧边菜单数据
    private void loadSlideMenuData() {
        Runnable runable = new Runnable() {
            @Override
            public void run() {
                ThemeProtocol protocol = new ThemeProtocol();
                themeInfo = protocol.loadData();
                if (themeInfo != null) {
                    UiUtils.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setSlideMenuRecylerView();
                        }
                    });
                }
            }
        };
        ThreadMgr.getThreadPool().execute(runable);
    }

    //我的收藏
    public void tvMyCollect(View view) {
        UiUtils.showToast("我的收藏待实现");
    }

    //我的下载
    public void tvMyDownload(View view) {
        UiUtils.showToast("我的下载待实现");
    }

    //点击用户
    public void tvUserIcon(View view) {
        UiUtils.showToast("用户待实现");
    }

}
