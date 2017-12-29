package com.connxun.ttdj.ui.fragment.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.connxun.ttdj.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @Author anna
 * @Date 2017-12-02 17:52
 * @Description banner 跳转使用
 */
public class BannerDetailsActivity extends AppCompatActivity {
    @BindView(R.id.wv_content)
    WebView wvContent;

    public static void launch(Context context, String url) {
        Intent intent = new Intent(context, BannerDetailsActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_details);
        ButterKnife.bind(this);

        String url = getIntent().getStringExtra("url");
        if (url == null) return;

        initWebViewSettings();
        wvContent.loadUrl(url);
    }
    /**
     * Init WebView configs
     */
    private void initWebViewSettings() {
        WebSettings webSettings = wvContent.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setAllowFileAccess(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setNeedInitialFocus(true);

        wvContent.requestFocusFromTouch();
        wvContent.setWebViewClient(new WebViewClient());
        wvContent.setWebChromeClient(new WebChromeClient());

    }


}
