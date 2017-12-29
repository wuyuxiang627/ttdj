package com.connxun.ttdj.ui.fragment.center;

import android.content.Context;
import android.util.Log;

import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.connxun.ttdj.api.AppApi;
import com.connxun.ttdj.constants.Constants;
import com.connxun.ttdj.data.ACache;
import com.connxun.ttdj.di.PerActivity;
import com.connxun.ttdj.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * @Author anna
 * @Date 2017-12-06 13:54
 * @Description
 */
@PerActivity
public class CenterPresenter extends BasePresenter<CenterContract.CenterView> implements CenterContract.CenterPresenter {

    Context context;
    private AppApi api;

    @Inject
    public CenterPresenter(Context context, AppApi accountApi) {
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
                    ToastUtils.showShort("解析数据成功");
                    LogUtils.e("---" + pUser);
                    ACache.get(context).put(Constants.USER_ID, pUser);
                    mView.endLoading();
                    mView.loginSuccess(pUser);
                },
                throwable -> {
                    ToastUtils.showShort("解析数据失败");
                    mView.onError(throwable.getMessage());
                    mView.endLoading();
            }
        ));


    }
}
