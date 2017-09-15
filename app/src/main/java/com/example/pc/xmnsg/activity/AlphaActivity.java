package com.example.pc.xmnsg.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.pc.xmnsg.R;
import com.example.pc.xmnsg.adapter.SearchBaseAdapter;
import com.example.pc.xmnsg.view.RecordSQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 头部动画页面
 */
public class AlphaActivity extends AppCompatActivity {

    private PopupWindow pop;
    private TextView et_search;
    private TextView textOne;
    private TextView textTwo;
    private TextView tv_clear;
    private EditText title2;
    private RecordSQLiteOpenHelper helper = new RecordSQLiteOpenHelper(this);
    private SQLiteDatabase db;
    private BaseAdapter adapter;
    private ListView listview;
    private List<Map<String, String>> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alpha);

        listview = (ListView) findViewById(R.id.listview);
        tv_clear = (TextView) findViewById(R.id.tv_clear);
        title2 = (EditText) findViewById(R.id.title2);
        ImageView search = (ImageView) findViewById(R.id.et_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //插入数据,并查询出来，显示
                insertData();
                queryData();

            }
        });

        ImageView backone = (ImageView) findViewById(R.id.back2);
        backone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        et_search = (TextView) findViewById(R.id.group_text);
        et_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View vv = View.inflate(AlphaActivity.this, R.layout.pop, null);
                textOne = (TextView) vv.findViewById(R.id.textOne);
                textTwo = (TextView) vv.findViewById(R.id.textTwo);
                pop = new PopupWindow(vv, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                pop.setOutsideTouchable(true);
                pop.setTouchable(true);
                pop.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
                pop.showAsDropDown(et_search);
                textOne.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pop.dismiss();
                        et_search.setText(textOne.getText());
                    }
                });
                textTwo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pop.dismiss();
                        et_search.setText(textTwo.getText());
                    }
                });
            }
        });


        // 清空搜索历史
        tv_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();
                queryData();
            }
        });
        // 第一次进入查询所有的历史记录
        queryData();
    }

    //插入数据
    private void insertData() {
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", title2.getText().toString());
        values.put("flag", et_search.getText().toString());
        db.insert("records", null, values);
        db.close();
    }

    //查询数据
    private void queryData() {
        list.clear();
        db = helper.getReadableDatabase();
        Cursor cursor = db.query("records", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Map<String, String> map = new HashMap<>();
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String flag = cursor.getString(cursor.getColumnIndex("flag"));
            map.put("name", name);
            map.put("flag", flag);
            list.add(map);
        }
        // 创建adapter适配器
        SearchBaseAdapter adapter = new SearchBaseAdapter(AlphaActivity.this, list);
        // 设置适配器
        listview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    /**
     * 清空数据
     */
    private void deleteData() {
        db = helper.getWritableDatabase();
        db.execSQL("delete from records");
        db.close();
    }


}
