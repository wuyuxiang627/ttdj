package com.connxun.ttdj.ui.adapter.competitive;

import android.content.Context;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.connxun.ttdj.R;

import java.util.ArrayList;

/**
 * Created by connxun-16 on 2018/1/3.
 */

public class CompetitiveAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static int TYPE_CONTENT=0;//正常内容
    private final static int TYPE_FOOTER=1;//加载View
    private  Context context;

    private ArrayList<String> listData;
    private LinearLayoutManager linearLayoutManager;

    CompetitiveAdapter(Context context, ArrayList<String> listData,LinearLayoutManager linearLayoutManager){
        this.context = context;
        this.listData = listData;
        this.linearLayoutManager = linearLayoutManager;
    }

    public CompetitiveAdapter(){

    }
    @Override
    public int getItemViewType(int position) {
        if (position==listData.size()){
            return TYPE_FOOTER;
        }
        return TYPE_CONTENT;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==TYPE_FOOTER){
            View view = LayoutInflater.from(context).inflate(R.layout.activity_main_foot, parent, false);
            return new FootViewHolder(view);
        }
        else {
            View view = LayoutInflater.from(context).inflate(R.layout.activity_main_item, parent, false);
            MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (getItemViewType(position)==TYPE_FOOTER){
        }
        else{
            MyViewHolder viewHolder= (MyViewHolder) holder;
            viewHolder.textView.setText("第" + position + "行");
        }
        linearLayoutManager.getChildCount();
        linearLayoutManager.getItemCount();
        linearLayoutManager.findLastVisibleItemPosition();
    }


    @Override
    public int getItemCount() {
        return listData.size()+1;
    }
}

 class MyViewHolder extends RecyclerView.ViewHolder {
     TextView textView;
    public MyViewHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.tv_item_name);
    }
}

 class FootViewHolder extends RecyclerView.ViewHolder{
    ContentLoadingProgressBar contentLoadingProgressBar;
    public FootViewHolder(View itemView) {
        super(itemView);
        contentLoadingProgressBar=itemView.findViewById(R.id.pb_progress);
    }
}
