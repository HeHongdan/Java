package com.hhd.java.A_2_11_0104;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hhd.java.R;

public class RegisterReceiverActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_receiver);
    }

    public void registerClick(View view){
        Intent intent = new Intent("com.hhd.action.REGISTER_RECEIVER");
        intent.putExtra("register_receiver","动态注册广播！");
        this.sendBroadcast(intent);
    }

    /**
     * 注册广播接受者
     */
    private RegisterReceiver registerReceiver = new RegisterReceiver();//创建一个
    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.hhd.action.REGISTER_RECEIVER");
        registerReceiver(registerReceiver,filter);
    }

    /**
     * 解除注册广播接受者
     */
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(registerReceiver);
    }
}
