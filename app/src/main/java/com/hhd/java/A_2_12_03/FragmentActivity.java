package com.hhd.java.A_2_12_03;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hhd.java.R;

/**
 * A12-05 Fragment与Activity交互
 * 1、创建Activity和Fragment
 * 2、Activity（宿主）中实例化Fragment
 * 3、Fragment（菜单界面）定义接口并实例化监听事件（代表宿主Activity）
 * 4、Fragment（内容界面）定义业务逻辑方法
 * 5、Activity（宿主）实现接口（本例实现Menu的接口、Main的方法）
 */
public class FragmentActivity extends Activity implements MenuFragment.MyMenuListener {
    private MenuFragment menuFragment;
    private MainFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        //静态方式添加Fragment
        menuFragment = (MenuFragment) getFragmentManager().findFragmentById(R.id.menuFragment);
        mainFragment = (MainFragment) getFragmentManager().findFragmentById(R.id.mainFragment);
    }

    /**
     * 实现自定义回调接口（Fragment中定义）
     * @param value
     */
    @Override
    public void changeValue(String value) {
        mainFragment.changeValue(value);
    }

}
