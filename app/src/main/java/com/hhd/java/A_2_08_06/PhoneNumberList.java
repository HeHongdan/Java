package com.hhd.java.A_2_08_06;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hhd.java.R;

/**
 * A08-06 处理返回结果
 */

public class PhoneNumberList extends Activity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number_list);

        listView = (ListView) findViewById(R.id.listView);

        final String[] numbers = {"17474749174", "13838389438", "18944447777", "13295279527", "13548694869"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, numbers);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                String number = numbers[position];

                System.out.println(number+"-------------------------------");

                Intent intent = new Intent();
                intent.putExtra("number",number);
                setResult(RESULT_OK,intent);//设置返回结果(RESULT_OK一般用这个值为-1表示成功)
                finish();//结束当前界面

            }
        });

    }
}
