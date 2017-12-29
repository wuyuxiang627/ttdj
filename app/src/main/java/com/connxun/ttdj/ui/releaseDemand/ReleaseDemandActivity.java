package com.connxun.ttdj.ui.releaseDemand;

/**
 * Created by connxun-16 on 2017/12/29.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.connxun.ttdj.MyApplication;
import com.connxun.ttdj.R;
import com.connxun.ttdj.api.AppApi;
import com.connxun.ttdj.entity.CategoryMenu;
import com.connxun.ttdj.entity.CategorySub;
import com.connxun.ttdj.ui.adapter.releaseDemand.ReaseDemandAdapter;
import com.connxun.ttdj.ui.adapter.releaseDemand.ReaseDemandCategorySubAdapter;
import com.connxun.ttdj.ui.base.BaseSwipeBackActivity;
import com.connxun.ttdj.ui.fragment.publish.PublishCardContract;
import com.connxun.ttdj.ui.fragment.publish.PublishCardPresenter;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.OkHttpClient;

/**
 * 发布需求
 */
public class ReleaseDemandActivity  extends BaseSwipeBackActivity implements PublishCardContract.HomeView,Validator.ValidationListener{
    String TAG = "ReleaseDemandActivity";

    @Inject
    PublishCardPresenter presenter;

    @BindView(R.id.sp_release_demand_fist_class)
    Spinner spRleaseDemandFistClass;
    @BindView(R.id.sp_release_demand_two_class)
    Spinner spRleaseDemandTwoClass;

    @BindView(R.id.tv_selease_demand_city)
    TextView tvSeleaseDemandCity;


    //相关数据
     /* 城市定位选择 */
    private static final int REQUEST_CODE_PICK_CITY = 0;


    @Override
    public int bindLayout() {
        return R.layout.activity_release_demand;
    }

    @Override
    public void initParms(Bundle parms) {
        Log.e(TAG,"initParms");

    }

    @Override
    public void initView(View view) {
//        presenter.attachView(this);
        Log.e(TAG,"initView");
    }

    @Override
    public void doBusiness(Context mContext) {

//        presenter = new PublishCardPresenter(new AppApi(new OkHttpClient()));
        presenter.attachView(this);
        presenter.getCategoryList();
        Log.e(TAG,"doBusiness: ");
    }

    @Override
    public void initInjector() {
        getComponent().inject(this);
//        Log.e(TAG,"initInjector");
    }

    @Override
    public void startLoading() {
        Log.e(TAG,"startLoading");
    }

    @Override
    public void endLoading() {
        Log.e(TAG,"endLoading");
    }

    @Override
    public void onError(String error) {
        Log.e(TAG,"onError: "+ error.toString());
        Toast(error);
    }

    /**点击事件*/
    @OnClick({R.id.tv_selease_demand_city})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.tv_selease_demand_city:
                //启动
                startActivityForResult(new Intent(MyApplication.getContext(), com.zaaach.citypicker.CityPickerActivity.class),
                        REQUEST_CODE_PICK_CITY);
                break;
        }
    }

    //重写onActivityResult方法
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PICK_CITY && resultCode == RESULT_OK) {
            if (data != null) {
                String city = data.getStringExtra(com.zaaach.citypicker.CityPickerActivity.KEY_PICKED_CITY);
                tvSeleaseDemandCity.setText("" + city);
            }
        }
    }


    /**一级分类数据返回*/
    @Override
    public void showCategoryMenuList(List<CategoryMenu> carouseMenus) {
        Log.e(TAG,"showCategoryMenuList: "+carouseMenus.toString());
        // 建立Adapter绑定数据源
        ReaseDemandAdapter adapter = new ReaseDemandAdapter(this, carouseMenus);
        // 绑定Adapter
        spRleaseDemandFistClass.setAdapter(adapter);
        spRleaseDemandFistClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                presenter.getCategorySubList(carouseMenus.get(position).getCategoryid());
                ToastUtils.showShort(carouseMenus.get(position).getCategoryid() + "");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    /**二级分类数据返回*/
    @Override
    public void showCategoryMenuSubList(List<CategorySub> carouseMenus) {
        Log.e(TAG,"showCategoryMenuSubList"+ carouseMenus.toString());
        ReaseDemandCategorySubAdapter adapter = new ReaseDemandCategorySubAdapter(this, carouseMenus);
        // 绑定Adapter
        spRleaseDemandTwoClass.setAdapter(adapter);
        spRleaseDemandTwoClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              //  presenter.getCategorySubList(carouseMenus.get(position).getCategoryid());
                ToastUtils.showShort(carouseMenus.get(position).getCategoryid() + "");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onValidationSucceeded() {

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {

    }
}
