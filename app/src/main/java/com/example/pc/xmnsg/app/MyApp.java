package com.example.pc.xmnsg.app;

import android.app.Application;

import com.example.pc.xmnsg.net.HttpUtil;


/**
 * Created by peng on 2017/9/1.
 */

public class MyApp extends Application {

    private HttpUtil httpUtil;

    @Override
    public void onCreate() {
        super.onCreate();
        httpUtil = HttpUtil.getHttpUtil();
    }

    public HttpUtil getHttpUtil() {
        return httpUtil;
    }
}
