package com.example.pc.xmnsg.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pc.xmnsg.R;
import com.example.pc.xmnsg.utils.InfoGoodsBean;

import java.util.List;

/**
 * Desc
 * Author 程茁燕
 * Time 2017/9/5.
 */
public class InfoGoodsAdapter extends BaseAdapter {
    private List<InfoGoodsBean.DatasBean.GoodsCommendListBean> goods_commend_list;
    private Context context;

    public InfoGoodsAdapter(Context context, List<InfoGoodsBean.DatasBean.GoodsCommendListBean> goods_commend_list) {
        this.context = context;
        this.goods_commend_list = goods_commend_list;
    }

    @Override
    public int getCount() {
        return goods_commend_list != null ? goods_commend_list.size() : 0;
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
            convertView = View.inflate(context, R.layout.item_infogoodslistview, null);
            holder = new ViewHolder();
            holder.info_goods_image_url = (ImageView) convertView.findViewById(R.id.info_goods_image_url);
            holder.info_goods_name = (TextView) convertView.findViewById(R.id.info_goods_name);
            holder.info_goods_promotion_price = (TextView) convertView.findViewById(R.id.info_goods_promotion_price);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.info_goods_name.setText(goods_commend_list.get(position).getGoods_name());
        holder.info_goods_promotion_price.setText("￥ " + goods_commend_list.get(position).getGoods_promotion_price());
        Glide.with(holder.info_goods_image_url.getContext()).load(goods_commend_list.get(position).getGoods_image_url())
                .into(holder.info_goods_image_url);
        return convertView;
    }

    class ViewHolder {
        ImageView info_goods_image_url;
        TextView info_goods_name;
        TextView info_goods_promotion_price;
    }
}
