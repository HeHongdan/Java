package com.hhd.java._Demo.HHDLogger;

import android.app.Activity;
import android.os.Bundle;

import com.hhd.java.R;

/**
 *
 */
public class HHDLogActivity extends Activity {

    MyLogger HHDLog=MyLogger.HHDLog();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hhdlog);

        HHDLog.v("级别1，无用信息");
        HHDLog.d("级别2，调试信息");
        HHDLog.i("级别3，一般信息");
        HHDLog.w("级别4，警告信息");
        HHDLog.e("级别5，错误信息");
        boolean isSafe = false;
        assert isSafe;
        System.out.println("断言通过!");

    }

}
