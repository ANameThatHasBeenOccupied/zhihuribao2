package com.example.seaice.zhihuribao.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.seaice.zhihuribao.R;
import com.example.seaice.zhihuribao.Utils.BaseApplication;
import com.example.seaice.zhihuribao.bean.HomeTopInfo;
import com.squareup.picasso.Picasso;
import java.util.List;
import butterknife.ButterKnife;
/**
 * Created by seaice on 2016/8/18.
 */
public class HomeViewPagerAdapter extends PagerAdapter {
    private List<HomeTopInfo> homeTopInfoList;

    public HomeViewPagerAdapter(List<HomeTopInfo> imageViewList){
        this.homeTopInfoList = imageViewList;
    }

    @Override
    public Object instantiateItem(ViewGroup viewGroup, int position){
        LayoutInflater layoutInflater = LayoutInflater.from(BaseApplication.getApplication());
        View RootView = layoutInflater.inflate(R.layout.top_view_pager, viewGroup, false);
        ButterKnife.inject(RootView);
        TextView topTitle = ButterKnife.findById(RootView, R.id.top_title);
        ImageView topImage = ButterKnife.findById(RootView, R.id.top_image);
        topTitle.setText(homeTopInfoList.get(position).getTitle());
        Picasso.with(BaseApplication.getApplication()).load(homeTopInfoList.get(position).getImage()).fit().into(topImage);
        viewGroup.addView(RootView);
        return RootView;
    }

    @Override
    public int getCount() {
        return homeTopInfoList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup viewGroup, int position, Object object){
        viewGroup.removeView((View) object);
    }
}
