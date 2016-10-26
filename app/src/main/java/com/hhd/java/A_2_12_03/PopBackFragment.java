package com.hhd.java.A_2_12_03;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hhd.java.R;

/**
 * A12-04 Fragment传参方式
 */
public class PopBackFragment extends Fragment {

    public PopBackFragment() {
    }

    //这种传统的传参方法屏幕状态改变时参数会丢失
//    private String title;
//    public PopBackFragment(String title){
//        this.title = title;
//    }

    /**
     * Fragment标准的传参方式（Bundle）
     * 内部的机制保存了传参（restoreAllState-〉instance(重新实例化)-〉clazz.newInstance args(反射：默认调用无参构造方法)）
     * @param title
     * @return
     */
    public static PopBackFragment getInstance(String title){
        PopBackFragment p = new PopBackFragment();
        Bundle b = new Bundle();
        b.putString("title",title);
        p.setArguments(b);
        return p;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pop_back, container, false);
        TextView tv = (TextView) view.findViewById(R.id.textView_fragment);
//        tv.setText(title);
        tv.setText(getArguments().getString("title"));//获取上个Activity传过来的参数

        return view;
    }

}

