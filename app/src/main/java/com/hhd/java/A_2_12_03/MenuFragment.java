package com.hhd.java.A_2_12_03;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hhd.java.R;

/**
 * A12-05 Fragment与Activity交互
 *
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment implements View.OnClickListener {

    private MyMenuListener myMenuListener;

    public MenuFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        myMenuListener = (MyMenuListener) activity;//实例化
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        view.findViewById(R.id.bt_News).setOnClickListener(this);
        view.findViewById(R.id.bt_Musics).setOnClickListener(this);
        return view;
    }

//    public void newsClick(View view){
//        myMenuListener.changeValue("News");
//    }
//    public void musicsClick(View view){
//        myMenuListener.changeValue("Musics");
//    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.bt_News:
                myMenuListener.changeValue("新闻");
                break;
            case R.id.bt_Musics:
                myMenuListener.changeValue("音乐");
                break;
        }
    }

    /**
     * 定义回调接口,用于和Activity交互（宿主Activity实现）
     */
    public static interface MyMenuListener{
        public void changeValue(String value);
    }

}
