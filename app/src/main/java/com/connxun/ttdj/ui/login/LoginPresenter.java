package com.connxun.ttdj.ui.login;

import android.content.Context;
import android.util.Log;

import com.blankj.utilcode.util.CacheUtils;
import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.connxun.ttdj.api.AppApi;
import com.connxun.ttdj.constants.Constants;
import com.connxun.ttdj.di.PerActivity;
import com.connxun.ttdj.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * @Author anna
 * @Date 2017-12-06 13:54
 * @Description
 */
@PerActivity
public class LoginPresenter extends BasePresenter<LoginContract.LoginView> implements LoginContract.LoginPresenter {

    Context context;
    private AppApi api;

    @Inject
    public LoginPresenter(Context context, AppApi accountApi) {
        this.context = context;
        this.api = accountApi;
    }


    @Override
    public void login() {
        String userName = mView.getUserName();
        String userPwd = mView.getUserPassWord();


        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(userPwd)) {
            mView.endLoading();
            mView.onError("用户名密码不能为空请检查！");
            return;
        }

        String md5 = EncryptUtils.encryptMD5ToString(userPwd).toLowerCase();
        Log.d("________",md5);

        mView.startLoading();
        mDisposable.add(api.login(userName, md5).subscribe(pUser -> {
                    LogUtils.e("---" + pUser);
                    CacheUtils.getInstance().put(Constants.USER_ID,pUser);
//                    ACache.get(context).put(Constants.USER_ID, pUser);
                    mView.endLoading();
                    mView.loginSuccess(pUser);
                },
                throwable -> {
                    mView.onError(throwable.getMessage());
                    mView.endLoading();
            }
        ));


//        accountApi.login(userName, md5).subscribe(new Observer<PUser>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//                mDisposable.add(d);
//            }
//
//            @Override
//            public void onNext(@NonNull PUser pUser) {
//                LogUtils.e("---" + pUser);
//                ACache.get(context).put(Constants.USER_ID, pUser);
//
//                mView.loginSuccess(pUser);
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//                mView.onError(e.getMessage());
//                mView.endLoading();
//            }
//
//            @Override
//            public void onComplete() {
//                mView.endLoading();
//            }
//        });

    }
}
