package com.example.pc.xmnsg.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pc.xmnsg.R;
import com.example.pc.xmnsg.adapter.InfoGoodsAdapter;
import com.example.pc.xmnsg.fragment.CastFragment;
import com.example.pc.xmnsg.net.Api;
import com.example.pc.xmnsg.net.OnNetListener;
import com.example.pc.xmnsg.utils.Basebean;
import com.example.pc.xmnsg.utils.InfoGoodsBean;

import java.util.List;

/**
 * 商品的详情页面
 */
public class GoodsInfoActivity extends BaseActivity_2 implements View.OnClickListener {

    private ListView get_goods_listview;
    private TextView get_goods_name;
    private TextView get_goods_jinge;
    private TextView get_goods_price;
    private TextView get_goods_salenum2;
    private ImageView imageview;
    private RadioButton rb_kefu;
    private RadioButton rb_gouwuche;
    private RadioButton rb_addgouwuche;
    private RadioButton rb_newgoumai;
    private ImageView back3;
    private PopupWindow popupWindow;
    private View inflate;
    private InfoGoodsBean.DatasBean.GoodsInfoBean goods_info;
    private InfoGoodsBean.DatasBean datas;
    private Button delect_shu;
    int num = 0; //数量
    private Button delect_jian;
    private Button delect_jia;
    private SQLiteDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
//            http://169.254.116.183/mobile/index.php?act=goods&op=goods_detail&goods_id=100002&gc_id_1=503
        initView();//控件
        getInfoListData();//获得详细数据

    }

    private void initView() {
        get_goods_listview = (ListView) findViewById(R.id.get_goods_listview);
        get_goods_name = (TextView) findViewById(R.id.get_goods_name);
        get_goods_jinge = (TextView) findViewById(R.id.get_goods_jinge);
        get_goods_price = (TextView) findViewById(R.id.get_goods_price);
        get_goods_salenum2 = (TextView) findViewById(R.id.get_goods_salenum2);
        back3 = (ImageView) findViewById(R.id.back3);
        back3.setOnClickListener(this);
        imageview = (ImageView) findViewById(R.id.imageview);
        rb_kefu = (RadioButton) findViewById(R.id.rb_kefu);
        rb_kefu.setOnClickListener(this);
        rb_gouwuche = (RadioButton) findViewById(R.id.rb_gouwuche);
        rb_gouwuche.setOnClickListener(this);
        rb_addgouwuche = (RadioButton) findViewById(R.id.rb_addgouwuche);
        rb_addgouwuche.setOnClickListener(this);
        rb_newgoumai = (RadioButton) findViewById(R.id.rb_newgoumai);
        rb_newgoumai.setOnClickListener(this);
    }

    private void getInfoListData() {
        Intent in = getIntent();
        String stringExtra = in.getStringExtra("info");
        httpUtil.get(GoodsInfoActivity.this, Api.LINK_MOBILE_GOODS_DETAIL + stringExtra, InfoGoodsBean.class, new OnNetListener() {
            @Override
            public void onSuccess(Basebean basebean) {
                InfoGoodsBean infogoodsbean = (InfoGoodsBean) basebean;
                List<InfoGoodsBean.DatasBean.GoodsCommendListBean> goods_commend_list = infogoodsbean.getDatas().getGoods_commend_list();
                get_goods_listview.setAdapter(new InfoGoodsAdapter(GoodsInfoActivity.this, goods_commend_list));
                //页面的信息赋值
                datas = infogoodsbean.getDatas();
                goods_info = datas.getGoods_info();
                get_goods_name.setText(goods_info.getGoods_name());
                get_goods_jinge.setText(goods_info.getGoods_jingle());
                get_goods_price.setText(goods_info.getGoods_price());
                get_goods_salenum2.setText(goods_info.getGoods_salenum());
                Glide.with(imageview.getContext()).load(datas.getGoods_image())
                        .into(imageview);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.back3://点击返回
                finish();
                break;
            case R.id.rb_kefu://客服
                break;
            case R.id.rb_gouwuche://购物车
                startActivity(new Intent(GoodsInfoActivity.this, CastFragment.class));
                break;
            case R.id.rb_addgouwuche://加入购物车
                getPoP();//底部弹出pop
                getView();//底部布局控件的初始化

                SetViewListener();//设置文本变化相关监听事件

                break;
            case R.id.jiaru:
                //点击加入购物车
                MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(GoodsInfoActivity.this);
                db = mySQLiteOpenHelper.getWritableDatabase();
                //向数据库中插入和更新数据
                insertAndUpdateData();

                break;
        }
    }

    private void insertAndUpdateData() {
        //插入数据
        ContentValues values = new ContentValues();
        values.put("name", "xh");
        values.put("level", 5);
        db.insert("hero_info", "id", values);
        //清空ContentValues对象
        values.clear();
        values.put("name", "xh");
        values.put("level", 10);
        //更新xh的level 为10
        db.update("hero_info", values, "level = 5", null);
        //关闭SQLiteDatabase对象
        db.close();
    }

    private void SetViewListener() {
        delect_jian.setOnClickListener(new OnButtonClickListener());
        delect_jia.setOnClickListener(new OnButtonClickListener());
        delect_shu.addTextChangedListener(new OnTextChangeListener());
    }

    class OnButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            //http://www.jb51.net/article/88253.htm
        }
    }

    private void getView() {
        ImageView iv_show_pic = (ImageView) inflate.findViewById(R.id.iv_show_pic);
        Glide.with(iv_show_pic.getContext()).load(datas.getGoods_image())
                .into(iv_show_pic);
        TextView tv_commodity_name = (TextView) inflate.findViewById(R.id.tv_commodity_name);
        tv_commodity_name.setText(goods_info.getGoods_name());
        TextView tv_price = (TextView) inflate.findViewById(R.id.tv_price);
        tv_price.setText(goods_info.getGoods_price());
        TextView tv_shu = (TextView) inflate.findViewById(R.id.tv_shu);
        tv_shu.setOnClickListener(this);
        TextView delect_goods = (TextView) inflate.findViewById(R.id.delect_goods);
        delect_goods.setOnClickListener(this);

        delect_jian = (Button) inflate.findViewById(R.id.delect_jian);
        delect_shu = (Button) inflate.findViewById(R.id.delect_shu);
        delect_jia = (Button) inflate.findViewById(R.id.delect_jia);

        Button jiaru = (Button) inflate.findViewById(R.id.jiaru);
        jiaru.setOnClickListener(this);
        Button liji = (Button) inflate.findViewById(R.id.liji);
        liji.setOnClickListener(this);


    }


    private void getPoP() {
        // 设置添加屏幕的背景透明度
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.7f;
        getWindow().setAttributes(lp);
        //pop实现
        inflate = View.inflate(GoodsInfoActivity.this, R.layout.pop_dibu, null);
        popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
        //获取popwindow焦点
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
            WindowManager.LayoutParams pp = getWindow().getAttributes();
            pp.alpha = 1f;
            getWindow().setAttributes(pp);
        } else {
            // 显示窗口
            popupWindow.showAtLocation(rb_gouwuche, Gravity.BOTTOM, 0, 0);
        }
    }

    private class OnTextChangeListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}