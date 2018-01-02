package com.connxun.ttdj.di.component;

import android.app.Activity;

import com.connxun.ttdj.di.PerActivity;
import com.connxun.ttdj.di.module.ActivityModule;
import com.connxun.ttdj.ui.allClassification.AllClassificationActivity;
import com.connxun.ttdj.ui.login.LoginActivity;
import com.connxun.ttdj.ui.register.RegisterActivity;
import com.connxun.ttdj.ui.releaseDemand.ReleaseDemandActivity;
import com.connxun.ttdj.ui.splash.SplashActivity;

import dagger.Component;


/**
 * @Author anna
 * @Date 2017-11-21 10:52
 * @Description 获取依赖对象，dependencies声明依赖关系
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    /**
     * inject方法：将依赖需求方对象送到Component类中，注入所需对象
     * 注：inject方法的参数不能使用父类接受
     * @param activity
     */
    void inject(SplashActivity activity);

    void inject(LoginActivity activity);

    void inject(RegisterActivity activity);

    void inject(ReleaseDemandActivity activity);

    void inject(AllClassificationActivity activity);
//
//    void inject(HelpActivity aboutActivity);
//
//    void inject(HomeSearchActivity activity);
//
//    void inject(ClassListActivity activity);
//
//    void inject(HomeHot_New_SpcActivity activity);
//
//    void inject(HomeCFActivity activity);
//
//    void inject(HomeBroseHistoryActivity activity);


}
