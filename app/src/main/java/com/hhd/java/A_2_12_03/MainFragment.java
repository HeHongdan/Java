package com.hhd.java.A_2_12_03;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hhd.java.R;

/**
 * A12-05 Fragment与Activity交互
 *
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private TextView tv_value;


    public MainFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        tv_value = (TextView) view.findViewById(R.id.tv_value);
        return view;
    }

    public void changeValue(String value){
        tv_value.setText(value);
    }

}
