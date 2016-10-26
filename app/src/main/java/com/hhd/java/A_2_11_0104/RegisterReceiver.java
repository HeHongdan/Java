package com.hhd.java.A_2_11_0104;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * 动态注册广播
 */
public class RegisterReceiver extends BroadcastReceiver {
    public RegisterReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String info = intent.getStringExtra("register_receiver");
        Toast.makeText(context,info,Toast.LENGTH_LONG).show();
    }
}
