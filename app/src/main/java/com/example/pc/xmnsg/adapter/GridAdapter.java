package com.example.pc.xmnsg.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pc.xmnsg.R;
import com.example.pc.xmnsg.utils.RightChildBean;

import java.util.List;

/**
 * Desc
 * Author 程茁燕
 * Time 2017/9/5.
 */
public class GridAdapter extends BaseAdapter {
    private List<RightChildBean.DatasBean.ClassListBean> classListBeen ;
    private Context context;

    public GridAdapter(List<RightChildBean.DatasBean.ClassListBean> classListBeen, Context context) {
        this.classListBeen = classListBeen;
        this.context = context;
    }

    @Override
    public int getCount() {
        return classListBeen.size();
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
        GridViewHolder gridViewHolder;
        if (convertView == null) {
            gridViewHolder = new GridViewHolder();
            convertView = View.inflate(context, R.layout.item_gridview, null);
            gridViewHolder.childname = (TextView) convertView.findViewById(R.id.child_name);
            convertView.setTag(gridViewHolder);
        } else {
            gridViewHolder = (GridViewHolder) convertView.getTag();
        }
        gridViewHolder.childname.setText(classListBeen.get(position).getGc_name());

        return convertView;
    }

    private class GridViewHolder {
        TextView childname;
    }
}
