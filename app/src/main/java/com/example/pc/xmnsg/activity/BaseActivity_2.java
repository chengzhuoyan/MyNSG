package com.example.pc.xmnsg.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.pc.xmnsg.app.MyApp;
import com.example.pc.xmnsg.net.HttpUtil;


/**
 * Created by peng on 2017/9/1.
 */

public class BaseActivity_2 extends AppCompatActivity {

    protected MyApp myApp;
    protected HttpUtil httpUtil;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myApp = (MyApp) getApplication();
        httpUtil = myApp.getHttpUtil();
    }
}
