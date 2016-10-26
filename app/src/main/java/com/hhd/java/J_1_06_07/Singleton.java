package com.hhd.java.J_1_06_07;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hhd.java.R;

/**
 * 单例模式
 *
 * 通常在工具类中使用单例设计模式（只有方法没有属性(对象的状态)、该类使用非常频繁）
 * 一个类有且仅有一个实例，并且自行实例化向整个系统提供。
 */
public class Singleton extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleton);

        Single s = Single.getInstance();
        s.method();

        Single2 s2 = Single2.getInstance();
        s2.method();
    }
}

/**
 * 饿汉式(推荐使用，多线程中懒汉式会出现问题)
 */
class Single{
    private static Single single = new Single();//二是类定义中含有一个该类的静态私有对象（类的内部创建一个自身的对象）
    private Single(){}//一是单例模式的类只提供私有的构造函数（构造函数私有化、为防止在该类外部实例化）
    public static Single getInstance(){
        return single;//三是该类提供了一个静态的公有的函数用于创建或获取它本身的静态私有对象（用public函数返回该对象）
    }
    public void method(){
        System.out.println("饿汉式被调用了");
    }
}

/**
 * 懒汉式
 */
class Single2{
    private static Single2 single2 = null;//二是类定义中含有一个该类的静态私有对象（类的内部创建一个自身的对象）
    private Single2(){}//一是单例模式的类只提供私有的构造函数（构造函数私有化）
    public static Single2 getInstance(){
        if (single2 == null){
            single2 = new Single2();
        }
        return single2;//三是该类提供了一个静态的公有的函数用于创建或获取它本身的静态私有对象（用public函数返回该对象）
    }
    public void method(){
        System.out.println("懒汉式被调用了");
    }
}