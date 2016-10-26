package com.hhd.java.A_2_10_0407;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * A10-04～07 BoundService_1～4
 *
 * BoundService（绑定服务）：
 * 通过绑定服务来实现功能的步骤：
 * 1、客户端通过bindService方法来绑定一个服务对象（CatImpl），如果绑定成功，会调用ServiceConnection接口方法onServiceConnected
 * 2、通过Service组件来暴露业务接口
 * 3、服务端通过创建一个*.aidl文件来定义一个可以被客户端调用的业务接口
 * （aidl文件要求：
 *      1.不能有修饰符(Interface)，类似接口的写法
 *      2.支持的8种基本类型、String、CharSequence、List<String>、Map<String>、自定义类型
 *          自定义类型：(1)实现Parcelable接口(2)定义一个aidl文件声明该类型(这里使用parcelable Person)(3)在其他aidl文件中使用必须使用import导包
 * ）
 * 4、服务端需要提供一个业务接口的实现类，通常会继承(extends)Stub类
 * 5、通过Service的onBind方法返回被绑定的业务对象
 * 6、客户端如果绑定成功，就可以像调用自己的方法一样调用远程(服务端)的业务对象方法
 *
 * BoundService用处（特点）：1、startedService启动后会长期存在(只要不停止) 2、BoundService启动后必须调用onUnbind才能停止(不管界面退不退出)
 * 因此通常实际使用时：先startedService把服务启动，再BoundService(绑定服务)：如音乐播放器界面退出(解绑)还可以后台播发音乐(除非用户停止服务或退出app)
 *
 *
 * 例子解析（借用打印机）：Service：中介(BoundService)、借方：客户端(BoundServiceActivity)、被借方：打印机(服务端(ICat)->要满足一定(协议)业务接口)
 */
public class BoundService extends Service {
    public BoundService() {
    }

    /**
     * 创建服务
     */
    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * 绑定服务
     * @param intent
     * @return
     */
    @Override
    public IBinder onBind(Intent intent) {//IBinder返回具体业务类：CatImpl(ICat->Cat)是Stub是Binder是IBinder
        return new CatImpl();//体现多态
    }

    /**
     * 解绑服务
     * @param intent
     * @return
     */
    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    /**
     * 销毁服务
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
