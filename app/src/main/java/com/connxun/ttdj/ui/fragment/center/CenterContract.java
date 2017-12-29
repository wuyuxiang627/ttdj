package com.connxun.ttdj.ui.fragment.center;


import com.connxun.ttdj.entity.PUser;
import com.connxun.ttdj.ui.base.BasePView;

/**
 * @Author anna
 * @Date 2017-12-06 14:02
 * @Description 
 */
public interface CenterContract {
    interface CenterView extends BasePView {
        String getUserName();

        String getUserPassWord();

        void loginSuccess(PUser token);
    }

    interface CenterPresenter {
        void login();

    }
}
