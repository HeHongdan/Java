package com.hhd.java.A_2_11_0104;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class OrderedBroadcast200 extends BroadcastReceiver {
    public OrderedBroadcast200() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String info = intent.getStringExtra("ordered_broadcast");
        Toast.makeText(context,info + "\n权限较高：200",Toast.LENGTH_SHORT).show();

        //传参到下个广播
        Bundle data = new Bundle();
        data.putString("dataInfo","权限较高广播传参");
        this.setResultExtras(data);

        //中断有序广播（可以用于拦截广告短信）
//        this.abortBroadcast();//abort:使中止
    }
}
