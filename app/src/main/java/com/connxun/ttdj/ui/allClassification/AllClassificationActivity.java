package com.connxun.ttdj.ui.allClassification;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.connxun.ttdj.MyApplication;
import com.connxun.ttdj.R;
import com.connxun.ttdj.entity.CategoryMenu;
import com.connxun.ttdj.entity.CategorySub;
import com.connxun.ttdj.entity.PublishCardResponse;
import com.connxun.ttdj.ui.adapter.allClassificatioin.MyAllClassificationAdapter;
import com.connxun.ttdj.ui.base.BaseSwipeBackActivity;
import com.connxun.ttdj.ui.fragment.publish.PublishCardContract;
import com.connxun.ttdj.ui.fragment.publish.PublishCardPresenter;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by connxun-16 on 2018/1/2.
 */

/**
 * 全部分类
 */
public class AllClassificationActivity extends BaseSwipeBackActivity implements PublishCardContract.HomeView,Validator.ValidationListener{

    @BindView(R.id.lv_all_classification_listview)
    ListView lvAllClassification; //标题栏

    @BindView(R.id.iv_all_classification_finish)
    ImageView ivFinish; //销毁

    @BindView(R.id.rl_all_classification_recyvlerView)
    RecyclerView rlAllclassificationRecyclerview;


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
        Toast(error);
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
                presenter.getCategorySubList(carouseMenus.get(position).getCategoryid());
                myAllClassificationAdapter.setSelectedPosition(position);
                myAllClassificationAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void showCategoryMenuSubList(List<CategorySub> carouseMenus) {

    }

    @Override
    public void showPublistCardText(PublishCardResponse publishCardResponse) {

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

    /**点击事件*/
    @OnClick({R.id.iv_all_classification_finish})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.iv_all_classification_finish:
                //返回
                this.finish();
                break;
        }
    }

}
