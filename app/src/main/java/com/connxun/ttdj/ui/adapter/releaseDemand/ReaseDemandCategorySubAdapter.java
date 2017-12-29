package com.connxun.ttdj.ui.adapter.releaseDemand;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.connxun.ttdj.R;
import com.connxun.ttdj.entity.CategoryMenu;
import com.connxun.ttdj.entity.CategorySub;

import java.util.List;

/**
 * Created by connxun-16 on 2017/12/29.
 */

public class ReaseDemandCategorySubAdapter extends BaseAdapter {
    private List<CategorySub> mList;
    private Context mContext;

    public ReaseDemandCategorySubAdapter(Context pContext, List<CategorySub> pList) {
        this.mContext = pContext;
        this.mList = pList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater _LayoutInflater = LayoutInflater.from(mContext);
        convertView = _LayoutInflater.inflate(R.layout.item_rease_demand_item, null);
        if (convertView != null) {
            TextView tvKcYear1 = (TextView) convertView
                    .findViewById(R.id.tv_item_release_demand);
            tvKcYear1.setText(mList.get(position).getName());
        }
        return convertView;
    }
}

