package com.hhd.java.A_2_08_0405;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.hhd.java.R;

public class ActivityC extends Activity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_b_c);

        textView = (TextView) findViewById(R.id.textView);

        Intent intent = getIntent();
        Dog dog = intent.getParcelableExtra("dog");

        textView.setText(dog.toString());
    }

}
