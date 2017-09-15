package com.example.pc.xmnsg.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pc.xmnsg.R;
import com.example.pc.xmnsg.utils.LeftBean;

import java.util.List;

/**
 * Desc
 * Author 程茁燕
 * Time 2017/9/5.
 */
public class LeftAdapter extends BaseAdapter {
    private List<LeftBean.DatasBean.ClassListBean> class_list;
    private Context context;

    public LeftAdapter(Context context, List<LeftBean.DatasBean.ClassListBean> class_list) {
        this.context = context;
        this.class_list = class_list;
    }

    @Override
    public int getCount() {
        return class_list != null ? class_list.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_leftlistview, null);
            holder = new ViewHolder();
            holder.left_name = (TextView) convertView.findViewById(R.id.left_name);
            holder.left_img = (ImageView) convertView.findViewById(R.id.left_img);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.left_name.setText(class_list.get(position).getGc_name());
        Glide.with(holder.left_img.getContext()).load(class_list.get(position).getImage())
                .into(holder.left_img);
        return convertView;
    }

    class ViewHolder {
        TextView left_name;
        ImageView left_img;
    }
}
