package com.connxun.ttdj.ui.fragment.publish;

import com.connxun.ttdj.api.AppApi;
import com.connxun.ttdj.di.PerActivity;
import com.connxun.ttdj.ui.base.BasePresenter;

import javax.inject.Inject;


/**
 * Created by wanglj on 16/7/4.
 */


@PerActivity
public class PublishCardPresenter extends BasePresenter<PublishCardContract.HomeView> implements PublishCardContract.PublishCardPresenter {

    private AppApi api;

    @Inject
    public PublishCardPresenter(AppApi api) {
        this.api = api;
    }


    @Override
    public void getCategoryList() {
        mDisposable.add(api.getCarouseMenu().subscribe(categoryMenus -> mView.showCategoryMenuList(categoryMenus),
                throwable -> mView.onError(throwable.getMessage())));
    }

    @Override
    public void getCategorySubList(String id) {
        mDisposable.add(api.getCarouseMenuSub(id).subscribe(categorySubs -> mView.showCategoryMenuSubList(categorySubs),
                throwable -> mView.onError(throwable.getMessage())));
    }

}
