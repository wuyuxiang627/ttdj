package com.connxun.ttdj.ui.allClassification;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;

import com.connxun.ttdj.R;
import com.connxun.ttdj.entity.CategoryMenu;
import com.connxun.ttdj.entity.CategorySub;
import com.connxun.ttdj.ui.base.BaseSwipeBackActivity;
import com.connxun.ttdj.ui.fragment.publish.PublishCardContract;
import com.connxun.ttdj.ui.fragment.publish.PublishCardPresenter;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by connxun-16 on 2018/1/2.
 */

/**
 * 全部分类
 */
public class AllClassificationActivity  extends BaseSwipeBackActivity implements PublishCardContract.HomeView,Validator.ValidationListener{

    @BindView(R.id.tl_all_classification_title)
    TabLayout tlClassificationTitle; //标题栏


    @Inject
    PublishCardPresenter presenter;

    @Override
    public void startLoading() {

    }

    @Override
    public void endLoading() {

    }

    @Override
    public void onError(String error) {

    }

    @Override
    public void showCategoryMenuList(List<CategoryMenu> carouseMenus) {
        //设置颜色
        tlClassificationTitle.setTabTextColors(AllClassificationActivity.this.getResources().getColor(R.color.find_msg)
                ,AllClassificationActivity.this.getResources().getColor(R.color.check_true));
        for (int i = 0; i < carouseMenus.size(); i++) {
            tlClassificationTitle.addTab(tlClassificationTitle.newTab().setText(carouseMenus.get(i).getName()));
        }

    }

    @Override
    public void showCategoryMenuSubList(List<CategorySub> carouseMenus) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_all_classification;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void doBusiness(Context mContext) {
        presenter.attachView(this);
        presenter.getCategoryList();
    }

    @Override
    public void initInjector() {
        getComponent().inject(this);

    }

    @Override
    public void onValidationSucceeded() {

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {

    }
}
