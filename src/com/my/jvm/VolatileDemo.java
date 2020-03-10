package com.my.jvm;

import java.util.concurrent.TimeUnit;

/**
 * author: Ma Xiangguang
 * date: 2020/3/6 16:43
 * version: 1.0
 */
public class VolatileDemo {

    public static void main(String[] args) {

//        seeOkByVolatile();

        MyData myData = new MyData();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {

                for (int j = 0; j < 1000; j++) {
                    myData.addPlus();
                }
                System.out.println(Thread.currentThread().getName());
            },String.valueOf(i)).start();
        }

        while (Thread.activeCount() > 2){
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() +"\t number = "+ myData.number);




    }

    /**
     *  Volatitle 的可见性验证
     *  1. int number = 0; number 变量变化后没有可见性
     *  2. volatile int number = 0; 解决了可见性问题
     *
     */
    private static void seeOkByVolatile() {
        MyData myData = new MyData();

        new Thread(() -> {

            System.out.println(Thread.currentThread().getName() + "\t come in"  );

            try {
                TimeUnit.SECONDS.sleep(3);
                myData.addTo60();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "\t number:" + myData.number  );


        },"AA").start();

        while (myData.number == 0){

        }

        System.out.println(Thread.currentThread().getName() + "\t missing is over"  );
    }


}

class MyData{
    volatile int number = 0;

    public void addTo60(){
        this.number = 60;
    }
    public void addPlus(){
        number++;
    }
}
