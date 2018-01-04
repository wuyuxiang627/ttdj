package com.connxun.ttdj.ui.adapter.allClassificatioin;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.connxun.ttdj.R;
import com.connxun.ttdj.constants.Constants;
import com.connxun.ttdj.entity.CategorySub;

import java.util.List;

/**
 * Created by connxun-16 on 2018/1/3.
 */

public class AllclassificationTwoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static int TYPE_CONTENT=0;//正常内容
    private final static int TYPE_FOOTER=1;//加载View
    private  Context context;

    private List<CategorySub> listData;

    public AllclassificationTwoAdapter(Context context, List<CategorySub> listData){
        this.context = context;
        this.listData = listData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_competitive, parent, false);
            MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            MyViewHolder viewHolder= (MyViewHolder) holder;
            viewHolder.textView.setText(listData.get(position).getName());
        Log.e("wewe'",Constants.BASE_IMAGE_URL +
                listData.get(position).getPicurl());
            Glide.with(context).load(Constants.BASE_IMAGE_URL +
                    listData.get(position).getCenterpicurl()).into(viewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }


}

 class MyViewHolder extends RecyclerView.ViewHolder {
     TextView textView;
     ImageView imageView;
    public MyViewHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.tv_item_competitive_text);
        imageView = itemView.findViewById(R.id.iv_item_competitive_iamge);
    }
}


