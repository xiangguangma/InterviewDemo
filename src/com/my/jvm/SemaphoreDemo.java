package com.my.jvm;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * author: Ma Xiangguang
 * date: 2020/3/10 14:49
 * version: 1.0
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);


        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();//开抢
                    System.out.println(Thread.currentThread().getName()+"\t 抢到车位" );
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release(); //释放
                }
            }, String.valueOf(i)).start();
        }

    }
}
