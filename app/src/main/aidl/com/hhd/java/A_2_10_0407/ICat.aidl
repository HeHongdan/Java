// ICat.aidl
package com.hhd.java.A_2_10_0407;

import com.hhd.java.A_2_10_0407.Person;

// Declare any non-default types here with import statements

interface ICat {

   //自定义类型------------------------------------------------------------------------------------┐
     Person getPerson();//aidl自定义类型
   //自定义类型------------------------------------------------------------------------------------┘


    void setName(String name);

    String desc();

    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
}
