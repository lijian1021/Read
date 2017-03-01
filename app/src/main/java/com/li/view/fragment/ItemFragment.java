package com.li.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.li.adapter.NewsAdapter;
import com.li.base.BaseFragment;
import com.li.base.HttpResultFunc;
import com.li.entity.NewsEntity;
import com.li.net.RetrofitHelper;
import com.li.net.Url;
import com.li.read.R;
import com.li.view.activity.NewsDetailActivity;

import java.util.List;

import butterknife.BindView;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/3/1.
 */

public class ItemFragment extends BaseFragment {
    @BindView(R.id.view_news)
    RecyclerView mViewNews;
    private int mCurrentPage = 1;
    private static final int mPageNum = 20;
    private List<NewsEntity> mNewsEntityList=null;
    private NewsAdapter mNewsAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_item;
    }

    public static ItemFragment newInstance(String type) {

        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        ItemFragment fragment = new ItemFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void onCreateShow() {
        super.onCreateShow();
        if (getArguments() != null) {
            String type = getArguments().getString("type");
            assert type != null;
            if (type.equals("social")) {
                init();
                loadNewsByType(type);
            }
        }
    }

    @Override
    protected void firstVisibleDeal() {
        super.firstVisibleDeal();
        if (getArguments() != null) {
            String type = getArguments().getString("type");
            assert type != null;
            if (!type.equals("social")) {
                init();
                loadNewsByType(type);
            }
        }
    }

    void init(){
        mViewNews.setLayoutManager(new LinearLayoutManager(getActivity()));
        mNewsAdapter=new NewsAdapter(mNewsEntityList,getActivity());
        mViewNews.setAdapter(mNewsAdapter);
        mNewsAdapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String url) {
                Intent intent=new Intent(getActivity(), NewsDetailActivity.class);

                intent.putExtra("url",url);
                startActivity(intent);
            }
        });
    }
    void loadNewsByType(String type) {

        Subscription subscription = RetrofitHelper.getNews()
                .getContent(type, Url.KEY, mPageNum, mCurrentPage)
                .map(new HttpResultFunc<List<NewsEntity>>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<NewsEntity>>() {
                    @Override
                    public void call(List<NewsEntity> newsEntities) {
                        mNewsAdapter.setNewsEntityList(newsEntities);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("tag", throwable.getMessage());
                    }
                });
        addSubscriptiom(subscription);
    }






}
