package com.li.view.fragment;


import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.li.base.BaseFragment;
import com.li.read.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Li on 2017/2/28.
 * NewsFragment
 */

public class NewsFragment extends BaseFragment {
    @BindView(R.id.layout_tab)
    TabLayout mLayoutTab;
    @BindView(R.id.pager_news)
    ViewPager mPagerNews;
    private List<String> mTypes;
    private List<String> mName;
    private List<ItemFragment> mItemFragments;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void onCreateShow() {
        init();
    }


    protected void init() {
        mItemFragments = new ArrayList<>();
        mTypes = Arrays.asList("social", "guonei", "world", "huabian",
                "tiyu", "mobile", "qiwen", "it");
        mName = Arrays.asList("社会", "国内", "国际", "娱乐", "体育", "移动互联", "奇闻", "IT");
        mPagerNews.setOffscreenPageLimit(mTypes.size());

        mLayoutTab.setTabMode(TabLayout.MODE_SCROLLABLE);
        mLayoutTab.setSelectedTabIndicatorColor(Color.parseColor("#fe8a25"));
        mLayoutTab.setTabTextColors(Color.BLACK, Color.parseColor("#fe8a25"));

        for (int i = 0; i < mTypes.size(); i++) {
            mItemFragments.add(ItemFragment.newInstance(mTypes.get(i)));
        }
        mPagerNews.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mItemFragments.get(position);
            }

            @Override
            public int getCount() {
                return mItemFragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mName.get(position);
            }
        });
        mLayoutTab.setupWithViewPager(mPagerNews);

    }
}
