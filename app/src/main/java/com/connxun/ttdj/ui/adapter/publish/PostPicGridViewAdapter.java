package com.connxun.ttdj.ui.adapter.publish;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.connxun.ttdj.R;
import com.connxun.ttdj.entity.PostMessageImageItem;

import java.io.File;
import java.util.List;

/**
 * @Author anna
 * @Date 2017-12-09 16:14
 * @Description 图片选择GridViewAdapter ——2行4列
 */
public class PostPicGridViewAdapter extends BaseAdapter {

    private Context context;
    private List<PostMessageImageItem> myList;
    private LayoutInflater layoutInflater;
    private ViewHolder_ptcontent vh_content = null;

    public PostPicGridViewAdapter(Context context, List<PostMessageImageItem> myList) {
        this.context=context;
        this.myList = myList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return myList.size() + 1;
    }   // 注意此处，预留尾部 + 号

    @Override
    public Object getItem(int position) {
        return myList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_postpic_gridview, null);
            vh_content = new ViewHolder_ptcontent();

            vh_content.mImageView = (ImageView) convertView
                    .findViewById(R.id.item_postmessage_gridview_image);
            vh_content.mImageViewDel = (ImageView) convertView
                    .findViewById(R.id.item_postmessage_gridview_image_delete);

            convertView.setTag(vh_content);
        } else {
            vh_content = (ViewHolder_ptcontent) convertView.getTag();
        }

        if (position < myList.size()) {
            vh_content.mImageViewDel.setVisibility(View.VISIBLE);
            Glide.with(context).load(myList.get(position).getPath()).skipMemoryCache(true).into(vh_content.mImageView);
//            vh_content.mImageView.setImageBitmap(myList.get(position).getImage());
        } else if (position == myList.size()) {
            if (myList.size() < 6) {
                Glide.with(context).load(R.mipmap.iv_picture).skipMemoryCache(true).into(vh_content.mImageView);
//                vh_content.mImageView.setImageResource(R.mipmap.iv_picture);// 最后一个显示加号图片
                convertView.setVisibility(View.VISIBLE);
            } else {
                convertView.setVisibility(View.GONE);
            }
            vh_content.mImageViewDel.setVisibility(View.GONE);
        }

        vh_content.mImageViewDel.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                deleteCacheFile( myList.get(position));
//                myList.get(position).remove();
                myList.remove(position);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    class ViewHolder_ptcontent {
        ImageView mImageView;
        ImageView mImageViewDel;
    }
    /**
     * 删除本地压缩图片
     */
    public void deleteCacheFile(PostMessageImageItem item) {
//           File file= new File(item.getUpPath());
//            if (file.exists() && item.getUpPath().contains("chengxin"))
//                file.delete();
        File file= new File(item.getPath());
        if (file.exists() && item.getPath().contains("ltcx"))
            file.delete();
    }
}
