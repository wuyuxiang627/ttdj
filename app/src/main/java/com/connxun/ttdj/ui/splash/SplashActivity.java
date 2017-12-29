package com.connxun.ttdj.ui.splash;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.LogUtils;
import com.connxun.ttdj.R;
import com.connxun.ttdj.api.AppApi;
import com.connxun.ttdj.constants.Constants;
import com.connxun.ttdj.data.ACache;
import com.connxun.ttdj.entity.PUser;
import com.connxun.ttdj.ui.base.BaseActivity;
import com.connxun.ttdj.ui.main.MainActivity;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @Author anna
 * @Date 2017-12-07 10:35
 * @Description 启动页
 */
public class SplashActivity extends BaseActivity {

    @Inject
    AppApi api;

    @Override
    public int bindLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void initParms(Bundle parms) {
    }

    @Override
    public void initView(View view) {
        //设置状态栏透明度
        BarUtils.setStatusBarAlpha(this, 0);
    }

    @Override
    public void doBusiness(Context mContext) {
        //在flash页面加载数据传给home页
        api.getCarouseMenu().subscribe(categoryMenus -> mOperation.addParameter("categoryMenuList", (Serializable) categoryMenus),
                throwable -> LogUtils.e(throwable.getMessage()));
        api.getBannerList().subscribe(banners -> mOperation.addParameter("carouselList", (Serializable) banners),
                throwable -> LogUtils.e(throwable.getMessage()));
        api.getHotCard(0,15).subscribe(cards -> mOperation.addParameter("pCard",  (Serializable)cards),
                throwable -> LogUtils.e(throwable.getMessage()));

        PUser pUser = (PUser) ACache.get(SplashActivity.this).getAsObject(Constants.USER_ID);
        Observable.timer(2, TimeUnit.SECONDS).subscribe(aLong -> {
            if (pUser != null) {
                mOperation.forwardAndFinish(MainActivity.class, LEFT_RIGHT);
            } else {
                mOperation.forwardAndFinish(MainActivity.class, LEFT_RIGHT);
            }
        });
    }

    @Override
    public void initInjector() {
        getComponent().inject(this);
    }


}
