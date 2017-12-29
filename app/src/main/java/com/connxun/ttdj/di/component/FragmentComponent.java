package com.connxun.ttdj.di.component;


import com.connxun.ttdj.di.PerActivity;
import com.connxun.ttdj.di.module.ActivityModule;
import com.connxun.ttdj.ui.fragment.center.CenterFragment;
import com.connxun.ttdj.ui.fragment.home.HomeFragment;
import com.connxun.ttdj.ui.fragment.message.MessageFragment;
import com.connxun.ttdj.ui.fragment.publish.PublishCardFragment;
import com.connxun.ttdj.ui.main.MainActivity;

import dagger.Component;

/**
 * @Author anna
 * @Date 2017-11-21 10:53
 * @Description 需要dagger注入的fragment
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class})
public interface FragmentComponent {
    void inject(MainActivity activity);

//    void inject(ClassDetailActivity aboutActivity);

    void inject(HomeFragment fragment);

    void inject(PublishCardFragment fragment);

    void inject(MessageFragment fragment);

    void inject(CenterFragment fragment);

//    void inject(MyStudyFragment fragment);
//
//    void inject(MyDownloadFragment fragment);
//
//    void inject(MyClassFragment fragment);
//
//    void inject(ClassDetailFragment fragment);
//
//    void inject(SettingFragment fragment);
//
//    void inject(ClassListlFragment fragment);


}
