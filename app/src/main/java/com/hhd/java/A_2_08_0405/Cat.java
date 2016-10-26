package com.hhd.java.A_2_08_0405;

import java.io.Serializable;

/**
 * descreption:
 * company:
 * Created by vince on 15/2/4.
 */
class Cat implements Serializable{//序列化对象

    String name;
    int age;
    String type;

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", type='" + type + '\'' +
                '}';
    }
}
