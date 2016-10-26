package com.hhd.java.J_1_09_0406;


import android.app.Activity;

import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hhd.java.R;


/**
 * J09-04 链表数据结构实现_1
 * J09-05 链表数据结构实现_2
 * J09-06 包装类访问修饰符和小结
 */
public class LinkedListActivity extends Activity implements OnClickListener{

    LinkedListNodeManager linkedListNodeManager = new LinkedListNodeManager();

    private EditText editText;
    private TextView textView;
    private Button add, del, query;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linked_list);

        editText = (EditText) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.textView);
        add = (Button) findViewById(R.id.bt_add);
        del = (Button) findViewById(R.id.bt_del);
        query = (Button) findViewById(R.id.bt_query);

        add.setOnClickListener(this);
        del.setOnClickListener(this);
        query.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_add:
                linkedListNodeManager.addNode(getData());
                editText.setText(null);
                break;
            case R.id.bt_del:
                linkedListNodeManager.delNode(getData());
                editText.setText(null);
                break;
            case R.id.bt_query:
                showResult();
                editText.setText(null);
                break;
        }
    }

    private String getData() {
        String node = null;
        if (editText.getText().toString().equals("")) {
            Toast.makeText(this, "请输入节点", Toast.LENGTH_LONG).show();
        } else {
            node = editText.getText().toString();
        }
        return node;
    }

    private void showResult(){
        String result = linkedListNodeManager.queryAll();
        textView.setText(result);
    }

}

