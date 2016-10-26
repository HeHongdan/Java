package com.hhd.java.A_2_08_0708;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.hhd.java.R;

/**
 * A08-08 屏幕方向旋转
 */
public class ScreenOrientationChange extends Activity {

    private int i_savedInstanceState = 0;
    private int i2 = 0;
    private int i_onConfigurationChanged = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //-----------------------------------------------------------------------------------------┐
        /**
         * A08-07 屏幕方向与显示方式
         * 这些方法必需在setContentView之前
         */

        /**
         * 设置全屏模式：
         * 1、可以通过代码实现
         * 2、在清单文件可以通过设置Activity的主题:android:theme="@android:style/Theme.DeviceDefault.NoActionBar.Fullscreen"
         *
         * 设置窗体模式：
         * 在清单文件中设置主题：android:theme="@android:style/Theme.DeviceDefault.Dialog"
         */

        //通过代码设置屏幕方向
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//android:screenOrientation="landscape"
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//android:screenOrientation="portrait"

        //设置全屏//设置全屏模式
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //去标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //-----------------------------------------------------------------------------------------┘


        setContentView(R.layout.activity_orientation_change);
        //还原转屏前状态值
        if (savedInstanceState != null){
            i_savedInstanceState = savedInstanceState.getInt("i_savedInstanceState",0);//如果没有就赋值为0
        }
    }

    public void changeClick(View view){
        i_savedInstanceState++;
        System.out.println("i_savedInstanceState=" + i_savedInstanceState);
    }

    /**
     * 保存转屏前状态
     *
     * 屏幕切换时默认情况下会重新创建Activity
     * 为了保存当前Activity的状态，我们可以重写onSaveInstanceState方法来保存相关的数据
     * 然后在onCreate方法中还原数据
     *
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("onSaveInstanceState->执行");
        outState.putInt("i_savedInstanceState",i_savedInstanceState);//保存状态(i1的值)
    }

    /**
     * 恢复转屏前状态
     * @param savedInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        i2++;
//        System.out.println("onRestoreInstanceState->" + i2);
    }

    /**
     * 横屏竖屏配置信息（android:configChanges="keyboard|orientation|screenSize"）
     *
     * 1、不会重新创建Activity也就不会调用onSaveInstanceState，但值不会还原
     * 2、可以分别设计横竖布局（需要配置layout-land必须设置为android:configChanges="keyboardHidden|orientation"但onConfigurationChanged不会执行）
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        i_onConfigurationChanged++;
        System.out.println("onConfigurationChanged->执行" + i_onConfigurationChanged);
    }
}
