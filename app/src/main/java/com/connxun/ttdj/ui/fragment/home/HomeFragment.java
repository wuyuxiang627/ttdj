package com.connxun.ttdj.ui.fragment.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.blankj.utilcode.util.CacheUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.connxun.ttdj.MyApplication;
import com.connxun.ttdj.R;
import com.connxun.ttdj.constants.Constants;
import com.connxun.ttdj.di.component.FragmentComponent;
import com.connxun.ttdj.entity.Carousel;
import com.connxun.ttdj.entity.CategoryMenu;
import com.connxun.ttdj.entity.PCard;
import com.connxun.ttdj.ui.allClassification.AllClassificationActivity;
import com.connxun.ttdj.ui.competitive.CompetitiveActivity;
import com.connxun.ttdj.ui.releaseDemand.ReleaseDemandActivity;
import com.connxun.ttdj.ui.adapter.MyViewPagerAdapter;
import com.connxun.ttdj.ui.adapter.home.GridViewAdapter;
import com.connxun.ttdj.ui.adapter.home.HomeHotAdapter;
import com.connxun.ttdj.ui.base.BaseFragmentV4;
import com.connxun.ttdj.utils.RecycleViewDivider;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import com.zaaach.citypicker.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;
import static com.connxun.ttdj.constants.Constants.AMAP_LOCATION;

/**
 * @Author anna
 * @Date 2017-11-21 15:33
 * @Description 首页
 */
public class HomeFragment extends BaseFragmentV4 implements
        HomeContract.HomeView, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    HomePresenter presenter;

    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.input_search_query)
    EditText inputSearchQuery;
    @BindView(R.id.tv_search)
    TextView tvSearch;

    @BindView(R.id.fabquxiu)
    ImageView fabquxiu;

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.swipe_view)
    SwipeRefreshLayout swipeView;
    Unbinder unbinder;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.points)
    LinearLayout points;



    /* 城市定位选择 */
    private static final int REQUEST_CODE_PICK_CITY = 0;
    private AMapLocationClient mLocationClient;
    String currentCity = "";
    String cou = "";

    /* 首页菜单-start */
    private List<View> viewPagerList;   //GridView作为一个View对象添加到ViewPager集合中
    private ImageView[] ivPoints;   //小圆点图片的集合
    private int totalPage;  //总的页数
    private int mPageSize = 10;  //每页显示的最大的数量
    /* end */

    /* 收藏热门-start */
    private HomeHotAdapter homeHotAdapter;
    private int hotPag=0;   //当前页
    boolean isErr=false;    //分页请求状态标志
    boolean isLast=false;   //是否最后一页标志
    /* end */

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }

    @Override
    public void initInjector() {
        getComponent(FragmentComponent.class).inject(this);
        //获取FragmentComponent 原理同上
//        FragmentComponent.class.cast(((HasComponent<FragmentComponent>) getActivity()).getFragmentComponent()).inject(this);
        //或者DaggerFragmentComponent 作用同上
//        DaggerFragmentComponent.builder().applicationComponent((ApplicationComponent)getActivity()).
//                build().inject(this);
    }

    @Override
    public void onRefresh() {
        presenter.getCategoryMenuList();
        presenter.getBannerList();
        hotPag=0;
        presenter.getHotCard(hotPag,15);
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

    @Override
    public int bindLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public void initView(View view) {
        presenter.attachView(this);
        //下滑刷新
        swipeView.setOnRefreshListener(this);
        swipeView.setColorSchemeColors(getResources().getColor(R.color.swipe_color));
        //banner图
        banner.setImageLoader(new GlideImageLoader());

        //初始化底部recycleView
        homeHotAdapter = new HomeHotAdapter(R.layout.item_home_hot_collect);
        //开始加载的位置
        homeHotAdapter.setStartUpFetchPosition(5);
        // 滑动最后一个Item的时候回调onLoadMoreRequested方法
        homeHotAdapter.setOnLoadMoreListener(() -> {
            rvList.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (isLast) {
                        //数据全部加载完毕
                        homeHotAdapter.loadMoreEnd();
                    } else {
                        if (!isErr) {
                            hotPag++;
                            //成功获取更多数据
                            presenter.getHotCard(hotPag,15);
                            homeHotAdapter.loadMoreEnd(true);
                        } else {
                            //获取更多数据失败
                            isErr = true;
//                            ToastUtils.showShort("数据加载失败");
                            homeHotAdapter.loadMoreFail();

                        }

                    }
                }

            }, 1000);
        }, rvList);

        //adpter开启动画（ALPHAIN 渐显、SCALEIN 缩放、SLIDEIN_BOTTOM 从下到上，SLIDEIN_LEFT从左到右、SLIDEIN_RIGHT 从右到左）
        homeHotAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        //自定义动画
//        homeHotAdapter.openLoadAnimation(new BaseAnimation() {
//            @Override
//            public Animator[] getAnimators(View view) {
//                return new Animator[]{
//                        ObjectAnimator.ofFloat(view, "scaleY", 1, 1.1f, 1),
//                        ObjectAnimator.ofFloat(view, "scaleX", 1, 1.1f, 1)
//                };
//            }
//        });
        //动画多次执行
        homeHotAdapter.isFirstOnly(false);
        //第一页不执行动画数量
        homeHotAdapter.setNotDoAnimationCount(2);
        //recycleView布局加载(LinearLayoutManager)
        rvList.setLayoutManager(new LinearLayoutManager(getContext()));
        //添加分割线
//        rvList.addItemDecoration(new SpaceDecoration(10));
//        rvList.addItemDecoration(new DividerDecoration(R.color.divider_recycle,1));
        rvList.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL));
        rvList.setAdapter(homeHotAdapter);
        //解决NestedScrollView和RecycleView滑动冲突
        rvList.setNestedScrollingEnabled(false);
        homeHotAdapter.setOnItemClickListener((adapter, view1, position) ->
                ToastUtils.showShort("点击了第" + position));

    }

    @Override
    public void doBusiness(Context mContext) {

        initLocation();

        //从flash页面传递过来的数据
        List<CategoryMenu> categoryMenuList = (List<CategoryMenu>) mOperation.getListParameter("categoryMenuList");
        List<Carousel> carouselList = (List<Carousel>) mOperation.getListParameter("carouselList");
        PCard pCard = (PCard) mOperation.getListParameter("pCard");
        //如果flash页面数据加载失败，则在这里重新加载
        if (categoryMenuList != null && categoryMenuList.size() > 0) {
            showCategoryMenuList(categoryMenuList);
        }else{
            presenter.getCategoryMenuList();
        }
        if (carouselList != null && carouselList.size() > 0) {
            showBannerList(carouselList);
        }else{
            presenter.getBannerList();
        }
        if (pCard != null && pCard.getContent().size()>0) {
            showHotCardList(pCard);
        }else{
            presenter.getHotCard(hotPag,20);
        }
    }

    @Override
    public void lazyInitBusiness(Context mContext) {

    }

    @Override
    public void showCategoryMenuList(List<CategoryMenu> carouseMenus) {

        //首页滚动菜单实现
        /* 1.使用ViewPager和GridView实现*/
        //总的页数向上取整
        totalPage = (int) Math.ceil(carouseMenus.size() * 1.0 / mPageSize);
        viewPagerList = new ArrayList<View>();
        for (int i = 0; i < totalPage; i++) {
            //每个页面都是inflate出一个新实例
            final GridView gridView = (GridView) View.inflate(getContext(), R.layout.item_home_gridview, null);
            gridView.setAdapter(new GridViewAdapter(getContext(), carouseMenus, i, mPageSize));
            //添加item点击监听
            int finalI = i;
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                                        int position, long arg3) {
                    // TODO Auto-generated method stub
                    ToastUtils.showShort("点击了" + (finalI * mPageSize + position));
                }
            });
            //每一个GridView作为一个View对象添加到ViewPager集合中
            viewPagerList.add(gridView);
        }
        //设置ViewPager适配器
        viewpager.setAdapter(new MyViewPagerAdapter(viewPagerList));

        /* 菜单底部圆点指示器实现 */
        //移除子视图
        points.removeAllViews();
        //添加小圆点
        ivPoints = new ImageView[totalPage];
        for (int i = 0; i < totalPage; i++) {
            //循坏加入点点图片组
            ivPoints[i] = new ImageView(getContext());
            if (i == 0) {
                ivPoints[i].setImageResource(R.mipmap.dot_focus_gray);
            } else {
                ivPoints[i].setImageResource(R.mipmap.dot_normal_gray);
            }
            ivPoints[i].setPadding(8, 8, 8, 8);


            points.addView(ivPoints[i]);
        }

        //设置ViewPager的滑动监听，主要是设置点点的背景颜色的改变
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                //currentPage = position;
                for (int i = 0; i < totalPage; i++) {
                    if (i == position) {
                        ivPoints[i].setImageResource(R.mipmap.dot_focus_gray);
                    } else {
                        ivPoints[i].setImageResource(R.mipmap.dot_normal_gray);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        swipeView.setRefreshing(false);
    }

    @Override
    public void showBannerList(List<Carousel> banners) {
        banner.update(banners);
        banner.setImages(banners);
        banner.setDelayTime(4000);
        banner.start();

        banner.setOnBannerListener(position ->
        {
            LogUtils.d("___________________" + banners.get(position).toString());
//            Logger.addLogAdapter(new AndroidLogAdapter());
//            Logger.d("___________________" + banners.get(position).toString());
//            if (banners.get(position).getState() == 1) {
//                BannerDetailsActivity.launch(mContext, banners.get(position).getBannerUrl());
//            }
        });
        swipeView.setRefreshing(false);

    }

    @Override
    public void showHotCardList(PCard pCard) {
        if (hotPag==0){
            homeHotAdapter.setNewData(pCard.getContent());
            homeHotAdapter.loadMoreComplete();
        }else{
            homeHotAdapter.addData(pCard.getContent());
            homeHotAdapter.loadMoreComplete();
        }

        isLast=pCard.isLast();
    }

    @OnClick({R.id.tv_city,R.id.iv_competitive,R.id.fabquxiu,R.id.iv_all_classily})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.tv_city:
                //启动
                startActivityForResult(new Intent(this.mContext, com.zaaach.citypicker.CityPickerActivity.class),
                        REQUEST_CODE_PICK_CITY);
                break;
            case R.id.iv_competitive:
                startActivity(new Intent(MyApplication.getContext(), CompetitiveActivity.class));
                break;
            case R.id.fabquxiu:
                startActivity(new Intent(MyApplication.getContext(), ReleaseDemandActivity.class));
                break;
            case R.id.iv_all_classily:
                startActivity(new Intent(MyApplication.getContext(), AllClassificationActivity.class));
                break;
        }


    }

//    @OnClick(R.id.fabquxiu)
//    public void onClick(View view){
////        ToastUtils.showShort("点击了发布需求");
//
//        startActivity(new Intent(MyApplication.getContext(), ReleaseDemandActivity.class));
//    }
//
//    @OnClick(R.id.iv_all_classily)
//    public void onClickClassily(View view){
//        startActivity(new Intent(MyApplication.getContext(), AllClassificationActivity.class));
//    }



    //重写onActivityResult方法
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PICK_CITY && resultCode == RESULT_OK) {
            if (data != null) {
                String city = data.getStringExtra(com.zaaach.citypicker.CityPickerActivity.KEY_PICKED_CITY);
                tvCity.setText("" + city);
            }
        }
    }


    /**
     * 轮播图加载图片
     */
    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //Glide 加载图片简单用法
            String url = Constants.BASE_IMAGE_URL + ((Carousel) path).getPicurl();
            Glide.with(context).load(url).into(imageView);
        }
    }

    private void initLocation() {
        //声明AMapLocationClient类对象
        mLocationClient = new AMapLocationClient(this.getContext());
        //配置定位参数
        AMapLocationClientOption option = new AMapLocationClientOption();
        //高精度定位模式：会同时使用网络定位和GPS定位
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        option.setOnceLocation(true);
        mLocationClient.setLocationOption(option);
        //设置 定位回调监听器
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                Log.d("______ddd", aMapLocation.toString());
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        /*
                        //基础定位信息
                         amapLocation.getCountry();//国家信息
                         amapLocation.getProvince();//省信息
                         amapLocation.getCity();//城市信息
                         amapLocation.getDistrict();//城区信息
                         amapLocation.getStreet();//街道信息
                         amapLocation.getStreetNum();//街道门牌号信息
                         //获取定位时间
                         SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                         Date date = new Date(amapLocation.getTime());
                         */
                        String city = aMapLocation.getCity();
                        String cou = aMapLocation.getDistrict();
                        Log.e("onLocationChanged", "city: " + city);
                        Log.e("onLocationChanged", "district: " + cou);
                        String location = StringUtils.extractLocation(city, cou);
                        tvCity.setText(location);
                        CacheUtils.getInstance().put(AMAP_LOCATION, location);
                    } else {
                        //定位失败
                        currentCity = "定位失败";
                        tvCity.setText(currentCity);
                    }
                }
            }
        });
        mLocationClient.startLocation();
    }
}
