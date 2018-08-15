package com.hhd.java._Demo.CountDownTimer;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hhd.java.R;
import com.hhd.java._Demo.HHDLogger.MyLogger;

public class CountDownTimerActivity extends Activity {

    MyLogger HHDLog = MyLogger.HHDLog();
    public static CountDownTimer countDownTimer;
    private TextView tv2;

    private MyCountDownTimer mc;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down_timer);

        tv2 = (TextView) findViewById(R.id.show2);

        tv = (TextView) findViewById(R.id.show);

    }

    public void start(View view) {
        Toast.makeText(CountDownTimerActivity.this, "开始计时", Toast.LENGTH_SHORT).show();// toast有显示时间延迟
        mc = new MyCountDownTimer(30000, 1000);
        mc.start();
    }

    public void cancel(View view) {
        Toast.makeText(CountDownTimerActivity.this, "取消计时", Toast.LENGTH_SHORT).show();// toast有显示时间延迟
        mc.cancel();
    }

    public void restart(View view) {
        Toast.makeText(CountDownTimerActivity.this, "重新开始计时（10s）", Toast.LENGTH_SHORT).show();// toast有显示时间延迟
        mc = new MyCountDownTimer(10000, 1000);
        mc.start();
    }

    /**
     * 继承 CountDownTimer 防范
     *
     * 重写 父类的方法 onTick() 、 onFinish()
     */

    class MyCountDownTimer extends CountDownTimer {
        /**
         *
         * @param millisInFuture
         *            表示以毫秒为单位 倒计时的总数
         *
         *            例如 millisInFuture=1000 表示1秒
         *
         * @param countDownInterval
         *            表示 间隔 多少微秒 调用一次 onTick 方法
         *
         *            例如: countDownInterval =1000 ; 表示每1000毫秒调用一次onTick()
         *
         */
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            tv.setText("完成");
        }

        @Override
        public void onTick(long millisUntilFinished) {
            Log.i("MainActivity", millisUntilFinished + "");
            tv.setText("倒计时(" + millisUntilFinished / 1000 + ")...");
        }
    }
//┏┓　　　┏┓
//┏┛┻━━━┛┻┓
//┃　　　　　　　┃ 　
//┃　　　━　　　┃
//┃　┳┛　┗┳　┃
//┃　　　　　　　┃
//┃　　　┻　　　┃
//┃　　　　　　　┃
//┗━┓　　　┏━┛
//┃　　　┃   神兽保佑　　　　　　　　
//┃　　　┃   代码无BUG！
//┃　　　┗━━━┓
//┃　　　　　　　┣┓
//┃　　　　　　　┏┛
//┗┓┓┏━┳┓┏┛
//  ┃┫┫　┃┫┫
//  ┗┻┛　┗┻┛



    /** 当前心跳的时间 */
    private int timeCntHeart = 0;
    private boolean isC=true;

    @Override
    protected void onResume() {
        super.onResume();
        countDownTimer = new CountDownTimer(Long.MAX_VALUE, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {//固定间隔被调用
                HHDLog.i("计时一次"+millisUntilFinished);
                if (isC){
                    timeCntHeart++;
                    tv2.setText("计时(" + timeCntHeart + ")...");
                    if (timeCntHeart % 10 == 0) {
                        HHDLog.e("当前心跳时间="+timeCntHeart);
                    }
                    if (timeCntHeart >= 30) {
                        HHDLog.e("timeCntHeart >= 30，心跳时间="+timeCntHeart);
                    }
                }
            }
            @Override
            public void onFinish() {

            }
        };

    }

    /** 开始计时 */
    public void s(View view) {
        countDownTimer.start();
        Toast.makeText(CountDownTimerActivity.this, "开始计时", Toast.LENGTH_SHORT).show();// toast有显示时间延迟
        HHDLog.e("开始计时");
    }

    public void r(View view){
        timeCntHeart=0;
        isC=true;
        tv2.setText("计时(0)...");
        Toast.makeText(CountDownTimerActivity.this, "清零计时", Toast.LENGTH_SHORT).show();// toast有显示时间延迟
        HHDLog.i("清零计时，timeCntHeart="+timeCntHeart+"，isC="+isC);
    }

    public void p(View view){
        countDownTimer.onPause();
        Toast.makeText(CountDownTimerActivity.this, "暂停计时", Toast.LENGTH_SHORT).show();// toast有显示时间延迟
        HHDLog.e("暂停计时");
    }



}

