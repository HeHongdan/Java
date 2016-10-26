package com.hhd.java.A_2_11_0104;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hhd.java.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.Vector;

/**
 *
 * 广播的使用：
 * 1、创建一个继承BroadcastReceiver类（实现onReceive方法）
 * 2、注册广播（静态和动态）
 * 3、发送广播：（Activity）Intent->sendBroadcast
 * 4、（BroadcastReceiver）onReceive接收并处理广播
 */

public class BroadcastReceiverActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_receiver);
    }

    /**
     * 发送普通广播
     * @param view
     */
    public void normalClick(View view){
        Intent intent = new Intent("com.hhd.action.NORMAL_BROADCAST");
        intent.putExtra("broadcast_receiver","发送了一个普通广播！");
        this.sendBroadcast(intent);
    }

    /**
     * 发送有序广播
     * @param view
     */
    public void orderedClick(View view){
        Intent intent = new Intent("com.hhd.action.ORDERED_BROADCAST");
        intent.putExtra("ordered_broadcast","发送了一个有序广播！");
        //参数：intent，接收权限
        this.sendBroadcast(intent,null);
    }

    /**
     * 发送粘性广播
     * @param view
     */
    public void stickyClick(View view){
        Intent intent = new Intent("com.hhd.action.STICKY_BROADCAST");
        intent.putExtra("sticky_broadcast", "粘性广播");
        this.sendStickyBroadcast(intent);
    }

    /**
     * 接收粘性广播
     * @param view
     */
    public void openStickyClick(View view){
        Intent intent = new Intent(this,StickyBroadcastActivity.class);
        startActivity(intent);
    }

    /**
     * 发送动态广播
     * @param view
     */
    public void registerClick(View view){
        Intent intent = new Intent(this, RegisterReceiverActivity.class);
        startActivity(intent);
    }

}
