package com.example.pc.xmnsg.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.example.pc.xmnsg.R;
import com.example.pc.xmnsg.activity.ClassInfoActivity;
import com.example.pc.xmnsg.utils.RightChildBean;
import com.example.pc.xmnsg.utils.RightGroupBean;

import java.util.List;

/**
 * Desc
 * Author 程茁燕
 * Time 2017/9/5.s
 */
public class RightAdapter extends BaseExpandableListAdapter {
    private List<RightGroupBean.DatasBean.ClassListBean> groupbean;
    private List<List<RightChildBean.DatasBean.ClassListBean>> childbean;
    private Context context;

    public RightAdapter(List<List<RightChildBean.DatasBean.ClassListBean>> childbean, Context context, List<RightGroupBean.DatasBean.ClassListBean> groupbean) {
        this.childbean = childbean;
        this.context = context;
        this.groupbean = groupbean;
    }

    @Override
    public int getGroupCount() {
        return groupbean.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupbean.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childbean.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            groupViewHolder = new GroupViewHolder();
            convertView = View.inflate(context, R.layout.item_rightgroup, null);
            groupViewHolder.mGroupName = (TextView) convertView.findViewById(R.id.group_name);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        groupViewHolder.mGroupName.setText(groupbean.get(groupPosition).getGc_name());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView == null) {
            childViewHolder = new ChildViewHolder();
            convertView = View.inflate(context, R.layout.item_rightchild, null);
            childViewHolder.gridview = (GridView) convertView.findViewById(R.id.gridview);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }


       final List<RightChildBean.DatasBean.ClassListBean> classListBeen = childbean.get(groupPosition);
//        childViewHolder.gridview.setAdapter(new GridAdapter(groupbean, context));
        childViewHolder.gridview.setAdapter(new GridAdapter(classListBeen,context));
        childViewHolder.gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =new Intent(context, ClassInfoActivity.class);
                intent.putExtra("goodsid",classListBeen.get(position).getGc_id());
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private class GroupViewHolder {
        TextView mGroupName;
    }
    private class ChildViewHolder {
        GridView gridview;
    }

    @Override
    public void notifyDataSetChanged() {

        super.notifyDataSetChanged();
    }
}
