package com.my.jvm;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * author: Ma Xiangguang
 * date: 2020/3/9 17:19
 * version: 1.0
 */
public class ABADemo {

    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {

       new Thread(() -> {
           atomicReference.compareAndSet(100, 101);
           atomicReference.compareAndSet(101, 100);
       },"t1").start();

       new Thread(() -> {
           try {
               TimeUnit.SECONDS.sleep(1);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           System.out.println(atomicReference.compareAndSet(100, 2019)+"\t " + atomicReference.get());

       },"t2").start();

       try {
           TimeUnit.SECONDS.sleep(2);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
        System.out.println("============以下是ABA问题解决===========");

       new Thread(() -> {
           int stamp = atomicStampedReference.getStamp();
           System.out.println(Thread.currentThread().getName()+"\t 第一次版本号：" + stamp);
           try {
               TimeUnit.SECONDS.sleep(1);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           atomicStampedReference.compareAndSet(100,101,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
           atomicStampedReference.compareAndSet(101,100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
           atomicStampedReference.compareAndSet(100,101,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);

       },"t3").start();

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t 第一次版本号：" + stamp);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            boolean result = atomicStampedReference.compareAndSet(100, 101, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName()+"\t 修改成功否："+result+" \t 版本号：" + atomicStampedReference.getStamp());

        },"t4").start();
    }
}
