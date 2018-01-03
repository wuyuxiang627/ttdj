package com.connxun.ttdj.ui.allClassification;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.connxun.ttdj.R;
import com.connxun.ttdj.entity.CategoryMenu;
import com.connxun.ttdj.entity.CategorySub;
import com.connxun.ttdj.ui.adapter.allClassificatioin.MyAllClassificationAdapter;
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
public class AllClassificationActivity extends BaseSwipeBackActivity implements PublishCardContract.HomeView,Validator.ValidationListener{

    @BindView(R.id.lv_all_classification_listview)
    ListView lvAllClassification; //标题栏


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
        MyAllClassificationAdapter myAllClassificationAdapter = new MyAllClassificationAdapter(this,carouseMenus);
        lvAllClassification.setAdapter(myAllClassificationAdapter);
        myAllClassificationAdapter.setSelectedPosition(0);
        myAllClassificationAdapter.notifyDataSetChanged();
        lvAllClassification.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                myAllClassificationAdapter.setSelectedPosition(position);
                myAllClassificationAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void showCategoryMenuSubList(List<CategorySub> carouseMenus) {

    }

//    @Override
//    public void showublishCard(String successs) {
//
//    }

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
