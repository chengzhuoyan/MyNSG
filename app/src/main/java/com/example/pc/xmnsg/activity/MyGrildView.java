package com.example.pc.xmnsg.activity;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Desc
 * Author 程茁燕
 * Time 2017/9/5.
 */
public class MyGrildView extends GridView {
    public MyGrildView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int i = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, i);

    }
}
