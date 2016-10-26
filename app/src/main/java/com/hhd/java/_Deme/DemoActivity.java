package com.hhd.java._Deme;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.hhd.java.R;
import com.hhd.java._Deme.CountDownTimer.CountDownTimerActivity;
import com.hhd.java._Deme.EarphoneInterface.EarphoneInterfaceActivity;
import com.hhd.java._Deme.GPSTime.GPSTimeActivity;
import com.hhd.java._Deme.HHDLogger.HHDLogActivity;
import com.hhd.java._Deme.Recycler.RecyclerViewActivity;

public class DemoActivity extends Activity implements OnItemClickListener {

    private ListView listView_demo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        ListView listView_demo = (ListView) findViewById(R.id.listView_demo);
        // 添加ListItem，设置事件响应
        listView_demo.setAdapter(new DemoListAdapter());
        listView_demo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                onListItemClick(index);
            }
        });

    }


    /**
     * Item的点击事件
     *
     * @param index
     */
    void onListItemClick(int index) {
        Intent intent = null;
        intent = new Intent(this, Demo[index].demo);
        this.startActivity(intent);
    }

    /**
     * 数组填充Item
     */
    private static final Demo[] Demo = {
            new Demo(R.string.GPSTime_title, R.string.GPSTime_desc, GPSTimeActivity.class),
            new Demo(R.string.EarPhone_title, R.string.EarPhone_desc, EarphoneInterfaceActivity.class),
            new Demo(R.string.HHDLog_title, R.string.HHDLog_desc, HHDLogActivity.class),
            new Demo(R.string.CountDownTimer_title, R.string.CountDownTimer_desc, CountDownTimerActivity.class),
            new Demo(R.string.Recycler_title, R.string.Recycler_desc, RecyclerViewActivity.class),
    };

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //
    }

    /**
     * Item的适配器
     */
    private class DemoListAdapter extends BaseAdapter {
        public DemoListAdapter() {//无参构造方法
            super();
        }

        @Override
        public int getCount() {
            return Demo.length;
        }

        @Override
        public Object getItem(int index) {
            return Demo[index];
        }

        @Override
        public long getItemId(int id) {
            return id;
        }

        @Override
        public View getView(int index, View convertView, ViewGroup parent) {
            convertView = View.inflate(DemoActivity.this, R.layout.demo_info_item, null);
            TextView title = (TextView) convertView.findViewById(R.id.title);
            TextView desc = (TextView) convertView.findViewById(R.id.desc);
            title.setText(Demo[index].title);
            desc.setText(Demo[index].desc);
            if (index >= 0) {
                title.setTextColor(Color.YELLOW);
                desc.setTextColor(Color.WHITE);
            }
            return convertView;
        }
    }

    /**
     * 相当于ViewHolder
     */
    private static class Demo {
        private final int title;
        private final int desc;
        private final Class<? extends Activity> demo;

        public Demo(int title, int desc, Class<? extends Activity> demo) {
            this.title = title;
            this.desc = desc;
            this.demo = demo;
        }
    }
}
