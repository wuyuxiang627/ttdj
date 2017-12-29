package com.connxun.ttdj.ui.center;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.connxun.ttdj.R;
import com.connxun.ttdj.constants.Constants;
import com.connxun.ttdj.ui.base.BaseSwipeBackActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import skin.support.SkinCompatManager;

/**
 * @Author anna
 * @Date 2017-12-07 14:07
 * @Descprition 设置
 */

public class SettingActivity extends BaseSwipeBackActivity {
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.rl_data)
    RelativeLayout rlData;
    @BindView(R.id.tv_logout)
    TextView tvLogout;
    @BindView(R.id.rl_update_pwd)
    RelativeLayout rlUpdatePwd;
    @BindView(R.id.sw_night)
    Switch swNight;


    @Override
    public int bindLayout() {
        return R.layout.center_activity_setting;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void initView(View view) {
        swNight.setChecked(SPUtils.getInstance().getBoolean(Constants.NIGHT_THEME));
    }

    @Override
    public void doBusiness(Context mContext) {
        swNight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //应用内换肤——SKIN_LOADER_STRATEGY_BUILD_IN后缀加载，SKIN_LOADER_STRATEGY_PREFIX_BUILD_IN前缀加载
                    SkinCompatManager.getInstance().loadSkin("night", null, SkinCompatManager.SKIN_LOADER_STRATEGY_BUILD_IN);
                    SPUtils.getInstance().put(Constants.NIGHT_THEME, true);
                    reload();
                } else {
                    //恢复默认皮肤
                    SkinCompatManager.getInstance().restoreDefaultTheme();
                    SPUtils.getInstance().put(Constants.NIGHT_THEME, false);
                    reload();
                }

            }
        });
    }

    @Override
    public void initInjector() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_data, R.id.rl_update_pwd, R.id.tv_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_data:
                break;
            case R.id.rl_update_pwd:
                break;
            case R.id.tv_logout:
                break;
        }
    }

    @OnClick(R.id.sw_night)
    public void onViewClicked() {
    }
}
