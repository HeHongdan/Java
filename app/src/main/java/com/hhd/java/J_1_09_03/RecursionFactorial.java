package com.hhd.java.J_1_09_03;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hhd.java.R;

/**
 * J09-03 递归算法
 */
public class RecursionFactorial extends Activity implements View.OnClickListener{
    private EditText editText, sum;
    Button button;

    private int shuru;
    private String s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recursion_factorial);

        editText = (EditText) findViewById(R.id.editText);
        sum = (EditText) findViewById(R.id.sum);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                quzhi();
                sum.setText(String.valueOf(factorial(shuru)));//int->String
//                sum.setText(Integer.toString(factorial(shuru)));

               /* String 转int
               int i=Integer.parseInt([String]);//直接使用静态方法，不会产生多余的对象，但会抛出异常。
               int i=integer.parseInt([String],int index);
               int i=integer.valueOf(my_str).intValue();相当于 new Integer(Integer.parseInt(my_str))，也会抛出异常。

                int 转 StringString
                s=String.valueOf(i);//直接使用String类的静态方法，只产生一个对象。
                String s=integer.toString(i);
                String s=""+i;//会产生两个String对象,不建议这种方法*/

                break;
        }
    }

    private int quzhi() {
        s = editText.getText().toString();
        if ("".equals(s)) {
            Toast.makeText(this, "请输入整数", Toast.LENGTH_LONG).show();
        } else {
            shuru = Integer.parseInt(s);
        }
        return shuru;
    }

    //递归阶乘-------------------------------------------------------------------------------------┐
    private static int factorial(int sr) {
        if (sr < 1)return 1;
        return sr * factorial(sr - 1);
    }
    //递归阶乘-------------------------------------------------------------------------------------┘
}
