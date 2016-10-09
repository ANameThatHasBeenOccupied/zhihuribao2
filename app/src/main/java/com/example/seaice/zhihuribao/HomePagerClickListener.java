package com.example.seaice.zhihuribao;

import android.view.View;

public interface HomePagerClickListener{
    void onHomePagerItemClick(boolean isThemePagerItem, int position);
    void onHomeViewPagerClick(View view, int position);
    void onSlideMenuItemClick(View view, int position);
    void onSlideMenuAddClick(View view, int position);
    void onHomePagerRefresh();
    void onHomePagerloadMore();
    void onHomePagerUpdateToolBarTitle(int pos);
    void onHomePagerEditorClick();
}