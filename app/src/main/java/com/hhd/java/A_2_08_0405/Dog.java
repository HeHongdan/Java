package com.hhd.java.A_2_08_0405;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * descreption:
 * company:
 * Created by vince on 15/2/4.
 */
class Dog implements Parcelable{//打包对象
    String name;
    int age;
    String type;

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public int describeContents() {//描述内容
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {//标记，目标
        parcel.writeString(name);
        parcel.writeInt(age);
        parcel.writeString(type);
    }

    //对象的创建器(解包成对象)
    public static final Parcelable.Creator<Dog> CREATOR
            = new Parcelable.Creator<Dog>() {
        public Dog createFromParcel(Parcel in) {
//            return new Dog(in);
            Dog dog = new Dog();
            dog.name = in.readString();
            dog.age = in.readInt();
            dog.type = in.readString();
            return dog;
        }

        public Dog[] newArray(int size) {
            return new Dog[size];
        }
    };

}

//public class Dog implements Parcelable{//打包对象
//    String name;
//    int age;
//    String type;
//
//    @Override
//    public String toString() {
//        return "Dog{" +
//                "name='" + name + '\'' +
//                ", age=" + age +
//                ", type='" + type + '\'' +
//                '}';
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(name);
//        dest.writeInt(age);
//        dest.writeString(type);
//    }
//
//    //对象的创建器
//    public static final Creator<Dog> CREATOR = new Creator<Dog>() {
//        public Dog createFromParcel(Parcel in) {
//            Dog dog = new Dog();
//            dog.name = in.readString();
//            dog.age = in.readInt();
//            dog.type = in.readString();
//            return dog;
//        }
//
//        public Dog[] newArray(int size) {
//            return new Dog[size];
//        }
//    };
//
//}
