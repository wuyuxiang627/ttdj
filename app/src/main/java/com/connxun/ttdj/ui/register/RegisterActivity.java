package com.connxun.ttdj.ui.register;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.connxun.ttdj.R;
import com.connxun.ttdj.ui.base.BaseSwipeBackActivity;
import com.connxun.ttdj.ui.login.LoginActivity;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Checked;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static com.connxun.ttdj.constants.Constants.WAITETIME;

/**
 * @Author anna
 * @Date 2017-12-06 18:50
 * @Descprition
 */

public class RegisterActivity extends BaseSwipeBackActivity implements RegisterContract.RegisterView, Validator.ValidationListener {

    @Inject
    RegisterPresenter registerPresenter;

    @BindView(R.id.et_phone)
    @NotEmpty(messageResId = R.string.emptyPhone)
    EditText etPhone;
    @BindView(R.id.et_password)
    @Password(min = 6, messageResId = R.string.emptyPassword)   //正则：scheme = Password.Scheme.ALPHA_NUMERIC_MIXED_CASE_SYMBOLS,
    EditText etPassword;
    @BindView(R.id.et_password_again)
    @NotEmpty(messageResId = R.string.emptyPasswordAgain)
    EditText etPasswordAgain;
    @BindView(R.id.et_captchacode)
    @NotEmpty(messageResId = R.string.emptyCaptcha)
    EditText etCaptchacode;
    @BindView(R.id.getcaptcha)
    TextView getcaptcha;
    @BindView(R.id.tv_regist)
    TextView tvRegist;
    @BindView(R.id.cb_agree)
    @Checked(message = "您必须同意此条款！")
    CheckBox cbAgree;

    protected int count = WAITETIME;

    Validator validator;

    @Override
    public int bindLayout() {
        return R.layout.activity_regist;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void initView(View view) {
//        BarUtils.setStatusBarAlpha(this,0);
        setTouchDissIm(true);
    }

    @Override
    public void doBusiness(Context mContext) {
        registerPresenter.attachView(this);
        validator=new Validator(this);
        validator.setValidationListener(this);
    }

    @Override
    public void initInjector() {
        getComponent().inject(this);
    }

    @OnClick({R.id.getcaptcha, R.id.tv_regist, R.id.cb_agree})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.getcaptcha:
                changeCaptcha(true);//设置验证码计时
                registerPresenter.getCaptcha();
                break;
            case R.id.tv_regist:
                validator.validate();
                break;
            case R.id.cb_agree:
                break;
        }
    }

    @Override
    public void startLoading() {
        mOperation.showProgress("正在注册...", false);
    }

    @Override
    public void endLoading() {
        dissmissDialog();
    }

    @Override
    public void onError(String error) {
        Toast(error);
    }

    @Override
    public String getPhone() {
        return etPhone.getText().toString();
    }

    @Override
    public String getUserPassWord() {
        return etPassword.getText().toString();
    }

    @Override
    public String getUserPassWordAgain() {
        return etPasswordAgain.getText().toString();
    }

    @Override
    public void registerSuccess() {
        mOperation.forwardAndFinish(LoginActivity.class);
    }


    @Override
    public void onValidationSucceeded() {
        registerPresenter.register();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                ToastUtils.showShort(message);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        registerPresenter.detachView();
    }

    private void changeCaptcha(boolean check) {
        if (check) {
            getcaptcha.setClickable(false);
            getcaptcha.setText("60s重新获取");
            handler.postDelayed(task, 1000);
        } else {
            getcaptcha.setClickable(true);
            getcaptcha.setText("获取验证码");
        }
    }

    private Thread task = new Thread() {
        @Override
        public void run() {
            handler.sendEmptyMessage(1);
            count--;
        }
    };
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if (count > 0) {
                        String showTimeString = count + "s重新获取";
                        getcaptcha.setText(showTimeString);
                        handler.postDelayed(task, 1000);
                    } else {
                        count = WAITETIME;
                        changeCaptcha(false);
                    }
                    break;

                default:
                    break;
            }
        }
    };
}