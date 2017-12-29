package com.connxun.ttdj.ui.fragment.home;

import com.connxun.ttdj.api.AppApi;
import com.connxun.ttdj.di.PerActivity;
import com.connxun.ttdj.ui.base.BasePresenter;

import javax.inject.Inject;


/**
 * Created by wanglj on 16/7/4.
 */


@PerActivity
public class HomePresenter extends BasePresenter<HomeContract.HomeView> implements HomeContract.HomePresenter {

    private AppApi api;

    @Inject
    public HomePresenter(AppApi api) {
        this.api = api;
    }

    @Override
    public void getCategoryMenuList() {
        mDisposable.add(api.getCarouseMenu().subscribe(categoryMenus -> mView.showCategoryMenuList(categoryMenus),
                throwable -> mView.onError(throwable.getMessage())));
    }

    @Override
    public void getBannerList() {
        mDisposable.add(api.getBannerList().subscribe(banners -> mView.showBannerList(banners),
                throwable -> mView.onError(throwable.getMessage())));
    }

    @Override
    public void getHotCard(int page,int length) {
        mDisposable.add(api.getHotCard(page,length).subscribe(cards -> mView.showHotCardList(cards),
                throwable -> mView.onError(throwable.getMessage())));
    }


}
