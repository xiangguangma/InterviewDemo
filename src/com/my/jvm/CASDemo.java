package com.my.jvm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * author: Ma Xiangguang
 * date: 2020/3/9 15:56
 * version: 1.0
 *
 * CAS : compareAndSwap
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger =  new AtomicInteger(5);

        System.out.println(atomicInteger.compareAndSet(5, 2019)+"\t current data :" + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 1024)+"\t current data :" + atomicInteger.get());

    }
}
