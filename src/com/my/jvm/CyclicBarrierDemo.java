package com.my.jvm;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * author: Ma Xiangguang
 * date: 2020/3/10 14:37
 * version: 1.0
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        //public CyclicBarrier(int parties, Runnable barrierAction) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("======开始开会");
        });

        for (int i = 0; i <=6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName());
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }

    }
}
