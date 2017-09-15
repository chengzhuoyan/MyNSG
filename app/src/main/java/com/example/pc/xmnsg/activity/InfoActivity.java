package com.example.pc.xmnsg.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.pc.xmnsg.R;
import com.example.pc.xmnsg.fragment.CastFragment;
import com.example.pc.xmnsg.fragment.ClassFragment;
import com.example.pc.xmnsg.fragment.HomeFragment;
import com.example.pc.xmnsg.fragment.UserFragment;

/**
 * 详情页面
 */
public class InfoActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup radiogroup;
    private RadioButton tab_home;
    private RadioButton tab_class;
    private RadioButton tab_buy;
    private RadioButton tab_me;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        //查找控件
        initView();
        //默认显示
        getMamager(new HomeFragment());
    }

    private void getMamager(Fragment fr) {
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, fr).commit();
    }

    private void initView() {
        radiogroup = (RadioGroup) findViewById(R.id.radiogroup);
        radiogroup.setOnCheckedChangeListener(this);
        tab_home = (RadioButton) findViewById(R.id.tab_home);
        tab_class = (RadioButton) findViewById(R.id.tab_class);
        tab_buy = (RadioButton) findViewById(R.id.tab_buy);
        tab_me = (RadioButton) findViewById(R.id.tab_me);
    }

    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.tab_home:
                HomeFragment homefrag = new HomeFragment();
                getMamager(homefrag);
                break;
            case R.id.tab_class:
                ClassFragment classfrag = new ClassFragment();
                getMamager(classfrag);
                break;
            case R.id.tab_buy:
                CastFragment castfrag = new CastFragment();
                getMamager(castfrag);
                break;
            case R.id.tab_me:
                UserFragment userfrag = new UserFragment();
                getMamager(userfrag);
                break;
        }
    }
}
