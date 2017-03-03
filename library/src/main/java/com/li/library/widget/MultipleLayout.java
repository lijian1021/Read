package com.li.library.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.li.library.R;

/**
 * Created by Li on 2017/3/2.
 * multipleLayout
 */

public class MultipleLayout extends RelativeLayout implements View.OnClickListener {

    public static final int STATUS_LOADING = 1;
    public static final int STATUS_SUCCESS = 2;
    public static final int STATUS_FAILURE = 3;
    public static final int STATUS_EMPTY = 4;

    public View getViewLoading() {
        return viewLoading;
    }

    public View getViewFailure() {
        return viewFailure;
    }

    public View getViewEmpty() {
        return viewEmpty;
    }

    private View viewLoading;
    private View viewContent;
    private View viewFailure;
    private View viewEmpty;


    private RelativeLayout.LayoutParams mLayoutParams;
    private OnMultipleClickListener mListener;

    public MultipleLayout(Context context) {
        super(context);
    }

    public MultipleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (mLayoutParams == null) {
            mLayoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            mLayoutParams.addRule(CENTER_IN_PARENT);
        }
        initAttr(context, attrs);
    }

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MultipleLayout);
        int loadingLayoutId = array.getResourceId(R.styleable.MultipleLayout_layout_loading, -1);
        int failLayoutId = array.getResourceId(R.styleable.MultipleLayout_layout_failure, -1);
        int emptyLayoutId = array.getResourceId(R.styleable.MultipleLayout_layout_empty, -1);

        viewLoading = LayoutInflater.from(context)
                .inflate(loadingLayoutId == -1 ? R.layout.layout_loading : loadingLayoutId, null);
        viewFailure = LayoutInflater.from(context)
                .inflate(failLayoutId == -1 ? R.layout.layout_failure : failLayoutId, null);
        viewEmpty = LayoutInflater.from(context)
                .inflate(emptyLayoutId == -1 ? R.layout.layout_empty : emptyLayoutId, null);

        addView(viewLoading, mLayoutParams);
        addView(viewFailure, mLayoutParams);
        addView(viewEmpty, mLayoutParams);
        array.recycle();

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int count = getChildCount();

        if (count > 0) {
            viewContent = getChildAt(count - 1);
            viewEmpty.setTag("empty");
            viewFailure.setTag("failure");
            viewEmpty.setOnClickListener(this);
            viewFailure.setOnClickListener(this);
            switchLayout(STATUS_LOADING);
        }
    }

    public void switchLayout(int layoutStatus) {
        viewLoading.setVisibility(layoutStatus == STATUS_LOADING ? VISIBLE : GONE);
        viewContent.setVisibility(layoutStatus == STATUS_SUCCESS ? VISIBLE : GONE);
        viewFailure.setVisibility(layoutStatus == STATUS_FAILURE ? VISIBLE : GONE);
        viewEmpty.setVisibility(layoutStatus == STATUS_EMPTY ? VISIBLE : GONE);
    }

    public void setOnMultipleClickListener(OnMultipleClickListener listener) {
        mListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            switch (v.getTag().toString()) {
                case "empty":
                    mListener.emptyClickListener();
                    break;
                case "failure":
                    mListener.failureClickListener();
                    break;
            }
        }
    }
    public interface OnMultipleClickListener {
        void emptyClickListener();
        void failureClickListener();
    }
}
