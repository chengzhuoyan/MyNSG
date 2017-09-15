package com.example.pc.xmnsg.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pc.xmnsg.R;
import com.example.pc.xmnsg.net.Api;
import com.example.pc.xmnsg.net.OnNetListener;
import com.example.pc.xmnsg.utils.Basebean;
import com.example.pc.xmnsg.utils.RegisteBean;

import java.util.HashMap;
import java.util.Map;

/**
 * 注册页面
 */
public class UserRegiste extends BaseActivity_registe implements View.OnClickListener {

    private ImageView mRegistBack;
    /**
     * 请输入账号
     */
    private EditText mRegistName;
    /**
     * 请输入密码
     */
    private EditText mRegistPwd;
    /**
     * 请再次输入密码
     */
    private EditText mRegistPwdAgin;
    /**
     * 请输入邮箱地址
     */
    private EditText mRegistAdd;
    /**
     * 注册
     */
    private Button mBtnRegist;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registe);
        initView();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.regist_back://点击弹出对话框，确认退出注册
                AlertDialog.Builder builder = new AlertDialog.Builder(UserRegiste.this)
                        .setTitle("确认您的选择")
                        .setMessage("返回将清空您正在输入的内容");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("取消", null);
                builder.create().show();
                break;
            case R.id.regist_name:
                break;
            case R.id.regist_pwd:
                break;
            case R.id.regist_pwd_agin:
                break;
            case R.id.regist_add:
                break;
            case R.id.btn_regist:
                //点击注册
                if (!mRegistName.getText().toString().equals("") && !mRegistPwd.getText().toString().equals("") && !mRegistPwdAgin.getText().toString().equals("") && !mRegistAdd.getText().toString().equals("")) {

                    if (!mRegistPwd.getText().toString().equals(mRegistPwdAgin.getText().toString()) ) {
                        return;
                    }
                    Map<String, String> registeMap = new HashMap<>();
                    registeMap.put("username", mRegistName.getText().toString());
                    registeMap.put("password", mRegistPwd.getText().toString());
                    registeMap.put("password_confirm", mRegistPwdAgin.getText().toString());
                    registeMap.put("email", mRegistAdd.getText().toString());
                    registeMap.put("client", "android");

                    httpUtil.post(UserRegiste.this, Api.REGISTER, registeMap, RegisteBean.class, new OnNetListener() {
                        @Override
                        public void onSuccess(Basebean basebean) {
                            RegisteBean registeBean = (RegisteBean) basebean;
                            if ("200".equals(registeBean.getCode())) {
                                Toast.makeText(UserRegiste.this, "注册成功了", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                String error = registeBean.getDatas().getError();
                                Toast.makeText(UserRegiste.this, error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                } else {
                    Toast.makeText(UserRegiste.this, "不能有空", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void initView() {
        mRegistBack = (ImageView) findViewById(R.id.regist_back);
        mRegistBack.setOnClickListener(this);
        mRegistName = (EditText) findViewById(R.id.regist_name);
        mRegistName.setOnClickListener(this);
        mRegistPwd = (EditText) findViewById(R.id.regist_pwd);
        mRegistPwd.setOnClickListener(this);
        mRegistPwdAgin = (EditText) findViewById(R.id.regist_pwd_agin);
        mRegistPwdAgin.setOnClickListener(this);
        mRegistAdd = (EditText) findViewById(R.id.regist_add);
        mRegistAdd.setOnClickListener(this);
        mBtnRegist = (Button) findViewById(R.id.btn_regist);
        mBtnRegist.setOnClickListener(this);
    }
}
