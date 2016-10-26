package com.hhd.java.A_2_08_0405;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.hhd.java.R;

public class ActivityB extends Activity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_b_c);

        textView = (TextView) findViewById(R.id.textView);

        Intent intent = getIntent();
        Cat cat = (Cat) intent.getSerializableExtra("cat");
        textView.setText(cat.toString());
    }

}
