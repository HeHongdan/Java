package com.hhd.java.A_2_11_0104;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * 顺序广播
 * Created by HHJ on 2016/5/26.
 */

public class OrderedBroadcast100 extends BroadcastReceiver {
    public OrderedBroadcast100() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        //取出上个广播传参
        Bundle data = getResultExtras(true);//false：没有参数就不创建
        String dataInfo = data.getString("dataInfo");

        String info = intent.getStringExtra("ordered_broadcast");
        Toast.makeText(context,info + "\n权限较低：100 <- " + dataInfo,Toast.LENGTH_SHORT).show();
    }
}
