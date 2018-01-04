package com.connxun.ttdj.api;

import com.connxun.ttdj.constants.Constants;
import com.connxun.ttdj.entity.Carousel;
import com.connxun.ttdj.entity.CategoryMenu;
import com.connxun.ttdj.entity.CategorySub;
import com.connxun.ttdj.entity.PCard;
import com.connxun.ttdj.entity.PUser;
import com.connxun.ttdj.entity.PublishCardEntity;
import com.connxun.ttdj.entity.PublishCardResponse;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.FieldMap;

/**
 * @Author anna
 * @Date 2017-11-20 18:04
 * @Description 网络请求
 */

public class AppApi implements AppApiService {
    //    public static final String BASE_URL = Constants.BASE_URL + "FHBE/api/";
    public static final String BASE_URL = Constants.BASE_API_URL;
    private AppApiService service;

    public AppApi(OkHttpClient mOkHttpClient) {
        Retrofit retrofit =
                new Retrofit.Builder()
                        .client(mOkHttpClient)
                        .baseUrl(BASE_URL)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(com.connxun.ttdj.components.retrofit.GsonConverterFactory.create())
                        .build();
        service = retrofit.create(AppApiService.class);
    }

    /**
     * 封装.subscribeOn(Schedulers.io()).
     * observeOn(AndroidSchedulers.mainThread());
     *
     * @return
     */
    private ObservableTransformer bindUntil() {
        return new ObservableTransformer() {
            @Override
            public ObservableSource apply(Observable upstream) {
                return upstream.
                        subscribeOn(Schedulers.io()).
                        observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 封装subscribeOn(Schedulers.io()).
     * observeOn(AndroidSchedulers.mainThread()).
     * takeUntil(Observable.timer(deleyTime, TimeUnit.SECONDS));
     *
     * @param deleyTime 延时时间
     * @return
     */
    private ObservableTransformer bindUntil(final long deleyTime) {
        return new ObservableTransformer() {
            @Override
            public ObservableSource apply(Observable upstream) {
                return upstream.
                        subscribeOn(Schedulers.io()).
                        observeOn(AndroidSchedulers.mainThread()).
                        takeUntil(Observable.timer(deleyTime, TimeUnit.SECONDS));
            }
        };
    }

    @Override
    public Observable<PUser> login(String userName, String pwd) {
        return service.login(userName, pwd).compose(bindUntil());

//        return service.login(userName, pwd)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<String> getCaptch(String phone, String state) {
        return service.getCaptch(phone, state)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<String> regist(String phone, String pwd, String captch) {
        return service.regist(phone, pwd, captch)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    @Override
    public Observable<String> upDatePW(@FieldMap Map<String, String> map) {
        return service.upDatePW(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 获取所有一级服务
     *
     * @return
     */
    @Override
    public Observable<List<CategoryMenu>> getCarouseMenu() {
        return service.getCarouseMenu()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 根据一级菜单ID获取二级菜单信息
     *
     * @param id
     * @return
     */
    @Override
    public Observable<List<CategorySub>> getCarouseMenuSub(String id) {


        return service.getCarouseMenuSub(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<Carousel>> getBannerList() {
        return service.getBannerList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<PCard> getHotCard(int page,int length) {
        return service.getHotCard( page, length)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 上传文件
     * @param params
     * @return
     */
    @Override
    public Call<ResponseBody> uploadFile(Map<String, RequestBody> params) {
        return null;
    }

    /**
     *
     * @param publishCard
     * @return
     */
    @Override
    public Observable<PublishCardResponse> putPublishCard(PublishCardEntity publishCard) {
         return service.putPublishCard(publishCard)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

//    @Override
//    public Call<String> putFile(RequestBody description, MultipartBody.Part file) {
//        return null;
//    }

//    @Override
//    public Call<String> putFile(MultipartBody multipartBody) {
//        return null;
//    }

//    /**
//     * 名片发布上传文件
//     * @param parts
//     * @return
//     */
//    @Override
//    public Observable<String> putFile(List<MultipartBody.Part> parts) {
//        return service.putFile(parts)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
}
