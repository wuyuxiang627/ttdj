package com.connxun.ttdj.ui.fragment.center;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.CacheUtils;
import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;
import com.connxun.ttdj.R;
import com.connxun.ttdj.constants.Constants;
import com.connxun.ttdj.entity.PUser;
import com.connxun.ttdj.ui.base.BaseFragmentV4;
import com.connxun.ttdj.ui.center.AboutUsActivity;
import com.connxun.ttdj.ui.center.HelpAndFeedbackActivity;
import com.connxun.ttdj.ui.center.SettingActivity;
import com.connxun.ttdj.ui.login.LoginActivity;
import com.connxun.ttdj.ui.register.RegisterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.connxun.ttdj.constants.Constants.USER_ID;


public class CenterFragment extends BaseFragmentV4 {

    @BindView(R.id.ci_user_pic)
    CircleImageView ciUserPic;
    @BindView(R.id.tv_nikeName)
    TextView tvNikeName;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tv_reg)
    TextView tvReg;
    @BindView(R.id.li_nologin)
    LinearLayout liNologin;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rl_invite_friends)
    RelativeLayout rlInviteFriends;
    @BindView(R.id.rl_send_card)
    RelativeLayout rlSendCard;
    @BindView(R.id.rl_certification)
    RelativeLayout rlCertification;
    @BindView(R.id.rl_store_order)
    RelativeLayout rlStoreOrder;
    @BindView(R.id.rl_my_collect)
    RelativeLayout rlMyCollect;
    @BindView(R.id.rl_my_order)
    RelativeLayout rlMyOrder;
    @BindView(R.id.rl_my_trace)
    RelativeLayout rlMyTrace;
    @BindView(R.id.rl_my_carder)
    RelativeLayout rlMyCarder;
    @BindView(R.id.rl_my_want)
    RelativeLayout rlMyWant;
    @BindView(R.id.rl_help_feedback)
    RelativeLayout rlHelpFeedback;
    @BindView(R.id.rl_my_setting)
    RelativeLayout rlMySetting;
    @BindView(R.id.rl_about_us)
    RelativeLayout rlAboutUs;
    @BindView(R.id.logout)
    LinearLayout logout;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    Unbinder unbinder;


//    @BindView(R.id.wave_view)
//    WaveView waveView;


    public static CenterFragment getInstance() {
        return new CenterFragment();
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_center;
    }

    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public void initView(View view) {
        //动态波浪线
//        final FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(-2, -2);
//        lp.gravity = Gravity.CENTER;
//        waveView.setOnWaveAnimationListener(new WaveView.OnWaveAnimationListener() {
//            @Override
//            public void OnWaveAnimation(float y) {
//                lp.setMargins(0, 0, 0, (int) y + 2);
////                ciUserPic.setLayoutParams(lp);
//            }
//        });

        //设置CollapsingToolbarLayout显示样式
//        toolbarLayout.setTitle("");
//        toolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this.getContext(), R.color.white));
//        toolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this.getContext(), R.color.white));
    }

    @Override
    public void doBusiness(Context mContext) {
//        PUser pUser=UserUtil.getUser();
        PUser pUser = (PUser) CacheUtils.getInstance().getSerializable(USER_ID);
        if (pUser != null) {
            // TODO: 2017-12-06 判断是否为第三方登录用户 这里使用getHeadpic
            if (!StringUtils.isEmpty(pUser.getHeadpic())) {
                Glide.with(getActivity())
                        .load(pUser.getHeadpic())
                        .into(ciUserPic);
            } else {
                Glide.with(getActivity())
                        .load(Constants.BASE_IMAGE_URL + "FHBE/userIcon/userIcon.jpg")
                        .into(ciUserPic);
            }
            tvNikeName.setText(pUser.getName());
        } else {
            tvNikeName.setVisibility(View.GONE);
            liNologin.setVisibility(View.VISIBLE);
            logout.setVisibility(View.GONE);
        }

    }

    @Override
    public void lazyInitBusiness(Context mContext) {

    }

    @Override
    public void initInjector() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ci_user_pic, R.id.tv_login, R.id.tv_reg, R.id.rl_send_card, R.id.rl_certification, R.id.rl_store_order, R.id.rl_my_collect, R.id.rl_my_order, R.id.rl_my_trace, R.id.rl_my_carder, R.id.rl_my_want, R.id.rl_help_feedback, R.id.rl_my_setting, R.id.rl_about_us, R.id.logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ci_user_pic:
                break;
            case R.id.tv_login:
                mOperation.forward(LoginActivity.class);
//                mOperation.forwardAndFinish(LoginActivity.class);
                break;
            case R.id.tv_reg:
                mOperation.forward(RegisterActivity.class);
//                mOperation.forwardAndFinish(RegisterActivity.class);
                break;
            case R.id.rl_send_card:
                break;
            case R.id.rl_certification:
                break;
            case R.id.rl_store_order:
                break;
            case R.id.rl_my_collect:
                break;
            case R.id.rl_my_order:
                break;
            case R.id.rl_my_trace:
                break;
            case R.id.rl_my_carder:
                break;
            case R.id.rl_my_want:
                break;
            case R.id.rl_help_feedback:
                mOperation.forward(HelpAndFeedbackActivity.class);
                break;
            case R.id.rl_my_setting:
                mOperation.forward(SettingActivity.class);
                break;
            case R.id.rl_about_us:
                mOperation.forward(AboutUsActivity.class);
                break;
            case R.id.logout:
                mOperation.showBasicDialog("退出登录?", (dialog, which) -> {
                    mOperation.forwardAndFinish(LoginActivity.class);
                    CacheUtils.getInstance().remove(Constants.USER_ID);
                });
                break;
        }
    }

}
