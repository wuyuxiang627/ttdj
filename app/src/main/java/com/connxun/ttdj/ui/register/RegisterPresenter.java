package com.connxun.ttdj.ui.register;

import android.content.Context;
import android.util.Log;

import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.LogUtils;
import com.connxun.ttdj.api.AppApi;
import com.connxun.ttdj.di.PerActivity;
import com.connxun.ttdj.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * @Author anna
 * @Date 2017-12-06 13:54
 * @Description
 */
@PerActivity
public class RegisterPresenter extends BasePresenter<RegisterContract.RegisterView> implements RegisterContract.RegisterPresenter {

    Context context;
    private AppApi api;

    String captcha;

    @Inject
    public RegisterPresenter(Context context, AppApi accountApi) {
        this.context = context;
        this.api = accountApi;
    }


    @Override
    public String getCaptcha() {
        mDisposable.add(api.getCaptch(mView.getPhone(),"1").subscribe(captcha -> {
                    LogUtils.e("---" + captcha);
                    this.captcha=captcha;
                },
                throwable -> {
                    mView.onError(throwable.getMessage());
                }
        ));
        return null;
    }

    @Override
    public void register() {
        String userName = mView.getPhone();
        String userPwd = mView.getUserPassWord();
        String userPwdAgain = mView.getUserPassWordAgain();

        if (!userPwd.equals(userPwdAgain)) {
            mView.endLoading();
            mView.onError("两次密码输入不相同！");
            return;
        }

        String md5 = EncryptUtils.encryptMD5ToString(userPwd).toLowerCase();
        Log.d("________",md5);

        mView.startLoading();
        mDisposable.add(api.regist(userName, md5, captcha).subscribe(pUser -> {
                    LogUtils.e("---" + pUser);
                    mView.endLoading();
                    mView.registerSuccess();
                },
                throwable -> {
                    mView.onError(throwable.getMessage());
                    mView.endLoading();
            }
        ));


    }
}
