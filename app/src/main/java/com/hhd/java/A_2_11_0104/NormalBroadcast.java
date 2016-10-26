package com.hhd.java.A_2_11_0104;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Normal BroadCast（普通广播）
 * Created by HHJ on 2016/5/26.
 */

public class NormalBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String info = intent.getStringExtra("broadcast_receiver");
        Toast.makeText(context,info,Toast.LENGTH_LONG).show();
    }
}
