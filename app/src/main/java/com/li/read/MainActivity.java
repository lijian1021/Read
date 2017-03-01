package com.li.read;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.li.base.BaseFragment;
import com.li.view.fragment.NewsFragment;
import com.li.view.fragment.VideoFragment;
import com.li.view.fragment.MineFragment;
import com.li.widget.FixedViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.view_pager_content)
    FixedViewPager mViewPagerContent;
    @BindView(R.id.bottom_navigation)
    BottomNavigationBar mBottomNavigation;
    private List<BaseFragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        initBottomNavigationBar();
        initViewPager();
    }

    private void initData() {
        mFragments=new ArrayList<>();
        mFragments.add(new NewsFragment());
        mFragments.add(new VideoFragment());
        mFragments.add(new MineFragment());
    }

    /**
     * 初始化底部导航栏
     */
    private void initBottomNavigationBar() {
        mBottomNavigation
                .addItem(new BottomNavigationItem(R.drawable.ic_bottom_navigation_news, "资讯"))
                .addItem(new BottomNavigationItem(R.drawable.ic_bottom_navigation_video, "视频"))
                .addItem(new BottomNavigationItem(R.drawable.ic_bottom_navigation_mine, "我的"))
                .setFirstSelectedPosition(0)
                .initialise();
        mBottomNavigation.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                if (mViewPagerContent.getCurrentItem() != position) {

                    mViewPagerContent.setCurrentItem(position,false);

                }
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    /**
     * 初始化ViewPager以及添加回调
     */
    private void initViewPager() {
        mViewPagerContent.setOffscreenPageLimit(mFragments.size());
        mViewPagerContent.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        });

        mViewPagerContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (mBottomNavigation.getCurrentSelectedPosition() != position) {
                    mBottomNavigation.selectTab(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
