package com.hhd.java.A_2_12_03;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.hhd.java.R;

/**
 * A12-03 Fragment出入栈操作
 *
 */
public class PopBackTaskActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_back_task);
    }

    public void oneClick(View view) {
//        PopBackFragment pbf = new PopBackFragment("第一个Fragment");//创建Fragment//传统的传参方式
        PopBackFragment pbf = PopBackFragment.getInstance("第一个Fragment");//创建Fragment//Fragment标准的传参方式（不能new）
        FragmentTransaction ft = getFragmentManager().beginTransaction();//添加Fragment到Activity
        ft.replace(R.id.content,pbf);//replace：替换
        //把当前Fragment添加到Activity栈
        ft.addToBackStack(null);//null:一个标记
        ft.commit();//提交事物
    }

    public void twoClick(View view) {
        PopBackFragment pbf = PopBackFragment.getInstance("第二个Fragment");
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content,pbf);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){//返回键
            if (getFragmentManager().getBackStackEntryCount()==0){//getBackStackEntryCount：返回栈的总数
                finish();
            } else {
                getFragmentManager().popBackStack();//如果有数量就（出栈操作）
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}


