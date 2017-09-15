package com.example.pc.xmnsg.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pc.xmnsg.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Desc
 * Author 程茁燕
 * Time 2017/9/1.
 */
public class SearchBaseAdapter extends BaseAdapter {
    private List<Map<String, String>> list = new ArrayList<>();
    private Context context;

    public SearchBaseAdapter(Context context, List<Map<String, String>> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
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
            convertView = View.inflate(context, R.layout.search_listview, null);
            holder = new ViewHolder();
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tv_flag = (TextView) convertView.findViewById(R.id.tv_flag);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_name.setText(list.get(position).get("name"));
        holder.tv_flag.setText(list.get(position).get("flag"));
        return convertView;
    }

    class ViewHolder {
        TextView tv_name;
        TextView tv_flag;
    }
}
