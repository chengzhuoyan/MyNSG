package com.example.pc.xmnsg.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc.xmnsg.R;
import com.example.pc.xmnsg.activity.BaseActivity;
import com.example.pc.xmnsg.activity.UserLogin;

/**
 * Desc
 * Author 程茁燕
 * Time 2017/8/31.
 */
public class UserFragment extends BaseActivity implements View.OnClickListener,UserLogin.SetTextView {

    private TextView usernameTextView1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.userfrag, null);
        usernameTextView1 = (TextView) view.findViewById(R.id.usernameTextView);
        usernameTextView1.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.usernameTextView://点击登录
                Toast.makeText(getActivity(),"ohyes",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), UserLogin.class));
                UserLogin.setTextName(UserFragment.this);
                break;
        }
    }

    @Override
    public void initTextView(String name) {
        usernameTextView1.setText(name);
    }
}
