package com.connxun.ttdj.ui.login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.connxun.ttdj.R;
import com.connxun.ttdj.entity.PUser;
import com.connxun.ttdj.ui.base.BaseSwipeBackActivity;
import com.connxun.ttdj.ui.main.MainActivity;
import com.connxun.ttdj.ui.register.RegisterActivity;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * @Author anna
 * @Date 2017-12-04 17:41
 * @Descprition 登录
 */

public class LoginActivity extends BaseSwipeBackActivity implements LoginContract.LoginView ,Validator.ValidationListener {

    @Inject
    LoginPresenter loginPresenter;

    @BindView(R.id.introduce)
    ImageView introduce;
    @BindView(R.id.usericon)
    ImageView usericon;
    @BindView(R.id.usernameEditText)
    @NotEmpty(messageResId = R.string.emptyPhone)
    EditText usernameEditText;
    @BindView(R.id.user1_edit_delete)
    ImageView user1EditDelete;
    @BindView(R.id.passwordicon)
    ImageView passwordicon;
    @BindView(R.id.passwordEditText)
    @Password(min = 6,messageResId = R.string.emptyPassword)    //正则：scheme = Password.Scheme.ALPHA_NUMERIC_MIXED_CASE_SYMBOLS,
    EditText passwordEditText;
    @BindView(R.id.password_edit_delete)
    ImageView passwordEditDelete;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.fastregister)
    TextView fastregister;
    @BindView(R.id.findpwd)
    TextView findpwd;
    @BindView(R.id.ib_qq)
    ImageButton ibQq;
    @BindView(R.id.ib_wx)
    ImageButton ibWx;
    @BindView(R.id.ib_wb)
    ImageButton ibWb;
    @BindView(R.id.check_passsword)
    CheckBox checkPasssword;

    Validator validator;

    @Override
    public int bindLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @SuppressLint("Range")
    @Override
    public void initView(View view) {
//        BarUtils.setStatusBarAlpha(this,0);
        setTouchDissIm(true);
    }

    @Override
    public void doBusiness(Context mContext) {

        loginPresenter.attachView(this);
        validator=new Validator(this);
        validator.setValidationListener(this);
    }

    @Override
    public void initInjector() {
        getComponent().inject(this);
    }

    @OnClick({R.id.user1_edit_delete, R.id.password_edit_delete,R.id.tv_login,
            R.id.fastregister, R.id.findpwd,
            R.id.ib_qq, R.id.ib_wx, R.id.ib_wb})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user1_edit_delete:
                break;
            case R.id.password_edit_delete:
                break;
            case R.id.tv_login:
                validator.validate();
                break;
            case R.id.fastregister:
                mOperation.forward(RegisterActivity.class);
                break;
            case R.id.findpwd:
                break;
            case R.id.ib_qq:
                break;
            case R.id.ib_wx:
                break;
            case R.id.ib_wb:
                break;
        }
    }

    @OnCheckedChanged(R.id.check_passsword)
    public void onCheckBoxClicked(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            passwordEditText.setTransformationMethod(HideReturnsTransformationMethod
                    .getInstance());
        } else {
            passwordEditText.setTransformationMethod(PasswordTransformationMethod
                    .getInstance());
        }
        passwordEditText.setSelection(passwordEditText.getText().length());
    }

    @Override
    public void startLoading() {
        mOperation.showProgress("正在登录...", false);
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
    public String getUserName() {
        return usernameEditText.getText().toString();
    }

    @Override
    public String getUserPassWord() {
        return passwordEditText.getText().toString();
    }

    @Override
    public void loginSuccess(PUser token) {
        mOperation.forwardAndFinish(MainActivity.class, LEFT_RIGHT);
    }

    @OnClick()
    public void onViewClicked() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.detachView();
    }

    @Override
    public void onValidationSucceeded() {
        loginPresenter.login();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            }else {
                ToastUtils.showShort(message);
            }
        }
    }
}
