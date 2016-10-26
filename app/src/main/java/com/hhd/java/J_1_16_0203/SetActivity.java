package com.hhd.java.J_1_16_0203;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hhd.java.R;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

/**
 * J16-02 Set集合_HashSet
 *
 * HashSet（基于HashMap实现）：
 * 1、值唯一性（不能重复）
 * 2、不保证迭代顺序
 * 3、不保证该顺序恒久不变
 *
 * TreeSet（基于TreeMap实现）：
 * 1、值唯一性（不能重复）
 * 2、有序排列
 *
 * LinkedHashSet（继承HashSet实现）：
 * 1、值唯一性（不能重复）
 * 2、按添加顺序排列
 *
 */
public class SetActivity extends Activity {

//    private EditText editText, editText2;
    private TextView textView;

//    String data;
//    String age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hast_set);

//        editText = (EditText) findViewById(R.id.editText);
//        editText2 = (EditText) findViewById(R.id.editText2);
        textView = (TextView) findViewById(R.id.textView);

//        data = editText.getText().toString();
//        age = editText2.getText().toString();
    }


    //HashSet--------------------------------------------------------------------------------------┐
    /**
     * @param view
     */
    java.util.Set<String> hashSet = new HashSet<>();
    public void addHashSet(View view){
//        hashSet.add(data);//添加元素（add）
        hashSet.add("x");
        hashSet.add("r");
        hashSet.add("x");
        Toast.makeText(this,"为" + hashSet.size() + "个",Toast.LENGTH_SHORT).show();
    }
    public void queryHashSetSize(View view) {
        textView.setText(String.valueOf(hashSet.size()));//元素长度（size）
    }

    /**
     * 添加对象元素时判断是否重复：
     * 1、先调用对象的hashCode方法求值，如果hash值在集合中不存在，那么可以添加该对象（不是重复的对象）
     * 2、如果hash值相同，还不能确定对象是否相同（因为不同的对象有可能产生相同的hashCode值），那么需要再调用equals方法来验证
     * 3、equals方法返回true（表示两个对象相同），返回false（表示两个对象不同），那么可以添加该对象（不是重复的对象）
     *
     * 如果需要在程序中有这样的需求：两个对象的值相同就认为两个对象是同一个对象（前提是使用HashSet来存储）
     * 这时就需要重写对象（类）中的hashCode方法和equals方法
     * @param view
     */
    java.util.Set<Student> hashSet2 = new HashSet<>();
    public void addHashSetObject(View view){
        Student s1 = new Student("x","1");//名称，年龄
        Student s2 = new Student("r","1");
        Student s3 = new Student("x","2");
        Student s4 = new Student("x","1");//s4和s3为同一个对象
        hashSet2.add(s1);//添加对象（add）
        hashSet2.add(s2);
        hashSet2.add(s3);
        hashSet2.add(s4);
        Toast.makeText(this,"为" + hashSet2.size() + "个 <- hashCode + equals防重复",Toast.LENGTH_SHORT).show();

    }
    public void queryHashSetObjectSize(View view) {
        textView.setText(String.valueOf(hashSet2.size()));
    }
    //HashSet--------------------------------------------------------------------------------------┘



    //TreeSet--------------------------------------------------------------------------------------┐
    /**
     * @param view
     */
    java.util.Set<String> treeSet = new TreeSet<>();
    public void addTreeSet(View view){
//        hashSet.add(data);//添加元素（add）
        treeSet.add("x");
        treeSet.add("r");
        treeSet.add("x");
        Toast.makeText(this,"为" + treeSet.size() + "个",Toast.LENGTH_SHORT).show();
    }
    public void queryTreeSetSize(View view) {
        textView.setText(String.valueOf(treeSet.size()));//元素长度（size）
    }

    /**
     * 添加对象元素时防止重复：
     * 1、在TreeSet集合中添加自定义的对象，必须实现Comparable接口，因为添加方法会使用compareTo方法来验证对象的排序位置
     * 2、并验证对象是否重复（如果返回结果为0，则表示两个对象重复），返回结果不同（-1小，1大），按照大小进行排序
     * 3、按自然顺序排序（从小到大）
     *
     * 如果需要在程序中有这样的需求：两个对象的值相同就认为两个对象是同一个对象（前提是使用TreeSet来存储）
     * 这时就需要实现对象（类）中的compareTo方法每个属性进行比较（如：比较完年龄还需比较名称……）
     * @param view
     */
    java.util.Set<Cat> treeSetObject = new TreeSet<>();
    public void addTreeSetObject(View view){
        Cat c1 = new Cat("x",1);//名称，id
        Cat c2 = new Cat("r",1);
        Cat c3 = new Cat("x",2);
        Cat c4 = new Cat("x",1);//c4和c1为同一个对象
        treeSetObject.add(c1);//添加对象（add）
        treeSetObject.add(c2);
        treeSetObject.add(c3);
        treeSetObject.add(c4);
        Toast.makeText(this,"为" + treeSetObject.size() + "个 <- Comparable防重复",Toast.LENGTH_SHORT).show();

    }
    public void queryTreeSetObjectSize(View view) {
        textView.setText(String.valueOf(treeSetObject.size()));
    }
    //TreeSet--------------------------------------------------------------------------------------┘


    //LinkedHashSet--------------------------------------------------------------------------------┐
    /**
     * @param view
     */
    java.util.Set<String> linkedHashSet = new LinkedHashSet<>();
    public void addLinkedHashSet(View view){
//        hashSet.add(data);//添加元素（add）
        linkedHashSet.add("x");
        linkedHashSet.add("r");
        linkedHashSet.add("x");
        Toast.makeText(this,"为" + linkedHashSet.size() + "个",Toast.LENGTH_SHORT).show();
    }
    public void queryLinkedHashSetSize(View view) {
        textView.setText(String.valueOf(linkedHashSet.size()));//元素长度（size）
    }

    /**
     * 添加对象元素时防止重复：
     * 1、从HashSet继承而来，确保对象的插入顺序（和HashSet的最大区别）
     * 2、由双向链表（适用于插入、删除） + HashMap表实现
     *
     * @param view
     */
    java.util.Set<Student> linkedHashSetObject = new LinkedHashSet<>();
    public void addLinkedHashSetObject(View view){
        Student s1 = new Student("x","1");//名称，年龄
        Student s2 = new Student("r","1");
        Student s3 = new Student("x","2");
        Student s4 = new Student("x","1");//s4和s3为同一个对象
        linkedHashSetObject.add(s1);//添加对象（add）
        linkedHashSetObject.add(s2);
        linkedHashSetObject.add(s3);
        linkedHashSetObject.add(s4);
        Toast.makeText(this,"为" + linkedHashSetObject.size() + "个 <- hashCode + equals防重复",Toast.LENGTH_SHORT).show();

    }
    public void queryLinkedHashSetObjectSize(View view) {
        textView.setText(String.valueOf(linkedHashSetObject.size()));
    }
    //LinkedHashSet--------------------------------------------------------------------------------┘



}
