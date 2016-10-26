package com.hhd.java.J_1_16_01;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hhd.java.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;

/**
 * J16-01 List集合
 *
 * ArrayList：数组列表（适合遍历、查询）
 * Vector：向量(线程安全/同步)
 * LinkedList：链表(适合删除、添加)
 */
public class ListActivity extends Activity {

    private EditText editText;
    private TextView textView;

    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        editText = (EditText) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.textView);

        data = editText.getText().toString();
    }

    /**
     *ArrayList（数组列表）特点：对象数组的实现，默认大小为10，线程不安全，（适合遍历、查询、(有序)）效率较高，JDK1.2版本
     * @param view
     */
    ArrayList<String> arrayList = new ArrayList<>();
    public void addArrayList(View view) {
//        if (TextUtils.isEmpty(data)){
//            Toast.makeText(this, "请输入内容",Toast.LENGTH_LONG).show();
//            return;
//        } else {
//            arrayList.add(data);
//        }
        arrayList.add(data);//添加元素（add）
    }
    public void queryArrayListSize(View view) {
        textView.setText(String.valueOf(arrayList.size()));//元素长度（size）
    }
//    public void queryArrayList(View view) {
//        textView.setText(String.valueOf(arrayList.get(Integer.valueOf(data))));
//    }

    /**
     *Vector（向量）特点：对象数组的实现，线程安全的（同步），效率较低，JDK1.0版本
     * @param view
     */
    Vector<String> vector = new Vector<String>();
    public void addVector(View view){
        vector.add(data);//添加元素（add）
    }
    public void queryVectorSize(View view) {
        textView.setText(String.valueOf(vector.size()));//元素长度（size）
    }

    LinkedList<String> linkedList = new LinkedList<>();

    /**
     * LinkedList（链表）特点：使用双向链表实现，适合删除、添加（插入）操作
     * @param view
     */
    public void addLinkedList(View view){
        linkedList.add(data);//添加元素（add）
    }
    public void queryLinkedListSize(View view){
        textView.setText(String.valueOf(linkedList.size()));//元素长度（size）
    }

}
