package com.li.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.li.entity.NewsEntity;
import com.li.read.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Li on 2017/3/1.
 * news
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    private OnItemClickListener mOnItemClickListener;
    public NewsAdapter(List<NewsEntity> newsEntityList, Context context) {
        mNewsEntityList = newsEntityList;
        mContext = context;
    }

    public void setNewsEntityList(List<NewsEntity> newsEntityList) {
        mNewsEntityList = newsEntityList;
        notifyItemRangeChanged(0,newsEntityList.size());
    }

    private List<NewsEntity> mNewsEntityList;
    private Context mContext;
    @Override
    public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_item_list, null);
        return new NewsHolder(view);
    }


    @Override
    public void onBindViewHolder(NewsHolder holder, final int position) {
        final NewsEntity newsEntity=mNewsEntityList.get(position);
        Glide.with(mContext).load(newsEntity.getPicUrl()).into(holder.mImg);
        holder.mTvTitle.setText(newsEntity.getTitle());
        holder.mTvDescription.setText(newsEntity.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(newsEntity.getUrl());

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNewsEntityList == null ? 0 : mNewsEntityList.size();
    }

    class NewsHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView mImg;
        @BindView(R.id.tv_title)
        TextView mTvTitle;
        @BindView(R.id.tv_description)
        TextView mTvDescription;
        public NewsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(String url);
    }

}
