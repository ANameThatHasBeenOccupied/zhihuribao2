package com.example.seaice.zhihuribao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.seaice.zhihuribao.HomePagerClickListener;
import com.example.seaice.zhihuribao.R;
import com.example.seaice.zhihuribao.Utils.BaseApplication;
import com.example.seaice.zhihuribao.Utils.LogUtils;
import com.example.seaice.zhihuribao.bean.ThemeInfo;

import butterknife.ButterKnife;

/**
 * Created by seaice on 2016/8/22.
 */
public class MenuRecylerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HOME = 0;
    private static final int TYPE_ITEM = 1;

    private LayoutInflater mInflate;
    private Context mContext;
    private ThemeInfo themeInfo;
    private static int mSelectedPos = 0;//生成新的adapter时 这个还是保持不变
    private HomePagerClickListener mListener;

    public MenuRecylerViewAdapter(ThemeInfo themeInfo) {
        this.themeInfo = themeInfo;
        initData();
    }

    private void initData() {
        mContext = BaseApplication.getApplication();
        mInflate = LayoutInflater.from(mContext);
    }

    //绑定不同类型Holder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HOME) {
            View view = mInflate.inflate(R.layout.menu_home_item, parent, false);
            return new MenuHomeHolder(view);
        } else if (viewType == TYPE_ITEM) {
            View view = mInflate.inflate(R.layout.menu_theme_item, parent, false);
            view.setBackgroundResource(R.color.blue);
            return new MenuThemeHolder(view);
        }
        throw new RuntimeException("there is no type that match the type " + viewType);
    }

    //设置holder的view元素
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vh, int position) {
        if (vh instanceof MenuThemeHolder) {
            MenuThemeHolder holder = (MenuThemeHolder) vh;
            holder.tv_menu_theme.setText(themeInfo.getOthers().get(position - 1).getName());
            setThemeItemBackground(holder.itemView, position);
        } else if (vh instanceof MenuHomeHolder) {
            MenuHomeHolder holder = (MenuHomeHolder) vh;
            setThemeItemBackground(holder.itemView, position);
        } else {
            throw new RuntimeException("there is no holer that match the  " + vh);
        }
    }

    //设置选中时对应Item的背景颜色
    private void setThemeItemBackground(View view, int position) {
        if (mSelectedPos == position) {
            view.setBackgroundResource(R.color.light_grey);
        } else {
            view.setBackgroundResource(R.color.white);
        }
    }

    @Override
    public int getItemCount() {
        return themeInfo.getOthers().size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isHomeItemPos(position)) {
            return TYPE_HOME;
        } else {
            return TYPE_ITEM;
        }
    }

    //是否第一条数据
    private boolean isHomeItemPos(int position) {
        return position == 0;
    }

    //显示主题的Holder
    public class MenuThemeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView iv_menu_follow;
        public TextView tv_menu_theme;

        public MenuThemeHolder(View view) {
            super(view);
            iv_menu_follow = ButterKnife.findById(view, R.id.iv_menu_follow);
            tv_menu_theme = ButterKnife.findById(view, R.id.tv_menu_theme);
            iv_menu_follow.setOnClickListener(this);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.iv_menu_follow:
                    mListener.onSlideMenuAddClick(v, mSelectedPos);
                    break;
                case R.id.ll_menu:
                    mSelectedPos = this.getAdapterPosition();
                    notifyDataSetChanged();
                    mListener.onSlideMenuItemClick(v, mSelectedPos);
                    break;
                default:
                    break;
            }
        }
    }

    //显示Home的条目
    public class MenuHomeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tv_menu_home;

        public MenuHomeHolder(View view) {
            super(view);
            tv_menu_home = ButterKnife.findById(view, R.id.tv_menu_home);
            view.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            LogUtils.e("MenuHomeHolder");
            mSelectedPos = this.getAdapterPosition();
            notifyDataSetChanged();
            if (mListener != null) {
                mListener.onSlideMenuItemClick(v, mSelectedPos);
            }
        }
    }

    //设置条目的监听器
    public void setRecylerViewListener(HomePagerClickListener listener) {
        this.mListener = listener;
    }

}
