package com.example.pc.xmnsg.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.pc.xmnsg.R;

/**
 * 导航页面的实现
 * 倒计时跳转详情页面
 */
public class MainActivity extends AppCompatActivity {
    private TextView tv;
    private int count = 2;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (count == 1) {
                startActivity(new Intent(MainActivity.this, InfoActivity.class));
            } else {
                count--;
                tv.setText(count+"秒后进入");
                sendEmptyMessageDelayed(0,1000);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tvload);
        handler.sendEmptyMessageDelayed(0,1000);
    }
}
