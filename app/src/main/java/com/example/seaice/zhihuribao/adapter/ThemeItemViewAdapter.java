package com.example.seaice.zhihuribao.adapter;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.seaice.zhihuribao.HomePagerClickListener;
import com.example.seaice.zhihuribao.R;
import com.example.seaice.zhihuribao.Utils.BaseApplication;
import com.example.seaice.zhihuribao.Utils.HttpUtils;
import com.example.seaice.zhihuribao.Utils.LogUtils;
import com.example.seaice.zhihuribao.bean.ThemeItemInfo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by seaice on 2016/8/22.
 */
public class ThemeItemViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_EDITOR = 2;

    private int MAX_EDITOR_NUM = 9;
    private LayoutInflater mInflate;
    private Context mContext;
    private ThemeItemInfo themeItemInfo;
    private HomePagerClickListener mListener;

    public ThemeItemViewAdapter(ThemeItemInfo themeItemInfo) {
        this.themeItemInfo = themeItemInfo;
        initData();
    }

    private void initData() {
        mContext = BaseApplication.getApplication();
        mInflate = LayoutInflater.from(mContext);
    }

    //绑定不同类型Holder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View view = mInflate.inflate(R.layout.theme__item_header, parent, false);
            return new HeaderHolder(view);
        } else if (viewType == TYPE_ITEM) {
            View view = mInflate.inflate(R.layout.item_recyler_view, parent, false);
            return new ItemHolder(view);
        } else if (viewType == TYPE_EDITOR) {
            View view = mInflate.inflate(R.layout.theme__item_editor, parent, false);
            return new EditorHolder(view);
        }
        throw new RuntimeException("there is no type that match the type " + viewType);
    }

    //设置holder的view元素
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vh, int position) {
        if (vh instanceof HeaderHolder) {
            HeaderHolder holder = (HeaderHolder) vh;
            holder.tv_theme_header.setText(themeItemInfo.getDescription());
            HttpUtils.loadImageFromUrl(themeItemInfo.getImage(), holder.iv_theme_header);
        } else if (vh instanceof ItemHolder) {
            ThemeItemInfo.ThemeStory story = themeItemInfo.getStories().get(position - 2);
            ItemHolder holder = (ItemHolder) vh;
            holder.tv_title.setText(story.getTitle());
            if (story.getImages() != null && story.getImages().size() > 0) {
                HttpUtils.loadImageFromUrl(story.getImages().get(0), holder.iv_title);
            }
        } else if (vh instanceof EditorHolder) {
            EditorHolder holder = (EditorHolder) vh;
            List<ThemeItemInfo.Editor> editorList = themeItemInfo.getEditors();
            int size = editorList.size();
            for (int i = 0; i < MAX_EDITOR_NUM; i++) {
                if (i < size) {
                    holder.iv[i].setVisibility(View.VISIBLE);
                    HttpUtils.loadImageFromUrl(editorList.get(i).getAvatar(), holder.iv[i]);
                } else {
                    holder.iv[i].setVisibility(View.INVISIBLE);
                }
            }
        } else {
            throw new RuntimeException("there is no holer that match the  " + vh);
        }
    }

    @Override
    public int getItemCount() {
        return themeItemInfo.getStories().size() + 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderItemPos(position)) {
            return TYPE_HEADER;
        } else if (isEditorItemPos(position)) {
            return TYPE_EDITOR;
        } else {
            return TYPE_ITEM;
        }
    }

    //是否第一条数据
    private boolean isHeaderItemPos(int position) {
        return position == 0;
    }

    //是否是显示主编的条目位置
    private boolean isEditorItemPos(int position) {
        return position == 1;
    }

    //显示主题的Holder
    public class HeaderHolder extends RecyclerView.ViewHolder {
        public ImageView iv_theme_header;
        public TextView tv_theme_header;

        public HeaderHolder(View view) {
            super(view);
            iv_theme_header = ButterKnife.findById(view, R.id.iv_theme_header);
            tv_theme_header = ButterKnife.findById(view, R.id.tv_theme_header);
        }
    }

    //显示Home的条目
    public class EditorHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tv_theme_editor;
        public ImageView[] iv = new ImageView[MAX_EDITOR_NUM];

        public EditorHolder(View view) {
            super(view);
            tv_theme_editor = ButterKnife.findById(view, R.id.tv_theme_editor);
            iv[0] = ButterKnife.findById(view, R.id.editor1);
            iv[1] = ButterKnife.findById(view, R.id.editor2);
            iv[2] = ButterKnife.findById(view, R.id.editor3);
            iv[3] = ButterKnife.findById(view, R.id.editor4);
            iv[4] = ButterKnife.findById(view, R.id.editor5);
            iv[5] = ButterKnife.findById(view, R.id.editor6);
            iv[6] = ButterKnife.findById(view, R.id.editor7);
            iv[7] = ButterKnife.findById(view, R.id.editor8);
            iv[8] = ButterKnife.findById(view, R.id.editor9);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.onHomePagerEditorClick();
        }
    }

    //条目信息
    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView iv_title;
        public TextView tv_title;

        public ItemHolder(View view) {
            super(view);
            iv_title = ButterKnife.findById(view, R.id.iv_title);
            tv_title = ButterKnife.findById(view, R.id.tv_title);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onHomePagerItemClick(true, this.getLayoutPosition() - 2);
            }
        }
    }

    //设置点击事件的回调
    public void setHomePagerClickListener(HomePagerClickListener listener) {
        this.mListener = listener;
    }
}
