package com.example.seaice.zhihuribao.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.seaice.zhihuribao.CommentPagerListener;
import com.example.seaice.zhihuribao.R;
import com.example.seaice.zhihuribao.Utils.BaseApplication;
import com.example.seaice.zhihuribao.bean.CommentsInfo;
import com.squareup.picasso.Picasso;
import java.util.List;
import butterknife.ButterKnife;

/**
 * Created by seaice on 2016/9/5.
 */
public class CommentRecylerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_LONG_HEADER = 0;
    private static final int TYPE_LONG_ITEM = 1;
    private static final int TYPE_SHORT_HEADER = 2;
    private static final int TYPE_SHORT_ITEM = 3;

    private CommentsInfo mLongCommentsInfo;
    private CommentsInfo mShortCommentInfo;

    private LayoutInflater mInflate;

    private CommentPagerListener mListener;

    public CommentRecylerViewAdapter(CommentsInfo mLongCommentsInfo, CommentsInfo mShortCommentInfo) {
        this.mLongCommentsInfo = mLongCommentsInfo;
        this.mShortCommentInfo = mShortCommentInfo;
        initData();
    }

    private void initData() {
        mInflate = LayoutInflater.from(BaseApplication.getApplication());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_LONG_HEADER) {
            View view = mInflate.inflate(R.layout.item_comment_header, parent, false);
            return new LongHeaderHolder(view);
        } else if (viewType == TYPE_SHORT_HEADER) {
            View view = mInflate.inflate(R.layout.item_comment_header, parent, false);
            return new ShortHeaderHolder(view);
        } else if (viewType == TYPE_LONG_ITEM) {
            View view = mInflate.inflate(R.layout.item_comment_view, parent, false);
            return new LongCommentsHolder(view);
        } else if (viewType == TYPE_SHORT_ITEM) {
            View view = mInflate.inflate(R.layout.item_comment_view, parent, false);
            return new ShortCommensHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof LongHeaderHolder) {
            LongHeaderHolder lvh = (LongHeaderHolder) holder;
            lvh.longHeaderText.setText(mLongCommentsInfo.getComments().size() + "条长评论");
        } else if (holder instanceof ShortHeaderHolder) {
            ShortHeaderHolder svh = (ShortHeaderHolder) holder;
            svh.shortHeaderText.setText(mShortCommentInfo.getComments().size() + "条短评论");
        } else if (holder instanceof LongCommentsHolder) {
            CommentsInfo.Comment comment = mLongCommentsInfo.getComments().get(position - 1);
            LongCommentsHolder lvh = (LongCommentsHolder) holder;
            lvh.setComtentView(comment);
        } else if (holder instanceof ShortCommensHolder) {
            CommentsInfo.Comment comment = mShortCommentInfo.getComments().get(position - mLongCommentsInfo.getComments().size() - 2);
            ShortCommensHolder lvh = (ShortCommensHolder) holder;
            lvh.setComtentView(comment);
        }
    }

    @Override
    public int getItemCount() {
        return mLongCommentsInfo.getComments().size() + mShortCommentInfo.getComments().size() + 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (isLongCommentHeader(position)) {
            return TYPE_LONG_HEADER;
        } else if (isShortCommentHeader(position)) {
            return TYPE_SHORT_HEADER;
        } else if (isLongCommentItem(position)) {
            return TYPE_LONG_ITEM;
        } else {
            return TYPE_SHORT_ITEM;
        }
    }

    //是否是短评论
    private boolean isShortCommentItem(int position) {
        List<CommentsInfo.Comment> comments = mShortCommentInfo.getComments();
        if (position > mLongCommentsInfo.getComments().size() + 1 && comments.size() > 0) {
            return true;
        }
        return false;
    }

    //是否是长评论
    private boolean isLongCommentItem(int position) {
        List<CommentsInfo.Comment> comments = mLongCommentsInfo.getComments();
        if (position > 0 && comments.size() > 0 && position <= comments.size()) {
            return true;
        }
        return false;
    }

    //是否是短评论的开头
    private boolean isShortCommentHeader(int position) {
        int count = mLongCommentsInfo.getComments().size() + 1;
        return position == count;
    }

    //是否是长评论的开头
    private boolean isLongCommentHeader(int position) {
        return position == 0;
    }

    //长评论
    public class LongCommentsHolder extends BaseCommentsHolder {
        protected LongCommentsHolder(View view) {
            super(view);
        }
    }

    //短评论
    public class ShortCommensHolder extends BaseCommentsHolder {
        protected ShortCommensHolder(View view) {
            super(view);
        }
    }

    //多少个长评论
    public class LongHeaderHolder extends RecyclerView.ViewHolder {
        public TextView longHeaderText;

        public LongHeaderHolder(View view) {
            super(view);
            longHeaderText = ButterKnife.findById(view, R.id.tv_comment_header);
        }
    }

    //多少个短评论
    public class ShortHeaderHolder extends RecyclerView.ViewHolder {
        public TextView shortHeaderText;

        public ShortHeaderHolder(View view) {
            super(view);
            shortHeaderText = ButterKnife.findById(view, R.id.tv_comment_header);
        }
    }

    public class BaseCommentsHolder extends RecyclerView.ViewHolder {
        public ImageView icon;
        public TextView author;
        public TextView likes;
        public TextView content;
        public TextView tvTime;
        public TextView tvExpand;

        protected BaseCommentsHolder(View view) {
            super(view);
            initView(view);
        }

        protected void initView(View view) {
            icon = ButterKnife.findById(view, R.id.iv_icon);
            author = ButterKnife.findById(view, R.id.tv_autor);
            likes = ButterKnife.findById(view, R.id.tv_likes);
            content = ButterKnife.findById(view, R.id.tv_content);
            tvTime = ButterKnife.findById(view, R.id.comment_time);
            tvExpand = ButterKnife.findById(view, R.id.tv_expand);
        }

        public void setComtentView(final CommentsInfo.Comment comment){
            Picasso.with(BaseApplication.getApplication()).load(comment.getAvatar()).into(this.icon);
            this.author.setText(comment.getAuthor());
            this.likes.setText(comment.getLikes());
            this.content.setText(comment.getContent());
            this.tvTime.setText(comment.getTime());
            this.tvExpand.setText("zhangkai");
            this.itemView.setBackgroundResource(R.drawable.ripple_bg);
            this.itemView.setClickable(true);
            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){
                        mListener.onCommentItemClick(v, comment);
                    }
                }
            });
        }
    }

    public void setItemClickListener(CommentPagerListener listener){
        this.mListener = listener;
    }
}
