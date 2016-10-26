package com.hhd.java.J_1_18_0103;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.hhd.java.R;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tread);
    }

    /**
     * 继承Thread类实现线程（Thread继承Runnable）
     */
    public void treadClick(View view) {
        System.out.println(Thread.currentThread().getName() + "-〉线程");//.getName():当前线程的名字
        MyThread t = new MyThread();//创建线程对象
        t.start();//线程已准备就绪，等待CPU调度
//        t.run();//直接调用线程（不是启动线程）；而是在当前线程（UI线程）中执行run方法
    }
    class MyThread extends Thread {
        @Override
        public void run() {//在run()方法中编写业务逻辑代码
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(500);//线程休眠（暂停执行）//sleep：该线程不丢失任何监器（锁）的所属权
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + System.currentTimeMillis() + "-〉线程" + i);
            }
        }
    }


    /**
     * 实现Runnable接口实现线程（因为是接口所以可拓展性更好，推荐使用）
     */
    public void runnableClick(View view) {
        MyRunnable r = new MyRunnable();//不能像Thread直接new，而是实现接口
        Thread thread = new Thread(r, "Runnable");//"Runnable"：更改线程名称
        System.out.println("Runnable活动状态是：" + thread.isAlive());//isAlive()：是否活动状态
        thread.start();
        System.out.println("Runnable活动状态是：" + thread.isAlive());//isAlive()：是否活动状态
    }
    class MyRunnable implements Runnable {//哪个线程执行Runnable它就代表哪个线程
        @Override
        public void run() {//在run()方法中编写业务逻辑代码
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(500);//线程休眠（暂停执行）
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + System.currentTimeMillis() + "-〉线程" + i);//Thread.currentThread()//获取当前线程
            }
        }
    }


    /**
     * .sleep():线程休眠（暂停执行）【sleep（休眠）时该线程不丢失任何监视器(执行时间)的所属权；受到系统计时器和调度程序精度、准确性的影响。】
     * 可以实现2个线程交替执行
     *
     * .wait()：当前线程等待【wait（等待）时该线程让出监视器(执行时间)的所属权。线程会放弃对象锁，进入等待此对象的等待锁定池，只有针对此对象调用notify()方法后本线程才进入对象锁定池准备】
     */
    public void threadRunnableClick(View view){
        MyThread t = new MyThread();//创建线程对象
        t.start();//线程已准备就绪，等待CPU调度

        MyRunnable r = new MyRunnable();//不能像Thread直接new，而是实现接口
        Thread thread = new Thread(r, "Runnable");//"Runnable"：更改线程名称
        thread.start();
    }


    /**
     * join()：等待线程终止
     * interrupt()：中断线程
     * setPriority()：设置抢占CPU执行时间的优先级（只是几率大而已不一定抢占到）
     * setDaemon(true)：设置为守护线程（如果没有非守护线程，守护线程会自动停止）
     * yield()：让出单次UPU的执行时间
     */
    public void joinClick(View view){
        MyThread t = new MyThread();//创建线程对象
        t.setPriority(Thread.MAX_PRIORITY);//设置抢占CPU执行时间的优先级（只是几率大而已不一定抢占到）
        t.setDaemon(true);//设置为守护线程（如果没有非守护线程，守护线程会自动停止）
        t.start();//线程已准备就绪，等待CPU调度

        MyRunnable r = new MyRunnable();//不能像Thread直接new，而是实现接口
        Thread thread = new Thread(r, "Runnable");//"Runnable"：更改线程名称
        thread.start();
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + System.currentTimeMillis() + "-〉线程" + i);//Thread.currentThread()//获取当前线程
            if (i == 3) {
                try {
                    t.join();//join()：等待线程终止//等待t线程终止执行完再执行当前线程（UI线程）
//                    t.interrupt();//中断线程
//                    t.yield();//让出单次UPU的执行时间
//                    t.wait();//当前线程等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 线程同步3种方法：
     *
     * 1、同步代码块
     * 2、同步方法
     * 2、Lock（效率较高）
     *
     * 注：同步会牺牲性能（换取线程安全）
     */
    public void lockClick(View view){
        MRunnable m = new MRunnable();
        Thread t1 = new Thread(m);
        Thread t2 = new Thread(m);
        t1.start();
        t2.start();
    }
    class MRunnable implements Runnable {//哪个线程执行Runnable它就代表哪个线程
        private int flag;//标记打饭状态
        private Object obj = new Object();//同步代码块对象（锁）
        @Override
        public void run() {//在run()方法中编写业务逻辑代码
            synchronized(obj) {//同步代码块（局部同步）---------------------------------------------
                for (int i = 0; i < 5; i++) {
                    flag = 0;
                    System.out.println("开始打饭〈-〉" + flag);
                    try {
                        Thread.sleep(300);//线程休眠（暂停执行）
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    flag = 1;
                    System.out.println("饭已打好〈-〉" + flag);//Thread.currentThread()//获取当前线程
                }
            }
        }

        //同步方法：同步的锁对象是当前对象（同步对象是(上面创建的)obj）-----------------------------
        public synchronized void eat1(){
            for (int i = 0; i < 5; i++) {
                flag = 0;
                System.out.println("开始吃饭〈-〉" + flag);
                try {
                    Thread.sleep(300);//线程休眠（暂停执行）
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                flag = 1;
                System.out.println("饭已吃完〈-〉" + flag);//Thread.currentThread()//获取当前线程
            }
        }
        public synchronized void eat2(){//同步时（锁定对象）其他类也不能调用该同步方法，因为当前对象已被锁定
            System.out.println("eat2");
        }

        //互斥锁Lock（调用者可以自行控制，其他两种方法由系统控制）----------------------------------
        private final ReentrantLock lock = new ReentrantLock();//ReentrantLock：互斥锁
        public void wash(){
            lock.lock();//（开始执行任务）上锁_JDK1.5
            for (int i = 0; i < 5; i++) {
                flag = 0;
                System.out.println("开始洗碗〈-〉" + flag);
                try {
                    Thread.sleep(300);//线程休眠（暂停执行）
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                flag = 1;
                System.out.println("碗已洗好〈-〉" + flag);//Thread.currentThread()//获取当前线程
            }
            lock.unlock();//（执行完任务）解锁_JDK1.5
        }
    }


}
