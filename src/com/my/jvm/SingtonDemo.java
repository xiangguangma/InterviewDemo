package com.my.jvm;

/**
 * author: Ma Xiangguang
 * date: 2020/3/9 15:44
 * version: 1.0
 */
public class SingtonDemo {

    //
    public static volatile SingtonDemo singtonDemo = null;

    public SingtonDemo() {
        System.out.println(Thread.currentThread().getName() + "\t 执行构造方法");
    }

    // DCL: double check Lock  双端检锁
    //单双端检锁 也不一定线程安全， 还存在指令重排，导致有名无实, 所以需要volatile 禁止指令重排
    public static SingtonDemo getInstance(){
        if (singtonDemo == null){
            synchronized (SingtonDemo.class){
                if (singtonDemo == null){
                    singtonDemo = new SingtonDemo();
                }
            }
        }
        return singtonDemo;
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                SingtonDemo.getInstance();

            },String.valueOf(i)).start();
        }

    }
}
