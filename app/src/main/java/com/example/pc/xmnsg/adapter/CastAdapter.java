//package com.example.pc.xmnsg.adapter;
//
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.example.pc.xmnsg.R;
//
///**
// * Desc
// * Author 程茁燕
// * Time 2017/9/15.
// */
//public class CastAdapter extends BaseAdapter {
//    @Override
//    public int getCount() {
//        return 0;
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder viewHolder;
//        if (convertView == null) {
//            convertView = View.inflate( ?, R.layout.item_gouwuche_listview, null);
//            viewHolder = new ViewHolder();
//            viewHolder.iv_show_pic = (ImageView) convertView.findViewById(R.id.iv_show_pic);
//            viewHolder.tv_commodity_name = (TextView) convertView.findViewById(R.id.tv_commodity_name);
//            viewHolder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
//            viewHolder.tv_num = (TextView) convertView.findViewById(R.id.tv_num);
//            viewHolder.tvshow2 = (TextView) convertView.findViewById(R.id.tvshow2);
//            viewHolder.tvshow4 = (TextView) convertView.findViewById(R.id.tvshow4);
//            convertView.setTag(viewHolder);//讲ViewHolder存储在View中
//
//        } else {
//            viewHolder = (ViewHolder) convertView.getTag();//重获取viewHolder
//        }
//        viewHolder.tv_commodity_name.setText( ?.get(position));
//        viewHolder.tv_price.setText( ?.get(position));
//        viewHolder.tv_num.setText( ?.get(position));
//        viewHolder.tvshow2.setText( ?.get(position));
//        viewHolder.tvshow4.setText( ?.get(position));
//
//        return convertView;
//    }
//
//    //内部类
//    class ViewHolder {
//        ImageView iv_show_pic;
//        TextView tv_commodity_name;
//        TextView tv_price;
//        TextView tv_num;
//        TextView tvshow2;
//        TextView tvshow4;
//    }
//}
