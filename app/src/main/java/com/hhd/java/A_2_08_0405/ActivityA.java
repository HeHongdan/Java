package com.hhd.java.A_2_08_0405;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.hhd.java.R;

/**
 * A08-04 传递简单数据
 * A08-05 传递自定义对象数据
 */

public class ActivityA extends Activity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_b_c);

        textView = (TextView) findViewById(R.id.textView);

        Intent intent = getIntent();
//        Bundle bundle = intent.getBundleExtra("bundle");
//        String data = bundle.getString("data");
        String data = intent.getStringExtra("data");
        int age = intent.getIntExtra("age",0);//获取不到赋值为0
        textView.setText("String：" + data + "\nint：" + age);

    }

}
