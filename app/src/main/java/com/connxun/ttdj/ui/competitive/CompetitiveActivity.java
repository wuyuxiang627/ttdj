package com.connxun.ttdj.ui.competitive;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.connxun.ttdj.R;
import com.connxun.ttdj.ui.base.BaseActivity;
import com.connxun.ttdj.ui.base.BaseSwipeBackActivity;
import com.connxun.ttdj.utils.onLoadMoreListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by connxun-16 on 2018/1/3.
 */

/**
 * 客户竞标
 */
public class CompetitiveActivity extends BaseSwipeBackActivity {

    @BindView(R.id.srl_competitive_swiperefreshlayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.rl_competitive_recyvlerView)
    RecyclerView rlCompetitiveRecyclerView;


    private Handler handler;
    private MyAdapter myAdapter;
    private LinearLayoutManager layoutManager;
    private int count;
    private ArrayList listData;


    @Override
    public int bindLayout() {
        return R.layout.activity_competitive;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void initView(View view) {

        init();

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void initInjector() {
        getComponent().inject(this);
    }



    private void init() {
        myAdapter = new MyAdapter();
        handler=new Handler();
        listData = new ArrayList();
        listData.clear();
        count = 0;
        for (int i = 0; i < 20; i++) {
            count += 1;
            listData.add(count);
        }
        layoutManager = new LinearLayoutManager(this);


        rlCompetitiveRecyclerView.setLayoutManager(layoutManager);
        rlCompetitiveRecyclerView.setItemAnimator(new DefaultItemAnimator());
        rlCompetitiveRecyclerView.setAdapter(myAdapter);

        //设置下拉时圆圈的颜色（可以尤多种颜色拼成）
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light);
        //设置下拉时圆圈的背景颜色（这里设置成白色）
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData("refresh");
            }
        });

        rlCompetitiveRecyclerView.addOnScrollListener(new onLoadMoreListener() {
            @Override
            protected void onLoading(int countItem,int lastItem) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getData("loadMore");
                    }
                },3000);
            }
        });

        getData("reset");
        
    }

    private void getData(final String type) {
        if ("refresh".equals(type)) {
            listData.clear();
            count = 0;
            for (int i = 0; i < 20; i++) {
                count += 1;
                listData.add(count);
            }
        }
        else {
            for (int i = 0; i < 20; i++) {
                count += 1;
                listData.add(count);
            }
        }

        myAdapter.notifyDataSetChanged();
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        if ("refresh".equals(type)) {
            Toast.makeText(getApplicationContext(), "刷新完毕", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "加载完毕", Toast.LENGTH_SHORT).show();
        }
    }


    private class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private final static int TYPE_CONTENT=0;//正常内容
        private final static int TYPE_FOOTER=1;//加载View

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
                View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_main_foot, parent, false);
                return new FootViewHolder(view);
            }
            else {
                View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_main_item, parent, false);
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
            layoutManager.getChildCount();
            layoutManager.getItemCount();
            layoutManager.findLastVisibleItemPosition();
        }


        @Override
        public int getItemCount() {
            return listData.size()+1;
        }
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_item_name);
        }
    }

    private class FootViewHolder extends RecyclerView.ViewHolder{
        ContentLoadingProgressBar contentLoadingProgressBar;
        public FootViewHolder(View itemView) {
            super(itemView);
            contentLoadingProgressBar=itemView.findViewById(R.id.pb_progress);
        }
    }
    
    
    
}
