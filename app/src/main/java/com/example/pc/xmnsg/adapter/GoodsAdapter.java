package com.example.pc.xmnsg.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pc.xmnsg.R;
import com.example.pc.xmnsg.utils.GoodsBean;

import java.util.List;

/**
 * Desc
 * Author 程茁燕
 * Time 2017/9/5.
 */
public class GoodsAdapter extends BaseAdapter {
    private List<GoodsBean.DatasBean.GoodsListBean> goods_list;
    private Context context;

    public GoodsAdapter(Context context, List<GoodsBean.DatasBean.GoodsListBean> goods_list) {
        this.context = context;
        this.goods_list = goods_list;
    }

    @Override
    public int getCount() {
        return goods_list != null ? goods_list.size() : 0;
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
            convertView = View.inflate(context, R.layout.item_goodslistview, null);
            holder = new ViewHolder();
            holder.goods_img = (ImageView) convertView.findViewById(R.id.goods_img);
            holder.goods_name = (TextView) convertView.findViewById(R.id.goods_name);
            holder.goods_price = (TextView) convertView.findViewById(R.id.goods_price);
            holder.goods_salenum = (TextView) convertView.findViewById(R.id.goods_salenum);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.goods_name.setText(goods_list.get(position).getGoods_name());
        holder.goods_price.setText("￥ " + goods_list.get(position).getGoods_price());
        holder.goods_salenum.setText("已售：" + goods_list.get(position).getGoods_salenum() + "件");
        Glide.with(holder.goods_img.getContext()).load(goods_list.get(position).getGoods_image_url())
                .into(holder.goods_img);
        return convertView;
    }

    class ViewHolder {
        ImageView goods_img;
        TextView goods_name;
        TextView goods_price;
        TextView goods_salenum;
    }
}
