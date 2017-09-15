package com.example.pc.xmnsg.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.pc.xmnsg.R;
import com.example.pc.xmnsg.adapter.GoodsAdapter;
import com.example.pc.xmnsg.net.Api;
import com.example.pc.xmnsg.net.OnNetListener;
import com.example.pc.xmnsg.utils.Basebean;
import com.example.pc.xmnsg.utils.GoodsBean;

import java.util.List;

public class ClassInfoActivity extends BaseActivity_2 implements View.OnClickListener {

    private PopupWindow popup;
    private TextView class_order;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView t1;
    private TextView t2;
    private TextView t3;
    private TextView t4;
    private EditText et1;
    private EditText et2;
    private ImageView back2;
    public static Boolean isGridView = true;
    private ListView goodslistview;
    private GridView goodsgridview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_info);
        initView();//控件
        getGoodsData();//goods列表数据

    }

    private void getGoodsData() {
        Intent intent = getIntent();
        final String goodsid = intent.getStringExtra("goodsid");
        httpUtil.get(ClassInfoActivity.this, Api.LINK_MOBILE_GOODS_SEARCH + goodsid, GoodsBean.class, new OnNetListener() {
            @Override
            public void onSuccess(Basebean basebean) {
                GoodsBean goodsbean = (GoodsBean) basebean;
                final List<GoodsBean.DatasBean.GoodsListBean> goods_list = goodsbean.getDatas().getGoods_list();
                goodslistview.setAdapter(new GoodsAdapter(ClassInfoActivity.this, goods_list));
                goodslistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(ClassInfoActivity.this, GoodsInfoActivity.class);
                        i.putExtra("info", goods_list.get(position).getGoods_id());
                        startActivity(i);
                    }
                });
                if (isGridView) {
                    goodsgridview.setVisibility(View.VISIBLE);
                    goodsgridview.setAdapter(new GoodsAdapter(ClassInfoActivity.this, goods_list));
//                    goodsgridview.setOnItemClickListener(this);
                    goodsgridview.setVisibility(View.GONE);

                    goodsgridview.setSelection(0);
                } else {
                    goodslistview.setVisibility(View.VISIBLE);
                    goodslistview.setAdapter(new GoodsAdapter(ClassInfoActivity.this, goods_list));
//                    goodslistview.setOnItemClickListener(this);
                    goodslistview.setVisibility(View.GONE);
                    goodsgridview.setSelection(0);
                }


            }


        });
    }

    private void initView() {
        class_order = (TextView) findViewById(R.id.class_order);
        class_order.setOnClickListener(this);
        findViewById(R.id.class_first).setOnClickListener(this);
        findViewById(R.id.class_filtrate).setOnClickListener(this);
        findViewById(R.id.class_change).setOnClickListener(this);
        back2 = (ImageView) findViewById(R.id.back2);
        back2.setOnClickListener(this);
        goodslistview = (ListView) findViewById(R.id.goods_listview);

        goodsgridview = (GridView) findViewById(R.id.goods_gridview);
//        goodsgridview.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.class_order://人气排序

                View vv = View.inflate(ClassInfoActivity.this, R.layout.pop_class_order, null);
                tv1 = (TextView) vv.findViewById(R.id.tv1);
                tv2 = (TextView) vv.findViewById(R.id.tv2);
                tv3 = (TextView) vv.findViewById(R.id.tv3);
                tv4 = (TextView) vv.findViewById(R.id.tv4);
                popup = new PopupWindow(vv, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                popup.setOutsideTouchable(true);
                popup.setTouchable(true);
                popup.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
                popup.showAsDropDown(class_order);
                tv1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popup.dismiss();
                        class_order.setText(tv1.getText());
                    }
                });
                tv2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popup.dismiss();
                        class_order.setText(tv2.getText());
                    }
                });
                tv3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popup.dismiss();
                        class_order.setText(tv3.getText());
                    }
                });
                tv4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popup.dismiss();
                        class_order.setText(tv4.getText());
                    }
                });
                break;
            case R.id.class_first://销量优先
                break;
            case R.id.class_filtrate://筛选

                View vv2 = View.inflate(ClassInfoActivity.this, R.layout.pop_class_filtrate, null);
                t1 = (TextView) vv2.findViewById(R.id.t1);
                t2 = (TextView) vv2.findViewById(R.id.t2);
                t3 = (TextView) vv2.findViewById(R.id.t3);
                t4 = (TextView) vv2.findViewById(R.id.t4);
                et1 = (EditText) vv2.findViewById(R.id.et1);
                et2 = (EditText) vv2.findViewById(R.id.et2);
                popup = new PopupWindow(vv2, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                popup.setOutsideTouchable(true);
                popup.setTouchable(true);
                popup.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
                popup.showAsDropDown(class_order);
//                popup.dismiss();
                break;
            case R.id.class_change://变换格式
                break;
            case R.id.back2://退出
                finish();
                break;

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.class_change) {
            isGridView = !isGridView;
            getGoodsData();//goods列表数据
        }
        return super.onOptionsItemSelected(item);
    }

}
