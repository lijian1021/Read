package com.li.view.activity;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.li.base.BaseActivity;
import com.li.read.R;

import butterknife.BindView;

/**
 * Created by Li on 2017/3/1.
 * newsDetail
 */

public class NewsDetailActivity extends BaseActivity {
    @BindView(R.id.web_news_detail)
    WebView mWebNewsDetail;

    @Override
    protected void init() {
        String url = getIntent().getExtras().getString("url");
        mWebNewsDetail.loadUrl(url);
        mWebNewsDetail.setWebViewClient(new WebViewClient(){


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });

        mWebNewsDetail.getSettings().setJavaScriptEnabled(true);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_news_detail;
    }


}
