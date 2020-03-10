package com.my.jvm;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phone implements Runnable {

    //可重入锁
    public synchronized void sendSMS() {
        System.out.println(Thread.currentThread().getName() + "\t invoked sendSMS()");
        sendEmail();
    }

    public synchronized void sendEmail() {
        System.out.println(Thread.currentThread().getName() + "\t  ##### invoked sendEmail()");
    }

    //非公平锁
    Lock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void get() throws Exception {
        //锁住资源
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getId() + "\t invoked get()");
            set();
        } finally {
            //释放资源
            lock.unlock();
        }
    }

    public void set() throws Exception {
        //锁住资源
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getId() + "\t ### invoked set()");
        } finally {
            //释放资源
            lock.unlock();
        }
    }
}

/**
 * author: Ma Xiangguang
 * date: 2020/3/10 9:17
 * version: 1.0
 */
public class ReentrantLockDemo {
    public static void main(String[] args) {
//        Lock lock = new ReentrantLock();

        Phone phone = new Phone();

        new Thread(() -> {
            phone.sendSMS();
        }, "t1").start();

        new Thread(() -> {
            phone.sendSMS();
        }, "t2").start();

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        Thread t3 = new Thread(phone);
        Thread t4 = new Thread(phone);

        t3.start();
        t4.start();
    }
}
