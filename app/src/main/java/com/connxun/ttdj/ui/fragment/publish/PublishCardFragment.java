package com.connxun.ttdj.ui.fragment.publish;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.CacheUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.connxun.ttdj.R;
import com.connxun.ttdj.api.AppApiService;
import com.connxun.ttdj.constants.Constants;
import com.connxun.ttdj.di.component.FragmentComponent;
import com.connxun.ttdj.entity.CategoryMenu;
import com.connxun.ttdj.entity.CategorySub;
import com.connxun.ttdj.entity.FileEntitiy;
import com.connxun.ttdj.entity.PostMessageImageItem;
import com.connxun.ttdj.entity.PublishCardEntity;
import com.connxun.ttdj.entity.PublishCardResponse;
import com.connxun.ttdj.ui.adapter.publish.PostPicGridViewAdapter;
import com.connxun.ttdj.ui.adapter.releaseDemand.ReaseDemandAdapter;
import com.connxun.ttdj.ui.adapter.releaseDemand.ReaseDemandCategorySubAdapter;
import com.connxun.ttdj.ui.base.BaseFragmentV4;
import com.connxun.ttdj.utils.AddressPickTask;
import com.connxun.ttdj.utils.GifSizeFilter;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import org.angmarch.views.NiceSpinner;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import cn.qqtheme.framework.entity.City;
import cn.qqtheme.framework.entity.County;
import cn.qqtheme.framework.entity.Province;
import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

import static android.app.Activity.RESULT_OK;
import static android.os.Environment.DIRECTORY_PICTURES;
import static com.connxun.ttdj.constants.Constants.AMAP_LOCATION;

/**
 * @Author anna
 * @Date 2017-12-02 17:54
 * @Description 名片发布
 */
public class PublishCardFragment extends BaseFragmentV4 implements PublishCardContract.HomeView, AdapterView.OnItemClickListener {

    @Inject
    PublishCardPresenter presenter;

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.nice_spinner)
    Spinner niceSpinner;
    @BindView(R.id.nice_spinner2)
    Spinner niceSpinner2;
    @BindView(R.id.et_service_range)
    EditText etServiceRange;
    @BindView(R.id.tv_province)
    TextView tvProvince;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.activity_postmessage_gridview)
    GridView activityPostmessageGridview;
    @BindView(R.id.rb_price)
    RadioButton rbPrice;
    @BindView(R.id.rb_range)
    RadioButton rbRange;
    @BindView(R.id.rb_agree)
    RadioButton rbAgree;
    @BindView(R.id.et_price)
    EditText etPrice;
    @BindView(R.id.et_low)
    EditText etLow;
    @BindView(R.id.et_above)
    EditText etAbove;
    @BindView(R.id.tv_publish)
    Button tvPublish;

    //未登陆注册
    @BindView(R.id.li_noauth)
    LinearLayout liNoauth;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_set_psw)
    EditText etSetPsw;
    @BindView(R.id.et_commit_psw)
    EditText etCommitPsw;
    @BindView(R.id.et_captchacode)
    EditText etCaptchacode;
    @BindView(R.id.getcaptcha)
    TextView getcaptcha;

    //未实名
    @BindView(R.id.rb_read)
    CheckBox rbRead;

    //登陆未实名
    @BindView(R.id.tv_certification)
    Button tvCertification;

    PublishCardEntity  publishCard;//发布名片请求实体

    List<File> fileList; //选择照片的文件集合

    //选择图片gridAdapter
    private PostPicGridViewAdapter gridadapter;
    //发帖图片实体LIST
    private List<PostMessageImageItem> gridViewData;
    //本地图片地址列表
    List<String> picList = new ArrayList<>();
    //压缩后的图片list列表
    List<File> compressPicList = new ArrayList<File>();
    //请求回调码
    private static final int REQUEST_CODE_CHOOSE = 23;

    String cardId ;//一级菜单id
    String twoCarouseMenusID ; //二级菜单id

    String pName; //省
    String cName; //市
    String couName; //县


    public static PublishCardFragment getInstance() {
        return new PublishCardFragment();
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_publish_card;
    }

    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public void initView(View view) {
        presenter.attachView(this);
        gridViewData = new ArrayList<>();
        fileList =  new ArrayList<>();
        gridadapter = new PostPicGridViewAdapter(this.getContext(), gridViewData);
        activityPostmessageGridview.setAdapter(gridadapter);
        activityPostmessageGridview.setOnItemClickListener(this);

    }

    @Override
    public void doBusiness(Context mContext) {
        //从flash页面传递过来的数据
        List<CategoryMenu> categoryMenuList = (List<CategoryMenu>) mOperation.getListParameter("categoryMenuList");
        if (categoryMenuList != null && categoryMenuList.size() > 0) {
            showCategoryMenuList(categoryMenuList);
        } else {
            presenter.getCategoryList();
        }
    }

    @Override
    public void lazyInitBusiness(Context mContext) {
    }

    @Override
    public void initInjector() {
        getComponent(FragmentComponent.class).inject(this);
    }

    @OnClick({R.id.app_bar_right_tv, R.id.tv_province,R.id.tv_publish,R.id.rb_price,
            R.id.rb_range,R.id.rb_agree,R.id.et_price,R.id.et_low,R.id.et_above})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.app_bar_right_tv: //重写
                ToastUtils.showShort("点击了右上角按钮");
                gridadapter.notifyDataSetChanged();
                break;
            case R.id.tv_province:  //地址选择
                AddressPickTask task = new AddressPickTask(getContext());
                task.setHideProvince(false);
                task.setHideCounty(false);
                task.setCallback(new AddressPickTask.Callback() {
                    @Override
                    public void onAddressInitFailed() {
                        ToastUtils.showShort("数据初始化失败");
                    }

                    @Override
                    public void onAddressPicked(Province province, City city, County county) {
                        if (county == null) {
                            ToastUtils.showShort(province.getAreaName() + city.getAreaName());
                            tvProvince.setText(province.getAreaName() + city.getAreaName());
                        } else {
                            ToastUtils.showShort(province.getAreaName() + city.getAreaName() + county.getAreaName());
                            tvProvince.setText(province.getAreaName() + city.getAreaName() + county.getAreaName());

                            pName = province.getAreaName();
                            cName = city.getAreaName();
                            couName = county.getAreaName();
                        }
                    }
                });
//                task.execute("贵州", "毕节", "纳雍");
                task.execute(CacheUtils.getInstance().getString(AMAP_LOCATION));
                break;
            case R.id.activity_postmessage_gridview: //添加图片
                ToastUtils.showShort("点击了activity_postmessage_gridview");

                break;
            case R.id.rb_price:
                etPrice.setCursorVisible(true);
                rbAgree.setChecked(false);
                rbRange.setChecked(false);
                etAbove.setCursorVisible(false);
                etLow.setCursorVisible(false);
                break;
            case R.id.rb_range:
                etPrice.setCursorVisible(false);
                etLow.setCursorVisible(true);
                etAbove.setCursorVisible(true);
                rbAgree.setChecked(false);
                rbPrice.setChecked(false);
                break;
            case R.id.rb_agree:
                etPrice.setCursorVisible(false);
                etAbove.setCursorVisible(false);
                etLow.setCursorVisible(false);
                rbRange.setChecked(false);
                rbPrice.setChecked(false);
                break;
            case R.id.et_price:
                rbPrice.setChecked(true);
                rbAgree.setChecked(false);
                rbRange.setChecked(false);
                break;
            case R.id.et_low:
            case R.id.et_above:
                rbPrice.setChecked(false);
                rbAgree.setChecked(false);
                rbRange.setChecked(true);
                break;
            case R.id.tv_publish:
                //1.上传文件
                if(getPublishCard()){
                    if(fileList != null && fileList.size() != 0){
                        putFile();
                    }else{
                        ToastUtils.showShort("请选择照片文件");
                        return;
                    }
                }


                break;
        }
    }

    //上传文件
    private void putFile() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build();
        AppApiService service = retrofit.create(AppApiService.class);
        RequestBody requestBody3 = RequestBody.create(MediaType.parse(""), "123456");
        Map<String, RequestBody> params = new HashMap<>();
        params.put("file_type", requestBody3);
        for(int i = 0; i<fileList.size();i++){
            File file = fileList.get(i);
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            params.put("file\"; filename=\""+ file.getName(), requestBody);
        }
        Call<ResponseBody> model = service.uploadFile(params);
        model.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    String s = new String(response.body().bytes());
                    Gson gson = new Gson();
                    FileEntitiy fileEntitiy = gson.fromJson(s,FileEntitiy.class);
                    //发送参数
                    //2.得到文件地址上传参数
                    if( publishCard != null){
                        publishCard.setPic(fileEntitiy.getCreateFilePath()); //设置图片路径
                        //发布名片
                        presenter.putPublishCard(publishCard);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("错误信息：",t.getMessage());
            }
        });

    }

    //获取实体类对象
    public boolean getPublishCard( ){
        publishCard = null;
        publishCard = new PublishCardEntity();
        if(!etName.getText().toString().equals("")&& //名称
                !tvProvince.getText().toString() .equals("")&&//地址
                !etAddress.getText().toString() .equals("")&& //详细地址
                !etServiceRange.getText().toString().equals("")&&//经营范围
                !cardId.equals("")&& !twoCarouseMenusID .equals("")){ //一级分类id和二级分类id
            publishCard.setName(etName.getText().toString());//名字
            publishCard.setContent(etServiceRange.getText().toString()); //经营范围
            publishCard.setAddr(etAddress.getText().toString());//具体地址

            publishCard.setCname(cName); //设置市
            publishCard.setPname(pName); //设置省
            publishCard.setCouname(couName); //设置县
            publishCard.setCardid(cardId); //一级id
            publishCard.setCategoryid(twoCarouseMenusID); //二级id

            if(rbPrice.isChecked()&&!etPrice.getText().toString() .equals("")){ //固定价格
                publishCard.setPrice(Integer.parseInt(etPrice.getText().toString())); //设置固定价钱
                publishCard.setIsagree("1");//设置不商定
            }else if(rbRange.isChecked()&&!etLow.getText().toString() .equals("")&&!etAbove.getText().toString() .equals("")){ //区间价格
                publishCard.setPricemax(Integer.parseInt(etAbove.getText().toString()));//设置最多价钱
                publishCard.setPricemin(Integer.parseInt(etLow.getText().toString()));//设置最少价钱
                publishCard.setIsagree("1");//设置不商定
            }else if(rbAgree.isChecked()){
                publishCard.setIsagree("0"); //设置商定
            }else {
                ToastUtils.showLong("请输入必要参数!!");
                return  false;
            }
            return true;
        }else {
            ToastUtils.showShort("请输入必要参数!!");
            return false;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void startLoading() {

    }

    @Override
    public void endLoading() {

    }

    @Override
    public void onError(String error) {
        mOperation.showBasicDialog(error);

    }

    /**
     * 一级菜单获取成功
     * @param carouseMenus
     */
    @Override
    public void showCategoryMenuList(List<CategoryMenu> carouseMenus) {
        // 建立Adapter绑定数据源
        ReaseDemandAdapter adapter = new ReaseDemandAdapter(getActivity(), carouseMenus);
        // 绑定Adapter
        niceSpinner.setAdapter(adapter);
//        niceSpinner.attachDataSource(carouseMenus);
//        presenter.getCategorySubList(carouseMenus.get(0).getCategoryid());
        niceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cardId = carouseMenus.get(position).getCategoryid();
                presenter.getCategorySubList(carouseMenus.get(position).getCategoryid());
                ToastUtils.showShort(carouseMenus.get(position).getCategoryid() + "");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * 二级菜单获取成功
     * @param carouseMenus
     */
    @Override
    public void showCategoryMenuSubList(List<CategorySub> carouseMenus) {
        ReaseDemandCategorySubAdapter adapter = new ReaseDemandCategorySubAdapter(getActivity(), carouseMenus);
        // 绑定Adapter
        niceSpinner2.setAdapter(adapter);
        niceSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                twoCarouseMenusID = carouseMenus.get(position).getCategoryid();
                ToastUtils.showShort(carouseMenus.get(position).getCategoryid() + "");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * 发布名片成功
     * @param publishCardResponse
     */
    @Override
    public void showPublistCardText(PublishCardResponse publishCardResponse) {
        mOperation.showBasicDialog("发布成功");
        etName.setText("");
        etAbove.setText("");
        etLow.setText("");
        etServiceRange.setText("");
        etAddress.setText("");
        etPrice.setText("");
        gridViewData.clear();
        fileList.clear();
        gridadapter.notifyDataSetChanged();
        publishCard = null;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取照片选择回调
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            //获取图片uri对象 Matisse.obtainResult(data);
            picList.clear();
            //获取图片路径
            picList.addAll(Matisse.obtainPathResult(data));
            compressWithLs(picList);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (parent.getId() == R.id.activity_postmessage_gridview) {
            //判断是否为最后一个
            if (position == parent.getChildCount() - 1) {
                new RxPermissions(getContext()).request(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA
                ).subscribe(granded -> {
                    if (granded) {
                        Matisse.from(this)
                                .choose(MimeType.ofAll(), false)
                                .countable(true)
                                .capture(true)  //拍照
                                .captureStrategy(
                                        new CaptureStrategy(true, "com.connxun.ttdj.app.fileprovider"))
                                .maxSelectable(6)
                                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                                .thumbnailScale(0.85f)
                                .imageEngine(new GlideEngine())
                                .forResult(REQUEST_CODE_CHOOSE);
                    } else {
                        ToastUtils.showShort("请先授予应用相关权限");
                        mOperation.showBasicDialog(R.string.error, R.string.grant_permission, (dialog, which) -> {
//                            Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);    //飞行模式，无线网和网络设置界面
                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);   //跳转位置服务界面
                            startActivity(intent);
                        });
                    }
                });
            }
        }
    }

    /**
     * Luban压缩图片 Listener 方式
     */
    private void compressWithLs(final List<String> photos) {
        Luban.with(this.getContext())
                .load(photos)
                .ignoreBy(100)
                .setTargetDir(getContext().getExternalFilesDir(DIRECTORY_PICTURES).getAbsolutePath())
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onSuccess(File file) {
                        //将选择的文件放入集合中
                        if(fileList != null){
                            fileList.add(file);
                        }else {
                            fileList = new ArrayList<>();
                            fileList.add(file);
                        }
//                        compressPicList.add(file);
                        LogUtils.e("当前图片信息：" + file.getPath() + "当前图片大小：" + file.length());
                        LogUtils.e("要加载的图片路径：" + file.getAbsolutePath());
                        gridViewData.add(new PostMessageImageItem(file.getAbsolutePath()));
                        gridadapter.notifyDataSetChanged();
                        if (picList.size() == compressPicList.size()) {
                            //图片上传
                            LogUtils.e("______图片压缩完毕", "图片list信息：" + compressPicList.size() + "--" + picList.size());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("______图片压缩失败" + e.toString());
                    }
                }).launch();
    }

}
