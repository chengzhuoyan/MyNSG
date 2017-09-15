package com.example.pc.xmnsg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc.xmnsg.R;
import com.example.pc.xmnsg.net.Api;
import com.example.pc.xmnsg.net.OnNetListener;
import com.example.pc.xmnsg.utils.Basebean;
import com.example.pc.xmnsg.utils.LoginBean;

import java.util.HashMap;
import java.util.Map;

public class UserLogin extends BaseActivity_login implements View.OnClickListener {

    public static interface SetTextView{
        void initTextView(String name);
    }
    private static SetTextView setTextView1;

    public static void setTextName(SetTextView setTextView){
        setTextView1 = setTextView;
    }

    private ImageView mLoginBack;
    /**
     * 请输入账号
     */
    private EditText mLoginName;
    /**
     * 请输入密码
     */
    private EditText mLoginPwd;
    /**
     * 登录
     */
    private Button mBtnLogin;
    /**
     * 注册账号
     */
    private TextView mZhuceName;
    /**
     * 找回密码
     */
    private TextView mFindPwd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        initView();


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zhuce_name://点击跳转注册
                startActivity(new Intent(UserLogin.this, UserRegiste.class));
                break;
            case R.id.login_back://点击退出登录
                finish();
                break;
            case R.id.login_name:
                break;
            case R.id.login_pwd:
                break;
            case R.id.btn_login:
                if (!mLoginName.getText().toString().equals("") && !mLoginPwd.getText().toString().equals("")) {
                    Map<String, String> loginMap = new HashMap<>();
                    loginMap.put("username", mLoginName.getText().toString());
                    loginMap.put("password", mLoginPwd.getText().toString());
                    loginMap.put("client", "android");

                    httpUtil.post(UserLogin.this, Api.LOGIN, loginMap, LoginBean.class, new OnNetListener() {
                        @Override
                        public void onSuccess(Basebean basebean) {
                            LoginBean loginBean= (LoginBean) basebean;
                            if ("200".equals(loginBean.getCode())) {
                                Toast.makeText(UserLogin.this, "登陆成功了", Toast.LENGTH_SHORT).show();
                                setTextView1.initTextView(mLoginName.getText().toString());
                                finish();
                            } else {
                                String error = loginBean.getDatas().getError();
                                Toast.makeText(UserLogin.this, error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                } else {
                    Toast.makeText(UserLogin.this, "不能有空", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.find_pwd:
                break;
        }
    }

    private void initView() {
        mLoginBack = (ImageView) findViewById(R.id.login_back);
        mLoginBack.setOnClickListener(this);
        mLoginName = (EditText) findViewById(R.id.login_name);
        mLoginName.setOnClickListener(this);
        mLoginPwd = (EditText) findViewById(R.id.login_pwd);
        mLoginPwd.setOnClickListener(this);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(this);
        mZhuceName = (TextView) findViewById(R.id.zhuce_name);
        mZhuceName.setOnClickListener(this);
        mFindPwd = (TextView) findViewById(R.id.find_pwd);
        mFindPwd.setOnClickListener(this);

    }
}
