package com.hhd.java.A_2_11_0104;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hhd.java.R;

public class StickyBroadcastActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky_broadcast);
    }

    private StickyBroadcast stickyBroadcast = new StickyBroadcast();

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter("com.hhd.action.STICKY_BROADCAST");
        registerReceiver(stickyBroadcast,filter);//注册广播接受者
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(stickyBroadcast);//解除注册广播接受者
    }
}
