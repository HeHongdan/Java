package com.hhd.java.A_2_15_0708;

import android.app.Activity;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hhd.java.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;


/**
 * A15-07 JSON解析_1
 * A15-08 JSON解析_2
 */
public class JsonActivity extends Activity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_reader);
        textView = (TextView) findViewById(R.id.textView);
    }


    /**
     * JsonReader解析Json
     *
     * 步骤：
     * 1、创建保存对象（数据）的数组（容器）
     * 2、获取需要解析的数据
     * 3、数据转换为JsonReader
     * 4、开始解析对象/数组（循环(跳到下个对象)）
     * 5、创建接收值的对象（循环(给相应属性赋值)）
     * 6、结束解析当前的对象/数组
     * 7、添加对象（数据）到list（容器）
     * 8、返回所有对象的list（容器）
     *
     * @return
     */
    public void jsonReaderClick(View view) {
        ArrayList<Person> list = jsonReader();//执行方法（jsonReader()），接收其返回的list
        for (int i = 0; i < list.size(); i++) {//循环输出list的对象
            System.out.println(list.get(i));//控制台测试
            textView.setText(list.toString());//手机测试
        }
    }

    private ArrayList<Person> jsonReader() {//
//        String json = "{{\"id\": 912345678901,\"text\": \"How do I read JSON on Android?\",\"geo\": null,\"user\": {\"name\": \"android_newb\",\"followers_count\": 41},{\"id\": 912345678902,\"text\": \"@android_newb just use android.util.JsonReader!\",\"geo\": [50.454722, -104.606667],\"user\": {\"name\": \"jesse\",\"followers_count\": 2}}";//创建本地Json数据
//        JsonReader jr = new JsonReader(new StringReader(json));//转换数据
        ArrayList<Person> list = new ArrayList<>();//创建保存对象的数组
        Reader r = new InputStreamReader(getResources().openRawResource(R.raw.person));//获取本地Json数据
        JsonReader jr = new JsonReader(r);//Reader转换为JsonReader
        try {
            jr.beginObject();//开始解析对象（User）
            while (jr.hasNext()) {//跳到下个对象
                if ("user".equals(jr.nextName())) {//遇到user表示解析到user对象
                    jr.beginArray();//开始解析数组
                    while (jr.hasNext()) {//
                        Person p = new Person();//创建接收值的对象
                        jr.beginObject();//开始解析User里边的Person对象
                        while (jr.hasNext()) {//
                            String s = jr.nextName();//对象（Person）属性的值（名称）
                            if ("id".equals(s)) {//给相应属性赋值
                                p.id = jr.nextString();//给对象（Person）id属性赋值
                            } else if ("doc".equals(s)) {//
                                p.doc = jr.nextString();//
                            } else if ("geo".equals(s)) {//
                                p.geo = jr.nextString();//
                            }
                        }
                        jr.endObject();//结束解析当前的对象（Person）
                        list.add(p);//添加到list
                    }
                    jr.endArray();//（解析全部对象）结束解析本次解析
                }
            }
            jr.endObject();//结束解析当前的对象（User）
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;//返回所有对象的list
    }


    /**
     * 生成Json数据（对象）
     *
     * 步骤：
     * 1、创建保存对象（数据）的数组（容器）
     * 2、创建接收值的对象
     * 3、给对象属性赋值
     * 4、添加对象到list（容器）
     * 5、创建创建保存对象的数组 +　创建接收值的数组（循环添加对象）
     * 6、创建接收数据对象 + 创建接收值的对象（循环(给相应属性赋值)）
     * 7、属性添加到Object(对象) + 对象属性添加到Json数组
     * 8、JSONArray（数组）添加到JSONObject（对象）
     * 9、返回所有对象（容器）
     *
     * @param view
     */
    public void createJsonClick(View view) {
        ArrayList<Person> list = new ArrayList<>();//创建保存对象的数组列表（容器）
        Person p1 = new Person();//创建接收数据对象
        p1.id = "001";//给对象属性赋值
        p1.doc = "生成Json对象001";//给对象属性赋值
        p1.geo = "深圳";//给对象属性赋值
        Person p2 = new Person();
        p2.id = "002";
        p2.doc = "生成Json对象002";
        p2.geo = "上海";
        list.add(p1);//添加对象到list（容器）
        list.add(p2);
        JSONArray array = new JSONArray();//创建保存对象的数组
        JSONObject json = new JSONObject();//创建接收数据对象
        try {
            for (int i = 0; i < list.size(); i++) {
                Person p = list.get(i);//创建接收值的数组（循环添加对象）
                JSONObject object = new JSONObject();//创建接收值的对象（循环(给相应属性赋值)）
                object.put("id", p.id);//属性添加到Object(对象)
                object.put("doc", p.doc);//
                object.put("geo", p.geo);//
                array.put(object);//对象属性添加到Json数组
            }
            json.put("person", array);//JSONArray（数组）添加到JSONObject（对象）
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(json.toString());//控制台测试
        textView.setText(json.toString());//
    }


    /**
     * Gson解析Json
     *
     * 步骤：
     * 1、创建Gson（解析）对象
     * 2-、获取需要解析的数据
     * 3、创建（new）一个类（数据转换的类型(对象)）
     * 4-、开始解析对象/数组（循环(跳到下个对象)）
     * 5、创建接收对象的容器（循环(给相应对象赋值)）
     * 6-、添加对象（数据）到list（容器）
     * 7、返回所有对象（容器）
     *
     * @return
     */
    public void gsonParseJsonClick(View view) {
        Gson gson = new Gson();//创建Gson（解析）对象
        Reader r = new InputStreamReader(getResources().openRawResource(R.raw.person_gson));//获取需要解析的数据
        Type type = new TypeToken<ArrayList<Person>>() {}.getType();//相当于new一个类//class MyTypeToken extends TypeToken<ArrayList<Person>>{}
        ArrayList<Person> list = gson.fromJson(r, type);//创建接收对象的容器（循环(给相应对象赋值)）
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));//
            textView.setText(list.toString());//
        }
    }


    /**
     * Gson生成Json数据（对象）
     *
     * 步骤：
     * 1-、创建保存对象（数据）的数组（容器）
     * 2-、创建接收值的对象（给对象属性赋值） + 添加待解析对象（数据）到list（容器）
     * 3、创建Gson（解析）对象
     * 4、创建（new）一个类（数据转换的类型(对象)）
     * 5、Gson开始解析对象/数组（传入需要解析的数据） + 创建接收对象的容器（循环(给相应对象赋值)） +　添加对象（数据）到list（容器）
     * 7、返回所有对象（容器）
     *
     * @return
     */
    public void gsonCreatJsonClick(View view) {
        ArrayList<Person> list = new ArrayList<>();//创建保存对象（数据）的数组（容器）
        Person p1 = new Person();//创建接收值的对象
        p1.id = "001";//给对象属性赋值
        p1.doc = "Gson生成Json对象001";//给对象属性赋值
        p1.geo = "深圳";//给对象属性赋值
        Person p2 = new Person();
        p2.id = "002";
        p2.doc = "Gson生成Json对象002";
        p2.geo = "上海";
        list.add(p1);//添加待解析对象（数据）到list（容器）
        list.add(p2);
        Gson gson = new Gson();//创建Gson（解析）对象
        Type type = new TypeToken<ArrayList<Person>>() {}.getType();//相当于new一个类//class MyTypeToken extends TypeToken<ArrayList<Person>>{}
        String json = gson.toJson(list, type);//Gson开始解析对象/数组（传入需要解析的数据） + 创建接收对象的容器（循环(给相应对象赋值)） +　添加对象（数据）到list（容器）
        System.out.println(json);//
        textView.setText(json);//
    }

}
