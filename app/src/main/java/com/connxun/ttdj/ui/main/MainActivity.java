package com.connxun.ttdj.ui.main;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewCompat;
import android.view.KeyEvent;
import android.view.View;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.connxun.ttdj.R;
import com.connxun.ttdj.di.HasComponent;
import com.connxun.ttdj.di.component.DaggerFragmentComponent;
import com.connxun.ttdj.di.component.FragmentComponent;
import com.connxun.ttdj.ui.base.BaseActivity;
import com.connxun.ttdj.ui.base.ViewPagerAdapter;
import com.connxun.ttdj.ui.fragment.center.CenterFragment;
import com.connxun.ttdj.ui.fragment.home.HomeFragment;
import com.connxun.ttdj.ui.fragment.message.MessageFragment;
import com.connxun.ttdj.ui.fragment.publish.PublishCardFragment;
import com.connxun.ttdj.widget.MyViewPager;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;



/**
 * 首页
 */
public class MainActivity extends BaseActivity implements HasComponent<FragmentComponent> {

    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    MyViewPager viewpager;
    private ViewPagerAdapter adapter;
    private FragmentComponent mMainComponent;

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initParms(Bundle parms) {
    }

    @Override
    public void initView(View view) {
        setTouchDissIm(true);
        BarUtils.setStatusBarAlpha(this,0);

//        BarUtils.setTransparentPadding(this);
//        BarUtils.setStatusBarTextColor(getContext(), true);
        tablayout.setTabMode(TabLayout.MODE_FIXED);
        tablayout.setSelectedTabIndicatorHeight(0);
        ViewCompat.setElevation(tablayout, 10);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(HomeFragment.getInstance(), "首页");
        adapter.addFragment(PublishCardFragment.getInstance(), "发布促销");
        adapter.addFragment(MessageFragment.getInstance(), "消息");
        adapter.addFragment(CenterFragment.getInstance(), "个人中心");

        viewpager.setOffscreenPageLimit(4);
        viewpager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewpager);
        tablayout.getTabAt(0).setIcon(R.drawable.tab_index);
        tablayout.getTabAt(1).setIcon(R.drawable.tab_card);
        tablayout.getTabAt(2).setIcon(R.drawable.tab_msg);
        tablayout.getTabAt(3).setIcon(R.drawable.tab_center);

    }

    @Override
    public void doBusiness(Context mContext) {

        new RxPermissions(getContext()).request(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.READ_PHONE_STATE
//                ,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE
        ).subscribe(granded -> {
            if (granded) {
            } else {
                Toast("请先授予应用相关权限");
                mOperation.showBasicDialog(R.string.error, R.string.grant_permission, (dialog, which) -> {
//                            Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);    //飞行模式，无线网和网络设置界面
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);   //跳转位置服务界面
                    startActivity(intent);
                });
            }
        });

        Logger.addLogAdapter(new AndroidLogAdapter());

        Logger.d("___________________hello");

//        CrashReport.initCrashReport(getApplicationContext());
//        CrashReport.testJavaCrash();

//        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "lenve.db", null);
//        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
//        DaoSession daoSession = daoMaster.newSession();
//
//        VideoClassDao videoClassDao=daoSession.getVideoClassDao();
//
//        VideoClass user = new VideoClass();
//        videoClassDao.insert(user);

    }

    @Override
    public void initInjector() {
        mMainComponent = DaggerFragmentComponent.builder()
                .applicationComponent(getApplicationComponent())
                .build();
        mMainComponent.inject(this);
    }

    @Override
    public FragmentComponent getFragmentComponent() {
        return mMainComponent;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode,KeyEvent event){
        //测试热修复
//        Toast.makeText(this,BugClass.getString(),Toast.LENGTH_SHORT).show();
        ToastUtils.showLong("热修复啦");
        if (keyCode==KeyEvent.KEYCODE_BACK) {
            mOperation.showBasicDialog("退出应用?", (dialog, which) -> {
                finish();
            });
        }
        return false;
    }

}
