package com.connxun.ttdj.di.component;

import android.content.Context;
import android.view.LayoutInflater;

import com.connxun.ttdj.MyApplication;
import com.connxun.ttdj.api.AppApi;
import com.connxun.ttdj.di.module.ApiModule;
import com.connxun.ttdj.di.module.ApplicationModule;
import com.connxun.ttdj.di.module.DBModule;
import com.connxun.ttdj.ui.base.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @Author anna
 * @Date 2017-11-21 10:53
 * @Description 获取依赖对象
 */
@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class, DBModule.class})
public interface ApplicationComponent {
    Context getContext();

    LayoutInflater getLayoutInflater();

    AppApi getAccountApi();


    void inject(MyApplication mApplication);

    void inject(BaseActivity mBaseActivity);
}
