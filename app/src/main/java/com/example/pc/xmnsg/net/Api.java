package com.example.pc.xmnsg.net;

/**
 * Created by peng on 2017/9/1.
 */

public interface Api {
    public static boolean isOnline = false;
    public static final String PRODUCT = "http://www.baidu.com";
    public static final String DEVELOP = "http://169.254.116.183";
    public static final String HOST = isOnline ? PRODUCT : DEVELOP;


    public static final String MAIN_PAGE = HOST + "/mobile/index.php?act=index";//首页
    public static final String LINK_MOBILE_CLASS = HOST + "/mobile/index.php?act=goods_class";//左列
    public static final String RIGHT = HOST + "/mobile/index.php?act=goods_class&gc_id=";//右列
    //http://169.254.116.183/mobile/index.php?act=goods&op=goods_list&page=100
    public static final String LINK_MOBILE_GOODS_SEARCH = HOST + "/mobile/index.php?act=goods&op=goods_list&page=100&gc_id=";//所有商品
    //    http://169.254.116.183/mobile/index.php?act=goods&op=goods_detail&goods_id=100009
    public static final String LINK_MOBILE_GOODS_DETAIL = HOST + "/mobile/index.php?act=goods&op=goods_detail&goods_id=";//商品详细信息
    //http://169.254.116.183/mobile/index.php?act=login&op=register
    public static final String REGISTER = HOST + "/mobile/index.php?act=login&op=register";//用户注册
    public static final String LOGIN = HOST + "/mobile/index.php?act=login";//登陆
    public static final String LINK_MOBILE_CART_ADD = HOST + "/mobile/index.php?act=member_cart&op=cart_add&goods_id=";//购物车添加

}
