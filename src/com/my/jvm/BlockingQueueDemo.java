package com.my.jvm;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * author: Ma Xiangguang
 * date: 2020/3/10 15:05
 * version: 1.0
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws Exception {
//        List list = null;
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(3);

        //抛异常
//        System.out.println(blockingQueue.add("a"));
//        System.out.println(blockingQueue.add("b"));
//        System.out.println(blockingQueue.add("c"));
//        System.out.println(blockingQueue.add("d"));
//
//        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());

        System.out.println("-------------------");


        //特殊值
        /*System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d"));

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());*/

        System.out.println("--------------------------");

        //阻塞
        /*blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
//        blockingQueue.put("d");

        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
//        System.out.println(blockingQueue.take());*/

        //超时
        blockingQueue.offer("a",2L, TimeUnit.SECONDS);
        blockingQueue.offer("b",2L, TimeUnit.SECONDS);
        blockingQueue.offer("c",2L, TimeUnit.SECONDS);
        blockingQueue.offer("d",2L, TimeUnit.SECONDS);

        System.out.println(blockingQueue.poll(2L,TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2L,TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2L,TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2L,TimeUnit.SECONDS));
    }
}
