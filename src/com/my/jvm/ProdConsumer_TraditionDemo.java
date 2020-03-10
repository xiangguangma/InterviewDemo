package com.my.jvm;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData{
    private int number = 0;
    //创建重复锁
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();

    public void increment() throws Exception{
        //锁住资源
        lock.lock();
        try{
            //判断
            while (number != 0) {
                //阻塞
                c1.await();
            }
            number++;

            System.out.println(Thread.currentThread().getName()+"\t " + number);

            //唤醒
            c1.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //释放资源
            lock.unlock();
        }
    }

    public void decrement() throws Exception{
        //锁住资源
        lock.lock();
        try{
            //判断
            while (number == 0) {
                //阻塞
                c1.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName()+"\t " + number);
            //唤醒
            c1.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //释放资源
            lock.unlock();
        }
    }

}
/**
 * author: Ma Xiangguang
 * date: 2020/3/10 16:48
 * version: 1.0
 *
 * 生产者和消费者模式
 *  题目：一个初始值为0的变量，两个线程对其交替操作，一个加1，一个减1，来 5 轮
 *
 *  1.线程        操作          资源类
 *  2.判断（while）        干活          通知
 *
 *      组合：     synchronized     wait       notify
 *                 lock             await       signal
 *  3.防止虚假唤醒机制
 *
 *  虚假唤醒指的是使用if 进行判断，因此需要使用while判断
 */
public class ProdConsumer_TraditionDemo {
    public static void main(String[] args) {

        ShareData shareData = new ShareData();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
    }
}
