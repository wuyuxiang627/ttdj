package com.connxun.ttdj.ui.center;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.connxun.ttdj.R;
import com.connxun.ttdj.ui.base.BaseSwipeBackActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author anna
 * @Date 2017-12-07 14:26
 * @Descprition 帮助与反馈
 */

public class HelpAndFeedbackActivity extends BaseSwipeBackActivity {


    @BindView(R.id.rl_help_feedback)
    RecyclerView rlHelpFeedback;

    @Override
    public int bindLayout() {
        return R.layout.center_activity_help_feedback;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void initInjector() {

    }

    @OnClick({R.id.app_bar_right_tv})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.app_bar_right_tv:
                ToastUtils.showShort("点击了右上角按钮");
                break;
        }
    }
}
