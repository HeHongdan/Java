package com.hhd.java.A_2_11_0104;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * 粘性广播
 * Created by HHJ on 2016/5/26.
 */

public class StickyBroadcast extends BroadcastReceiver {
    public StickyBroadcast(){}

    @Override
    public void onReceive(Context context, Intent intent) {
        String info = intent.getStringExtra("sticky_broadcast");
        Toast.makeText(context,"接收 -> " + info,Toast.LENGTH_SHORT).show();
    }
}
