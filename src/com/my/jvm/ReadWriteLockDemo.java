package com.my.jvm;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache{
    private volatile Map<String,Object> map = new HashMap<>();

    private ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();

    public void put(String key, Object value){

        //锁住资源
        rwlock.writeLock().lock();
        try{

            System.out.println(Thread.currentThread().getName()+"\t 正在写入："+key);
            //模拟堵塞300毫秒
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t 写入完成");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //释放资源
            rwlock.writeLock().unlock();
        }
    }

    public void get(String key){
       //锁住资源
       rwlock.readLock().lock();
       try{
           System.out.println(Thread.currentThread().getName()+"\t 正在读取："+key);
           //模拟堵塞300毫秒
           try {
               TimeUnit.MILLISECONDS.sleep(300);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }

           map.get(key);
           System.out.println(Thread.currentThread().getName()+"\t 读取完成");
       }catch (Exception e){
           e.printStackTrace();
       }finally {
           //释放资源
           rwlock.readLock().unlock();
       }
    }
}

/**
 * author: Ma Xiangguang
 * date: 2020/3/10 11:03
 * version: 1.0
 *
 * 读写锁：
 *      多个线程读一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行
 *      但是
 *      如果有一个线程想去写共享资源，就不应该再有其他线程可以对该资源进行读或写
 *
 *      小结：
 *      读-读能共享
 *      读-写不能共享
 *      写-写不能共享
 *
 *      写操作：原子+独占，整个过程必须是一个完整的同一体，中间不许被分割，被打断
 *
 *
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {

        MyCache myCache = new MyCache();

        for (int i = 0; i < 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                myCache.put(tempInt+"",tempInt+"");
                System.out.println(Thread.currentThread().getName());
            },String.valueOf(i)).start();
        }

        for (int i = 0; i < 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                myCache.get(tempInt+"");
                System.out.println(Thread.currentThread().getName());
            },String.valueOf(i)).start();
        }



    }
}
