package com.connxun.ttdj.ui.register;


import com.connxun.ttdj.ui.base.BasePView;

/**
 * @Author anna
 * @Date 2017-12-06 14:02
 * @Description 
 */
public interface RegisterContract {
    interface RegisterView extends BasePView {
        String getPhone();

        String getUserPassWord();

        String getUserPassWordAgain();

        void registerSuccess();
    }

    interface RegisterPresenter {

        String getCaptcha();

        void register();

    }
}
