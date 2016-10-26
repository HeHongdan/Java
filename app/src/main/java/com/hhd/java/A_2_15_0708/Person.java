package com.hhd.java.A_2_15_0708;


import java.util.List;

/**
 * Created by HHJ on 2016/6/4.
 */

public class Person {
//    long id = -1;
//    String text = null;
//    User user = null;
//    List geo = null;

    String id;
    String doc;
    String geo;

    @Override
    public String toString() {
        return '\n' + "Person{" +
                "id='" + id + '\'' +
                ", doc='" + doc + '\'' +
                ", geo='" + geo + '\'' +
                '}';
    }
}
