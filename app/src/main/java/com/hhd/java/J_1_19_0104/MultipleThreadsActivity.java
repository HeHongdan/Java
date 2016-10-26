package com.hhd.java.J_1_19_0104;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.hhd.java.R;

public class MultipleThreadsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_threads);
    }

    /**
     * 中断线程
     */
    MyThread my = new MyThread();
    Thread t1 = new Thread(my);

    //中断线程
    public void interruptClick(View view) {
        //t1.stop();//停止线程（已过时）
//        for (int i = 0; i<10;i++){
//            try {
//                Thread.sleep(1000);
        my.flag = false;
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }

    //启动线程
    public void startClick(View view) {
        t1.start();
    }

    static class MyThread implements Runnable {
        public boolean flag;//停止线程标记

        public MyThread() {
            flag = true;
        }

        @Override
        public void run() {
            int i = 0;
            while (flag) {
                System.out.println("i=" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
                if (i == 100) break;//执行到100就退出
            }
        }
    }


    /**
     * 生产者
     * 消费者
     */
    public void producterCustomersClick(View view) {
        Food food = new Food();
        Producter p = new Producter(food);
        Customers c = new Customers(food);
        Thread t1 = new Thread(p);
        Thread t2 = new Thread(c);
        t1.start();
        t2.start();
    }

    //生产者
    class Producter implements Runnable {
        private Food food;

        public Producter(Food food) {
            this.food = food;
        }

        @Override
        public void run() {
            for (int i = 0; i < 50; i++) {
                if (i % 2 == 0) {
//                    food.setName("银耳莲子");
//                    try {
//                        Thread.sleep(500);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    food.setPrice("结账银耳莲子5元");
                    food.set("银耳莲子", "结账银耳莲子5元");
                } else {
//                    food.setName("糖醋里脊");
//                    try {
//                        Thread.sleep(500);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    food.setPrice("结账糖醋里脊10元");
                    food.set("糖醋里脊", "结账糖醋里脊10元");
                }
            }
        }
    }

    //消费者
    class Customers implements Runnable {
        private Food food;

        public Customers(Food food) {
            this.food = food;
        }

        @Override
        public void run() {
            for (int i = 0; i < 50; i++) {
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(food.getName()+"->"+food.getPrice());
                food.get();
            }
        }
    }

    class Food {
        private String name;//菜名
        private String price;//价格

        private boolean flag = true;//true表示可以生产

        //同步set方法(生产)
        public synchronized void set(String name, String price) {
            //表示不能生产
            if (!flag) {
                try {
                    this.wait();//当前线程进入等待状态，让出CPU，并释放该监视器上的锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
//            else {
                this.setName(name);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.setPrice(price);
                flag = false;////表示可以消费不能生产
                this.notify();//唤醒该监视器上的其他（一个）线程
//            }
        }

        //同步get方法(消费)
        public synchronized void get() {
            if (flag) {
                try {
                    this.wait();//当前线程进入等待状态，让出CPU，并释放该监视器的（锁）都有权
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.getName() + "->" + this.getPrice());
            flag = true;//表示可以生产不能消费
            this.notify();
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Food() {
        }

        public Food(String name, String price) {
            this.name = name;
            this.price = price;
        }
    }
}
