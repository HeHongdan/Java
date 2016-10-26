package com.hhd.java.A_2_08_0405;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.hhd.java.R;

/**
 * A08-04 传递简单数据
 * A08-05 传递自定义对象数据
 */

public class PutExtraBundle extends Activity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_putextra_bundle);

        editText = (EditText) findViewById(R.id.editText);
    }

    /**
     * 传递简单数据
     * @param view
     */
    public void sendClick(View view){
        String data = editText.getText().toString();
        Intent intent = new Intent(this,ActivityA.class);

        //装要传递的数据
//        Bundle bundle = new Bundle();
//        bundle.putString("data",data);
//        intent.putExtra("bundle",bundle);

        intent.putExtra("data",data);//其实这里封装了bundle
        intent.putExtra("age",18);
        startActivity(intent);
    }

    /**
     * 传递自定义对象数据(Serializable序列化：io传输耗性能)
     * @param view
     */
    public void sendObj1Click(View view){
        Cat cat = new Cat();
        cat.name = "cater";
        cat.age = 2;
        cat.type = "波斯猫";

        Intent intent = new Intent(this, ActivityB.class);
        intent.putExtra("cat", cat);
        startActivity(intent);
    }

    /**
     * 传递自定义对象数据(Parcelable打包：)
     * @param view
     */
    public void sendObj2Click(View view){
        Dog dog = new Dog();
        dog.name = "doger";
        dog.age = 1;
        dog.type = "藏獒";

        Intent intent = new Intent(this, ActivityC.class);
        intent.putExtra("dog", dog);
        startActivity(intent);
    }

}
