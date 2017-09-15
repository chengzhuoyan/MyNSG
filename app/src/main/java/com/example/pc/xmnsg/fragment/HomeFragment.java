package com.example.pc.xmnsg.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.xmnsg.R;
import com.example.pc.xmnsg.activity.AlphaActivity;
import com.google.zxing.integration.android.IntentIntegrator;

/**
 * Desc  主页面
 * Author 程茁燕
 * Time 2017/8/31.
 */
public class HomeFragment extends Fragment {
    private TextView title;
    private ImageView code;
    private ImageView seach;
    private ImageView message;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.homefrag, null);
        title = (TextView) view.findViewById(R.id.title);
        code = (ImageView) view.findViewById(R.id.code);
        seach = (ImageView) view.findViewById(R.id.search);
        message = (ImageView) view.findViewById(R.id.message);
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击可是搜索平移
                startActivity(new Intent(getActivity(), AlphaActivity.class));
                getActivity().finish();

                //平移切换页面
                getActivity().overridePendingTransition(R.anim.tran_in, R.anim.tran_out);
            }
        });
        code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击扫描二维码
                IntentIntegrator integrator = new IntentIntegrator(getActivity());
                integrator.initiateScan();
            }
        });
        return view;
    }
}