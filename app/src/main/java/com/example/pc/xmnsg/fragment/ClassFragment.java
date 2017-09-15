package com.example.pc.xmnsg.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.example.pc.xmnsg.R;
import com.example.pc.xmnsg.activity.BaseActivity;
import com.example.pc.xmnsg.adapter.LeftAdapter;
import com.example.pc.xmnsg.adapter.RightAdapter;
import com.example.pc.xmnsg.net.Api;
import com.example.pc.xmnsg.net.OnNetListener;
import com.example.pc.xmnsg.utils.Basebean;
import com.example.pc.xmnsg.utils.LeftBean;
import com.example.pc.xmnsg.utils.RightChildBean;
import com.example.pc.xmnsg.utils.RightGroupBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc 分类
 * Author 程茁燕
 * Time 2017/8/31.
 */
public class ClassFragment extends BaseActivity {

    private ListView leftlistview;
    private ExpandableListView expand;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.classfrag, null);
        leftlistview = (ListView) view.findViewById(R.id.leftlistview);
        expand = (ExpandableListView) view.findViewById(R.id.expandableListView);
        //左侧列表数据
        leftData();

        return view;
    }

    private void leftData() {
        httpUtil.get(getActivity(), Api.LINK_MOBILE_CLASS, LeftBean.class, new OnNetListener() {
            @Override
            public void onSuccess(Basebean basebean) {
                LeftBean leftbean = (LeftBean) basebean;
                final List<LeftBean.DatasBean.ClassListBean> class_list = leftbean.getDatas().getClass_list();
                leftlistview.setAdapter(new LeftAdapter(getActivity(), class_list));
                String c = class_list.get(0).getGc_id();
                leftlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String c = class_list.get(position).getGc_id();
                        Log.d("zzz", c);
                        //右侧列表显示
                        rightData(c);
                    }
                });
            }
        });
    }

    private void rightData(final String c) {
        httpUtil.get(getActivity(), Api.RIGHT + c, RightGroupBean.class, new OnNetListener() {
            @Override
            public void onSuccess(Basebean basebean) {
                Log.d("zzz", Api.RIGHT + c);
                RightGroupBean rightgroupbean = (RightGroupBean) basebean;
                final List<RightGroupBean.DatasBean.ClassListBean> class_list_group = rightgroupbean.getDatas().getClass_list();
                final List<List<RightChildBean.DatasBean.ClassListBean>> class_list_child = new ArrayList<List<RightChildBean.DatasBean.ClassListBean>>();
                class_list_child.clear();
//                for (RightGroupBean.DatasBean.ClassListBean classlistbean : class_list_group) {
                for (int i = 0; i < class_list_group.size(); i++) {
                    final int finalI = i;
                    httpUtil.get(getActivity(), Api.RIGHT + class_list_group.get(i).getGc_id(), RightChildBean.class, new OnNetListener() {
                        @Override
                        public void onSuccess(Basebean basebean) {
                            RightChildBean rightchildbean = (RightChildBean) basebean;
                            List<RightChildBean.DatasBean.ClassListBean> class_lists = rightchildbean.getDatas().getClass_list();
                            class_list_child.add(class_lists);
                            if (finalI == class_list_group.size() - 1) {
                                RightAdapter rightAdapter = new RightAdapter(class_list_child, getActivity(), class_list_group);
                                expand.setAdapter(rightAdapter);
                                for (int i = 0; i < rightAdapter.getGroupCount(); i++) {
                                    expand.expandGroup(i);
                                }
                            }
                        }
                    });
                }

            }
        });
    }

}
