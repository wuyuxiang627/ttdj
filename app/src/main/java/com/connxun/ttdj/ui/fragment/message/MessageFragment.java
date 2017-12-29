package com.connxun.ttdj.ui.fragment.message;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.connxun.ttdj.R;
import com.connxun.ttdj.di.component.FragmentComponent;
import com.connxun.ttdj.ui.base.BaseFragmentV4;


/**
 * Created by Administrator on 2017/2/23.
 */
public class MessageFragment extends BaseFragmentV4 {

    public static MessageFragment getInstance() {
        return new MessageFragment();
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_message;
    }

    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void lazyInitBusiness(Context mContext) {

    }

    @Override
    public void initInjector() {
        getComponent(FragmentComponent.class).inject(this);
    }
}
