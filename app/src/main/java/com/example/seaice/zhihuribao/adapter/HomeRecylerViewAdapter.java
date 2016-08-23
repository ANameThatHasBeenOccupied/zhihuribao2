package com.example.seaice.zhihuribao.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.seaice.zhihuribao.R;
import com.example.seaice.zhihuribao.Utils.BaseApplication;
import com.example.seaice.zhihuribao.bean.HomeInfo;
import com.example.seaice.zhihuribao.bean.HomeStoriesInfo;
import com.example.seaice.zhihuribao.bean.HomeTopInfo;
import com.example.seaice.zhihuribao.view.CircleIndicator;
import com.squareup.picasso.Picasso;
import java.util.List;
import butterknife.ButterKnife;
/**
 * Created by seaice on 2016/8/22.
 */
public class HomeRecylerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_DATE = 2;
    private LayoutInflater mInflate;
    private Context mContext;
    private List<HomeStoriesInfo> homeStoriesInfos;
    private List<HomeTopInfo> homeTopInfos;
    private HomeInfo homeInfo;
    private boolean isTouchViewPager = false;
    private boolean isViewPagerLoop = false;

    public HomeRecylerViewAdapter(HomeInfo homeInfo) {
        this.homeInfo = homeInfo;
        initData();
    }

    private void initData() {
        homeStoriesInfos = homeInfo.getHomeStoriesInfos();
        homeTopInfos = homeInfo.getHomeTopInfoList();
        mContext = BaseApplication.getApplication();
        mInflate = LayoutInflater.from(mContext);
    }

    //绑定不同类型Holder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View view = mInflate.inflate(R.layout.header_recyler_view, parent, false);
            return new HeaderHolder(view);
        } else if (viewType == TYPE_DATE) {
            View view = mInflate.inflate(R.layout.date_recyler_view, parent, false);
            return new DateHolder(view);
        } else if (viewType == TYPE_ITEM) {
            View view = mInflate.inflate(R.layout.item_recyler_view, parent, false);
            return new MyViewHolder(view);
        }
        throw new RuntimeException("there is no type that match the type " + viewType);
    }

    //设置holder的view元素
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vh, int position) {
        if (vh instanceof HeaderHolder) {
            if(isViewPagerLoop == false){
                isViewPagerLoop = true;
                setViewPager((HeaderHolder) vh);
            }
        } else if (vh instanceof DateHolder) {
        } else if (vh instanceof MyViewHolder) {
            HomeStoriesInfo info = homeStoriesInfos.get(position - 2);
            MyViewHolder holder = (MyViewHolder) vh;
            holder.tv_title.setText(info.getTitle());
            Picasso.with(BaseApplication.getApplication()).load(info.getImages()).into(holder.iv_title);
        } else {
            throw new RuntimeException("there is no holer that match the  " + vh);
        }
    }

    @Override
    public int getItemCount() {
        return homeStoriesInfos.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position)) {
            return TYPE_HEADER;
        } else if (isPositionDate(position)) {
            return TYPE_DATE;
        } else {
            return TYPE_ITEM;
        }
    }

    private boolean isPositionDate(int position) {
        return position == 1;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    //设置viewPager
    private void setViewPager(final HeaderHolder holder) {
        holder.viewPager.setAdapter(new HomeViewPagerAdapter(homeTopInfos));
        holder.indicator.setCircleNumber(homeTopInfos.size());
        holder.viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                holder.indicator.setSelectdItem(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                    isTouchViewPager = true;
                } else {
                    isTouchViewPager = false;
                }
            }
        });
        loopViewPager(holder);
    }

    //让viewpager循环播放
    private void loopViewPager(final HeaderHolder holder) {
        BaseApplication.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isTouchViewPager == false) {
                    int item = (holder.viewPager.getCurrentItem()) + 1;
                    holder.viewPager.setCurrentItem(item % (holder.viewPager.getAdapter().getCount()));
                    holder.indicator.setSelectdItem(item);
                }
                loopViewPager(holder);
            }
        }, 5000);
    }

    //item view
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_title;
        public TextView tv_title;

        public MyViewHolder(View view) {
            super(view);
            iv_title = ButterKnife.findById(view, R.id.iv_title);
            tv_title = ButterKnife.findById(view, R.id.tv_title);
        }
    }

    //header view
    public class HeaderHolder extends RecyclerView.ViewHolder {
        public ViewPager viewPager;
        public CircleIndicator indicator;

        public HeaderHolder(View view) {
            super(view);
            viewPager = ButterKnife.findById(view, R.id.viewPager);
            indicator = ButterKnife.findById(view, R.id.indicator);
        }
    }

    //dateHolder view
    public class DateHolder extends RecyclerView.ViewHolder {
        public TextView tv_date;
        public DateHolder(View view) {
            super(view);
            tv_date = ButterKnife.findById(view, R.id.tv_date);
        }
    }
}
