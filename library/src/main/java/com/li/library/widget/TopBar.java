package com.li.library.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.li.library.R;

/**
 * Created by Li on 2017/3/3.
 * topBar
 * contain:navigation_icon;navigation_title;navigation_description
 */

public class TopBar extends RelativeLayout implements View.OnClickListener {
    public ImageView getNavigationIcon() {
        return navigationIcon;
    }

    public void setNavigationIcon(ImageView navigationIcon) {
        this.navigationIcon = navigationIcon;
    }

    public TextView getNavigationDescription() {
        return navigationDescription;
    }

    public void setNavigationDescription(TextView navigationDescription) {
        this.navigationDescription = navigationDescription;
    }

    public TextView getNavigationTitle() {
        return navigationTitle;
    }

    public void setNavigationTitle(TextView navigationTitle) {
        this.navigationTitle = navigationTitle;
    }

    private ImageView navigationIcon;
    private TextView navigationDescription;
    private TextView navigationTitle;
    private int defaultSize= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,13,getResources().getDisplayMetrics());

    public TopBar(Context context) {
        super(context);
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_top_bar,this);
        navigationIcon= (ImageView) findViewById(R.id.navigation_icon);
        navigationDescription= (TextView) findViewById(R.id.navigation_description);
        navigationTitle= (TextView) findViewById(R.id.navigation_title);
        initAttrs(context,attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray array=context.obtainStyledAttributes(attrs,R.styleable.TopBar);
        int iconResId=array.getResourceId(R.styleable.TopBar_navigation_icon,R.drawable.back);
        String description=array.getString(R.styleable.TopBar_navigation_description);
        String title=array.getString(R.styleable.TopBar_navigation_title);
        int titleColor=array.getColor(R.styleable.TopBar_navigation_title_color, Color.BLACK);
        int titleSize=array.getDimensionPixelSize(R.styleable.TopBar_navigation_title_size, defaultSize);

        int desColor=array.getColor(R.styleable.TopBar_navigation_des_color,Color.BLACK);
        int desSize=array.getDimensionPixelSize(R.styleable.TopBar_navigation_des_size, defaultSize);

        navigationIcon.setImageResource(iconResId);
        navigationDescription.setText(description);
        navigationDescription.setTextColor(desColor);
        navigationDescription.setTextSize(TypedValue.COMPLEX_UNIT_PX,desSize);

        Log.e("tag",desSize+" "+titleSize);
        navigationTitle.setText(title);
        navigationTitle.setTextColor(titleColor);
        navigationTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX,titleSize);
        navigationIcon.setOnClickListener(this);
        array.recycle();
    }


    public void setOnTopBarListener(OnTopBarListener onTopBarListener) {
        mOnTopBarListener = onTopBarListener;
    }

    private OnTopBarListener mOnTopBarListener;

    @Override
    public void onClick(View v) {
        if (mOnTopBarListener != null) {
            mOnTopBarListener.onNavigationIconClickListener();
        }
    }

    public interface OnTopBarListener{
        void onNavigationIconClickListener();
    }

}
