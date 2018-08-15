package com.hhd.java;

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

import com.hhd.java.A_2_08_0103.ActivityActivity;
import com.hhd.java.A_2_08_0405.PutExtraBundle;
import com.hhd.java.A_2_08_06.ResultActivity;
import com.hhd.java.A_2_08_0708.ScreenOrientationChange;
import com.hhd.java.A_2_10_0407.BoundServiceActivity;
import com.hhd.java.A_2_11_0104.BroadcastReceiverActivity;
import com.hhd.java.A_2_12_03.FragmentActivity;
import com.hhd.java.A_2_12_03.PopBackTaskActivity;
import com.hhd.java.A_2_15_0708.JsonActivity;
import com.hhd.java.A_2_17_0104.ContentProviderActivity;
import com.hhd.java.J_1_06_07.Singleton;
import com.hhd.java.J_1_09_03.RecursionFactorial;
import com.hhd.java.J_1_09_0406.LinkedListActivity;
import com.hhd.java.J_1_16_01.ListActivity;
import com.hhd.java.J_1_16_0203.SetActivity;
import com.hhd.java.J_1_17_0103.MapActivity;
import com.hhd.java.J_1_18_0103.ThreadActivity;
import com.hhd.java.J_1_19_0104.MultipleThreadsActivity;
import com.hhd.java.J_1_20_0205.EchoClientActivity;
import com.hhd.java._Demo.DemoActivity;


public class MainActivity extends Activity implements OnItemClickListener {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Vitamio------------------------------┐
        //加载so类库（通常使用C、C++编写类库）
      /*  if (!LibsChecker.checkVitamioLibs(this))
            return;*/
        //Vitamio------------------------------┘
        ListView listView = (ListView) findViewById(R.id.listView);
        // 添加ListItem，设置事件响应
        listView.setAdapter(new DemoListAdapter());
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                onListItemClick(index);
            }
        });
    }

    /**
     * Item的点击事件
     * @param index
     */
    void onListItemClick(int index) {
        Intent intent = null;
        intent = new Intent(MainActivity.this,JAVA[index].demoClass);
        this.startActivity(intent);
    }

    /**
     * 数组填充Item
     */
    private static final JavaDemo[] JAVA = {
            new JavaDemo(R.string.Demo_title, R.string.Demo_desc, DemoActivity.class),
            new JavaDemo(R.string.singleton_title, R.string.singleton_desc, Singleton.class),
            new JavaDemo(R.string.recursion_factorial_title, R.string.recursion_factorial_desc, RecursionFactorial.class),
            new JavaDemo(R.string.linked_list_title, R.string.linked_list_desc, LinkedListActivity.class),
            new JavaDemo(R.string.list_title, R.string.list_desc, ListActivity.class),
            new JavaDemo(R.string.set_title, R.string.set_desc, SetActivity.class),
            new JavaDemo(R.string.map_title, R.string.map_desc, MapActivity.class),
            new JavaDemo(R.string.thread_title, R.string.thread_desc, ThreadActivity.class),
            new JavaDemo(R.string.multiple_threads_title, R.string.multiple_threads_desc, MultipleThreadsActivity.class),
            new JavaDemo(R.string.socket_title, R.string.socket_desc, EchoClientActivity.class),
            new JavaDemo(R.string.activity_title, R.string.activity_desc, ActivityActivity.class),
            new JavaDemo(R.string.putextra_bundle_title, R.string.putextra_bundle_desc, PutExtraBundle.class),
            new JavaDemo(R.string.result_activity_title, R.string.result_activity_desc, ResultActivity.class),
            new JavaDemo(R.string.screen_change_title, R.string.screen_change_desc, ScreenOrientationChange.class),
            new JavaDemo(R.string.bound_service_title, R.string.bound_service_desc, BoundServiceActivity.class),
            new JavaDemo(R.string.broadcast_receiver_title, R.string.broadcast_receiver_desc, BroadcastReceiverActivity.class),
            new JavaDemo(R.string.fragment_title, R.string.fragment_desc, PopBackTaskActivity.class),
            new JavaDemo(R.string.fragment_title, R.string.fragment_desc, FragmentActivity.class),
            new JavaDemo(R.string.json_title, R.string.json_desc, JsonActivity.class),
            new JavaDemo(R.string.content_provider_title, R.string.content_provider_desc, ContentProviderActivity.class),
    };
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

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
            return JAVA.length;
        }
        @Override
        public Object getItem(int index) {
            return JAVA[index];
        }
        @Override
        public long getItemId(int id) {
            return id;
        }
        @Override
        public View getView(int index, View convertView, ViewGroup parent) {
            convertView = View.inflate(MainActivity.this, R.layout.demo_info_item,null);
            TextView title = (TextView) convertView.findViewById(R.id.title);
            TextView desc = (TextView) convertView.findViewById(R.id.desc);
            title.setText(JAVA[index].title);
            desc.setText(JAVA[index].desc);
            if (index >= 0){
                title.setTextColor(Color.YELLOW);
                desc.setTextColor(Color.WHITE);
            }
            return convertView;
        }
    }

    /**
     * 相当于ViewHolder
     */
    private static class JavaDemo {
        private final int title;
        private final int desc;
        private final Class<? extends Activity> demoClass;
        public JavaDemo(int title, int desc, Class<? extends Activity> demoClass) {
            this.title = title;
            this.desc = desc;
            this.demoClass = demoClass;
        }
    }
}
