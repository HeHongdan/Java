package com.hhd.java.A_2_08_06;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.hhd.java.R;

/**
 * A08-06 处理返回结果
 */

public class ResultActivity extends Activity {

    private static final int REQUESTCODE_1 = 0x1;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        editText = (EditText) findViewById(R.id.editText);

    }

    /**
     * 选择一个电话号码
     * @param view
     */
    public void selectClick(View view){
        Intent intent = new Intent(this, PhoneNumberList.class);
        startActivityForResult(intent, REQUESTCODE_1);//intent,请求编码
    }

    /**
     * 重写onActivityResult方法来处理返回的结果
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUESTCODE_1 && resultCode == RESULT_OK){
            String number = data.getStringExtra("number");
            System.out.println(number+"-----------------------------");
            editText.setText(number);
        }
    }

    /**
     * 拨打电话
     * @param view
     */
    public void callClick(View view){
        String number = editText.getText().toString();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);//发一个打电话的广播
        intent.setData(Uri.parse("tel:"+number));//带数据过去
        startActivity(intent);

    }
}
