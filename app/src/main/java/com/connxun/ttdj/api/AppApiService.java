package com.connxun.ttdj.api;

import com.connxun.ttdj.entity.Carousel;
import com.connxun.ttdj.entity.CategoryMenu;
import com.connxun.ttdj.entity.CategorySub;
import com.connxun.ttdj.entity.PCard;
import com.connxun.ttdj.entity.PUser;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @Author anna
 * @Date 2017-12-01 11:44
 * @Description 
 */

public interface AppApiService {

    /**
     * 用户登录
     * @param phone
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("login/login")
    Observable<PUser> login(@Field("phone") String phone, @Field("password") String password);

    /**
     * 验证码获取接口
     * @param phone
     * @param state 1 注册验证码 2 修改密码验证码 3 直接登录
     * @return
     */
    @FormUrlEncoded
    @POST("login/getCaptch")
    Observable<String> getCaptch(@Field("phone") String phone, @Field("state") String state);

    /**
     * 用户注册
     * @param username
     * @param password
     * @param captch
     * @return
     */
    @FormUrlEncoded
    @POST("login/reg")
    Observable<String> regist(@Field("phone") String username, @Field("password") String password, @Field("captch") String captch);


    /**
     * 修改密码
     */
    @FormUrlEncoded
    @POST("usr/updatepasswrd")
    Observable<String> upDatePW(@FieldMap Map<String, String> map);

    /**
     * 获取首页一级菜单信息
     *
     * @return
     */
    @POST("category/categoryAll")
    Observable<List<CategoryMenu>> getCarouseMenu();

    /**
     * 根据一级菜单ID获取二级菜单信息
     * @param categoryId 一级菜单ID
     * @return
     */
    @FormUrlEncoded
    @POST("category/categorySub")
    Observable<List<CategorySub>> getCarouseMenuSub(@Field("categoryId")String categoryId);

    /**
     * 获取banner
     *
     * @return
     */
    @POST("banner/bannerAll")
    Observable<List<Carousel>> getBannerList();

    /**
     * 获取首页热门推荐
     *
     * @return
     */
    @FormUrlEncoded
    @POST("card/getCardAllOrderRecommendValue")
    Observable<PCard> getHotCard(@Field("page")int page,@Field("length")int length);



}
