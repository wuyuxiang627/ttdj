package com.connxun.ttdj.ui.login;


import com.connxun.ttdj.entity.PUser;
import com.connxun.ttdj.ui.base.BasePView;

/**
 * @Author anna
 * @Date 2017-12-06 14:02
 * @Description 
 */
public interface LoginContract {
    interface LoginView extends BasePView {
        String getUserName();

        String getUserPassWord();

        void loginSuccess(PUser token);
    }

    interface LoginPresenter {
        void login();

    }
}
