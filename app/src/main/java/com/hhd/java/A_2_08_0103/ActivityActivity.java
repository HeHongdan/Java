package com.hhd.java.A_2_08_0103;

import android.app.Activity;
import android.os.Bundle;

import com.hhd.java.R;

/**
 * A08-01 Activity概述
 * A08-02 状态与生命周期概述
 * A08-03 状态与生命周期详解
 */

public class ActivityActivity extends Activity {

    /**
     * 创建Activity：用于初始化（视图、数据）、注册监听器、创建组件
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity);
        System.out.println("onCreate->");

    }

    /**
     * 开始状态：用于显示界面但还未可交互
     */
    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("onStart->");
    }

    /**
     * 重新开始：用于Stop状态会到Resume状态
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("onRestart->");
    }

    /**
     * 运行状态：可以和用户进行交互
     */
    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("onResume->");
    }

    /**
     * 暂停状态：界面失去焦点（如透明、覆盖、返回、Home键）
     * 1、其他优先级高的app需要更多内存时，可能会Kill掉此Activity
     * 2、当弹框式Activity退出后（未Kill掉）通过以下步骤恢复：onPause-〉onResume
     */
    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("onPause->");
    }

    /**
     * 停止状态：界面完全不见（覆盖、返回、Home键）
     * 1、其他优先级高的app需要更多内存时，可能会kill掉此Activity
     * 2、当重新回到此界面时（未Kill掉）通过以下步骤恢复：onStop-〉onRestart-〉onStart-〉onResume
     */
    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("onStop->");
    }

    /**
     * 销毁Activity：用于销毁（视图、数据）、组件或者释放其他
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("onDestroy->");
    }

}
