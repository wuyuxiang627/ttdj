package com.connxun.ttdj.ui.adapter.allClassificatioin;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.connxun.ttdj.R;
import com.connxun.ttdj.entity.CategoryMenu;

import java.util.List;

/**
 * Created by connxun-16 on 2018/1/2.
 */

public class MyAllClassificationAdapter extends BaseAdapter {
    Context context;
    List<CategoryMenu> carouseMenus;

    private int selectedPosition = -1;// 选中的位置

    public void setSelectedPosition(int position) {
        this.selectedPosition = position;
    }

    public MyAllClassificationAdapter(Context context, List<CategoryMenu> carouseMenus){
        this.context  = context;
        this.carouseMenus = carouseMenus;
    }


    @Override
    public int getCount() {
        return carouseMenus.size();
    }

    @Override
    public Object getItem(int i) {
        return carouseMenus.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        viewHolder holder;
        if (convertView == null) {
            holder = new viewHolder();
            convertView = View.inflate(context, R.layout.item_all_claaaification_left, null);
            holder.tv_text = (TextView) convertView.findViewById(R.id.item_all_classification_left_txt);
            holder.view = (View) convertView.findViewById(R.id.item_all_classification_left_img);
            holder.rl_item_all_classififcation =(RelativeLayout) convertView.findViewById(R.id.rl_item_all_classififcation);
            convertView.setTag(holder);
        }
        holder = (viewHolder) convertView.getTag();
        CategoryMenu d = carouseMenus.get(position);
        holder.tv_text.setText(d.getName());
        if(selectedPosition == position){
            holder.rl_item_all_classififcation.setBackgroundResource(R.color.white);
            holder.view.setVisibility(View.VISIBLE);
            d.setChecked(false);
        }else{
            holder.rl_item_all_classififcation.setBackgroundResource(R.color.back_item);
            holder.view.setVisibility(View.INVISIBLE);
        }
        if (d.isChecked() == true) {

        } else if (d.isChecked() == false) {

        }
        return convertView;
    }


    class  viewHolder{
        View view;
        TextView tv_text;
        RelativeLayout rl_item_all_classififcation;


    }

}
