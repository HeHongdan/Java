package com.hhd.java.J_1_17_0103;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hhd.java.R;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * J17-01 HashMap
 * J17-02 HashMap补充、Hashtable和TreeMap
 * J17-03 Map迭代、HashCode、Collections、对象关系、堆栈队列
 *
 * Map：操作两个对象的集合（键值对），Key也可以是自定义对象
 * Created by HHJ on 2016/5/30.
 */

public class MapActivity extends Activity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        textView = (TextView) findViewById(R.id.textView);

    }

    /**
     * HashMap：
     *
     * 1、线程不安全（同步），效率低
     * 2、使用哈希表（hash）实现
     * 3、键值不能重复（如果键值相同则覆盖原值）
     * 4、（哈希表保存对象时）会根据Key的hashcode值对HashMap的容量（16）求余，决定该对象在数组的位置，如果该位置已有对象那就以链表结构来存储对象
     * 5、每次扩充2倍
     *
     * 注：初始容量为16(数组)；加载因子为0.75（数组满75%需要重新扩充(又称哈希表重新散列)）；链表来实现Key和Value
     * @param view
     */
    Map<Integer,String> hashMap = new HashMap<>();
//    Map<String,String> hashMap = new HashMap<>();
    public void addHashMap(View view){
        hashMap.put(1,"一");
//        System.out.println("1".hashCode()%16);
        hashMap.put(2,"二");
//        System.out.println("2".hashCode()%16);
        hashMap.put(1,"三");//键值重复会覆盖原来
//        System.out.println("3".hashCode()%16);
        Toast.makeText(this,"HashMap长度为：" + hashMap.size() + "\n\n值分别为：" + hashMap.get(1) + "，" + hashMap.get(2),Toast.LENGTH_LONG).show();//.get取值
    }
    public void queryHashMapSize(View view){
        textView.setText(String.valueOf(hashMap.size()));
    }

    /**
     * Hashtable：
     *
     * 1、线程安全（同步），效率低（有别于HashMap的地方）
     * 2、实现方式和HashMap基本相同
     * 3、初始容量为11，每次扩充2倍+1
     *
     */
    Map<Integer,String> hashtable = new Hashtable<>();
//    Map<String,String> hashMap = new HashMap<>();
    public void addHashtable(View view){
        hashtable.put(1,"一");
        hashtable.put(2,"二");
        hashtable.put(1,"三");//键值重复会覆盖原来
        Toast.makeText(this,"Hashtable长度为：" + hashtable.size() + "\n\n值分别为：" + hashtable.get(1) + "，" + hashtable.get(2),Toast.LENGTH_LONG).show();//.get取值
    }
    public void queryHashtableSize(View view){
        textView.setText(String.valueOf(hashtable.size()));
    }


    /**
     *TreeMap：
     *
     * 1、二叉树数据结构实现（红黑树(平衡二叉树)）
     *
     */
    Map<Integer,Cat> treeMap = new TreeMap<>();
//    Map<String,String> hashMap = new HashMap<>();
    public void addTreeMap(View view){
        treeMap.put(1,new Cat("猫1",1));
        treeMap.put(2,new Cat("猫2",2));
        treeMap.put(2,new Cat("猫2(覆盖)",3));//键值重复会覆盖原来
        Toast.makeText(this,"TreeMap长度为：" + treeMap.size() + "\n\n值分别为：" + treeMap.get(1) + "，" + treeMap.get(2),Toast.LENGTH_LONG).show();//.get取值
    }
    public void queryTreeMapSize(View view){
        textView.setText(String.valueOf(treeMap.size()));
    }


    /**
     * Iterator：
     * 遍历（迭代）
     * @param view
     */
    public void queryIterator(View view){
        HashMap<String, Cat> map = new HashMap<>();
        map.put("猫1",new Cat("Tom",1));
        map.put("猫2",new Cat("mimi",2));
        map.put("猫3",new Cat("miaomiao",3));

        //遍历
        Set<String> keys = map.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            Cat cat = map.get(key);

            Toast.makeText(this,key + "->" + cat,Toast.LENGTH_LONG).show();
        }
    }

//    Collection（List和Set父接口）与Collections（工具类）

    //Stack

    //Queue


}
