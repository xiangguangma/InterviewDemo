package com.my.jvm;


import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MySource{
    private volatile boolean FLAG = true; //默认开启，进行生产+消费
    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<String> blockingQueue = null;

    //构造尽量传接口
    public MySource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println("blockingQueue = " + blockingQueue.getClass().getName());
    }

    //生产
    public void myProd() throws Exception{
        String data = null;
        boolean retValue = false;
        while (FLAG){
            data = atomicInteger.incrementAndGet() +"";
            blockingQueue.offer(data, 2L, TimeUnit.SECONDS);

            if (retValue){
                System.out.println(Thread.currentThread().getName()+"\t 插入数据队列"+data+"成功");
            }else {
                System.out.println(Thread.currentThread().getName()+"\t 插入数据队列"+data+"成功");
            }
        }

        System.out.println(Thread.currentThread().getName()+"\t 被叫停，FLAG=false,生产结束");
    }

    //消费
    public void myConsumer()throws Exception{
        String result = null;
        while (FLAG){
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);

            if (null == result || result.equalsIgnoreCase("")){
                FLAG = false;
                System.out.println(Thread.currentThread().getName()+"\t 超过2秒没有取到数据，消费退出");

                System.out.println();
                System.out.println();
                return;
            }

            System.out.println(Thread.currentThread().getName()+"\t 消费队列"+result+"成功");
        }
        System.out.println(Thread.currentThread().getName()+"\t 被叫停，FLAG=false,消费结束");
    }

    //叫停
    public void stop(){
        this.FLAG = false;
    }


}
/**
 * author: Ma Xiangguang
 * date: 2020/3/11 11:22
 * version: 1.0
 *
 * volatile/CAS/atomicInteger/blockingQueue/线程交互
 * 串用
 */
public class ProConsumer_BlockQueueDemo {

    public static void main(String[] args) {


        MySource mySource = new MySource(new ArrayBlockingQueue<>(10));

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName()+"\t 生产线程启动");
                mySource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Pro").start();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName()+"\t 消费线程启动");
                mySource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Con").start();

        // 线程等待 5 秒
        try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }

        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println(Thread.currentThread().getName()+"\t 5 秒时间到，叫停线程");
        mySource.stop();

    }
}
