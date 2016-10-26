package com.hhd.java.A_2_10_0407;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Toast;

import com.hhd.java.R;

public class BoundServiceActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bound_service);
    }

    /**
     * 绑定服务的回调方法
     */
    private ICat cat;//接口
    private boolean mBound = false;//标记是否绑定成功
    private ServiceConnection sc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {//绑定成功回调
            //iBinder://IBinder返回具体业务类：CatImpl(ICat->Cat)是Stub是Binder是IBinder(BoundService.class)
            cat = ICat.Stub.asInterface(iBinder);//涉及：IO、代理设计模式、网络传输等知识点
            mBound = true;
            System.out.println(cat + "----------------------------------");
            //本地服务（）
            //远程服务（IPC(代理)）android:process="remote"
            Toast.makeText(BoundServiceActivity.this,"绑定服务成功",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {//绑定异常回调
            mBound = false;
        }
    };

    /**
     * 绑定服务
     * @param view
     */
    public void boundClick(View view){
        Intent intent = new Intent(this,BoundService.class);
        //异步绑定，如果绑定成功后会回调onServiceConnected
        bindService(intent, sc, Context.BIND_AUTO_CREATE);//BIND_AUTO_CREATE：绑定前没有服务则创建
    }

    /**
     * 解绑服务
     * @param view
     */
    public void unBoundClick(View view){
        if (mBound){//判断是否在绑定状态
            unbindService(sc);
            Toast.makeText(BoundServiceActivity.this,"解绑服务成功",Toast.LENGTH_LONG).show();
        }
    }

    /**
     * IPC调用业务方法
     * @param view
     */
    public void callClick(View view){
        if (cat == null){
            return;
        }
        try {
            cat.setName("小黑");
//            Toast.makeText(this,cat.desc(),Toast.LENGTH_LONG).show();
            //自定义类型---------------------------------------------------------------------------┐
            Toast.makeText(this,cat.desc() + "\n猫主人是：" + cat.getPerson().toString(),Toast.LENGTH_LONG).show();
            //自定义类型---------------------------------------------------------------------------┘
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
