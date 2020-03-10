package com.my.jvm;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class TestData{
    private int number = 1; //A:1 B:2 C:3

    //创建重复锁
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();


    public void print(){
        //锁住资源
        lock.lock();
        try{

            //判断
            while (number != 1) {
                //阻塞
                c1.await();
            }

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
 * date: 2020/3/10 17:45
 * version: 1.0
 */
public class SyncAndReentrantLockDemo {
    public static void main(String[] args) {


    }
}
