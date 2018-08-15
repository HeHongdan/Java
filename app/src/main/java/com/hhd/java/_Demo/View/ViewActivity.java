package com.hhd.java._Demo.View;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.hhd.java.R;

public class ViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        findViewById(R.id.view1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.custom_title).setVisibility(View.VISIBLE);
                Toast.makeText(ViewActivity.this,"显示文本",Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.view2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.customImageView1).setVisibility(View.VISIBLE);
                findViewById(R.id.customImageView2).setVisibility(View.VISIBLE);
                findViewById(R.id.customImageView3).setVisibility(View.VISIBLE);
                Toast.makeText(ViewActivity.this,"显示富文本",Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.view3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.customProgressBar).setVisibility(View.VISIBLE);
                Toast.makeText(ViewActivity.this,"显示圆圈进度",Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.view4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.customVolumControlBar).setVisibility(View.VISIBLE);
                Toast.makeText(ViewActivity.this, "显示圆圈指示", Toast.LENGTH_LONG).show();
            }
        });

    }
}
