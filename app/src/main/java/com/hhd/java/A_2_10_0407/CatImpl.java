package com.hhd.java.A_2_10_0407;

import android.os.RemoteException;

/**
 * 业务接口具体实现类
 */

class CatImpl extends ICat.Stub {//Stub是Binder是IBinder是ICat（Cat）

    private String name;

    //自定义类型-----------------------------------------------------------------------------------┐
    @Override
    public Person getPerson() throws RemoteException {
        Person p = new Person();
        p.name = "HHD";
        p.work = "工程师";
        return p;
    }
    //自定义类型-----------------------------------------------------------------------------------┘

    @Override
    public void setName(String name) throws RemoteException {
        this.name = name;
    }

    @Override
    public String desc() throws RemoteException {
        return "你好！我是“" + name + "”，我是一只猫。";
    }

    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

    }
}
