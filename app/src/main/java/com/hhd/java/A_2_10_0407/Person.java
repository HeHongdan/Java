package com.hhd.java.A_2_10_0407;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * aidl自定义类型(猫的主人)
 * Created by HHJ on 2016/5/25.
 */
class Person implements Parcelable {

    String name;
    String work;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", work='" + work + '\'' +
                '}';
    }

    /**
     * 打包传输后构建时的创建器
     */
    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>(){
        public Person createFromParcel(Parcel in){
            Person p = new Person();
            p.name = in.readString();
            p.work = in.readString();
            return p;
        }
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(work);
    }
}
