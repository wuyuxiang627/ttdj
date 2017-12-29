package com.connxun.ttdj.ui.adapter.home;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.connxun.ttdj.R;
import com.connxun.ttdj.entity.Card;

import static com.connxun.ttdj.constants.Constants.BASE_IMAGE_URL;

/**
 * @Author anna
 * @Date 2017-11-22 13:52
 * @Descprition 首页热门收藏HotRecycleViewAdapter
 */

public class HomeHotAdapter extends BaseQuickAdapter<Card, BaseViewHolder> {

    /**
     * 创建adapter
     * @param layoutResId 布局
     */
    public HomeHotAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, Card item) {
        Glide.with(mContext).load(BASE_IMAGE_URL+item.getPic()).into((ImageView) helper.getView(R.id.iv_hot_pic));
        helper.setText(R.id.tv_name,item.getName()).
                setText(R.id.tv_service,item.getServicemode()).
                setText(R.id.tv_price,"888").
                setText(R.id.buy_num,"999");


    }
}
