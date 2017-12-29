package com.connxun.ttdj.di.module;

import com.connxun.ttdj.api.AppApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * @Author anna
 * @Date 2017-11-21 20:46
 * @Description  传入OkHttpClient用以提供Retrofit
 */
@Module
public class ApiModule {

    @Provides
    @Singleton
    public AppApi provideAppApi(OkHttpClient okHttpClient) {
        return new AppApi( okHttpClient);
    }

}
