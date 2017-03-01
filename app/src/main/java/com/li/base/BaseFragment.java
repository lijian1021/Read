package com.li.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Li on 2017/2/28.
 * baseFragment
 */

public abstract class BaseFragment extends Fragment {

    private Unbinder mUnbinder;
    private boolean mFirstVisible = true;
    private CompositeSubscription mCompositeSubscription;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), null);
        mUnbinder = ButterKnife.bind(this, view);
        onCreateShow();
        CompositeSubscription compositeSubscription=new CompositeSubscription();
        return view;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            visibleDeal();
        } else {
            inVisibleDeal();
        }

        if (isVisibleToUser && mUnbinder != null && mFirstVisible) {
            mFirstVisible = false;
            firstVisibleDeal();
        }
    }

    protected abstract int getLayoutId();

    @Override
    public void onDestroy() {
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        unSubscription();
        super.onDestroy();

    }

    protected void visibleDeal() {
    }

    protected void inVisibleDeal() {
    }

    protected void firstVisibleDeal() {
    }

    protected void onCreateShow() {
    }

    protected void addSubscriptiom(Subscription subscription){
        mCompositeSubscription=mCompositeSubscription==null?new CompositeSubscription():mCompositeSubscription;
        mCompositeSubscription.add(subscription);
    }
    private void unSubscription(){
        if (mCompositeSubscription != null && !mCompositeSubscription.isUnsubscribed()) {

            mCompositeSubscription.unsubscribe();
        }
    }
}
