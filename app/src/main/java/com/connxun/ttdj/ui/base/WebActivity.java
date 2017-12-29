package com.connxun.ttdj.ui.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.FrameLayout;

import com.connxun.ttdj.R;
import com.connxun.ttdj.widget.AppTitle;
import com.just.library.AgentWeb;

import butterknife.BindView;

/**
 * Created by wushange on 2017/7/24.
 */

public class WebActivity extends BaseSwipeBackActivity {
    public static String WEB_URL_KEY = "FILE_URL";
    @BindView(R.id.fl_web)
    FrameLayout flWeb;
    @BindView(R.id.app_title_id)
    AppTitle appTitle;
    private String fileUrl = "";

    public static void callMe(Context context, @NonNull String webUrl) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(WEB_URL_KEY, webUrl);
        context.startActivity(intent);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_web;
    }

    @Override
    public void initParms(Bundle parms) {
        fileUrl = parms.getString(WEB_URL_KEY);
    }

    @Override
    public void initView(View view) {
        AgentWeb web =  AgentWeb.with(this)//传入Activity
                .setAgentWebParent(flWeb, new FrameLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams
                .useDefaultIndicator()// 使用默认进度条
                .defaultProgressBarColor() // 使用默认进度条颜色
                .setReceivedTitleCallback((view1, title) -> appTitle.setTitle(title)) //设置 Web 页面的 title 回调
                .createAgentWeb()//
                .ready()
                .go(fileUrl == null || fileUrl.length() == 0 ? "" : fileUrl);
        web.getAgentWebSettings().getWebSettings().setSupportZoom(true);
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void initInjector() {

    }

}
