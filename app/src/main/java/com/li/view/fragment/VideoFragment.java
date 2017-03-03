package com.li.view.fragment;


import android.view.View;
import android.widget.Toast;

import com.li.base.BaseFragment;
import com.li.library.widget.MultipleLayout;
import com.li.library.widget.TopBar;
import com.li.read.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Li on 2017/2/28.
 */

public class VideoFragment extends BaseFragment {
    @BindView(R.id.layout_multiple)
    MultipleLayout mLayoutMultiple;
    @BindView(R.id.top_bar)
    TopBar mTopBar;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    protected void firstVisibleDeal() {
        super.firstVisibleDeal();
        mTopBar.setOnTopBarListener(new TopBar.OnTopBarListener() {
            @Override
            public void onNavigationIconClickListener() {
                Toast.makeText(getActivity(), "click", Toast.LENGTH_SHORT).show();
            }
        });
        mLayoutMultiple.setOnMultipleClickListener(new MultipleLayout.OnMultipleClickListener() {
            @Override
            public void emptyClickListener() {
                mLayoutMultiple.switchLayout(MultipleLayout.STATUS_LOADING);
            }

            @Override
            public void failureClickListener() {
                mLayoutMultiple.switchLayout(MultipleLayout.STATUS_SUCCESS);
            }
        });
    }

    @OnClick({R.id.show_content, R.id.loading, R.id.loading_success, R.id.loading_failure})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.show_content:
                mLayoutMultiple.switchLayout(MultipleLayout.STATUS_EMPTY);
                break;
            case R.id.loading:
                mLayoutMultiple.switchLayout(MultipleLayout.STATUS_LOADING);
                break;
            case R.id.loading_success:
                mLayoutMultiple.switchLayout(MultipleLayout.STATUS_SUCCESS);
                break;
            case R.id.loading_failure:
                mLayoutMultiple.switchLayout(MultipleLayout.STATUS_FAILURE);
                break;
        }
    }


}
