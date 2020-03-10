package com.my.jvm;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * author: Ma Xiangguang
 * date: 2020/3/9 17:42
 * version: 1.0
 *
 * ArrayList 线程不安全
 *
 * 1,故障现象
 *      java.util.ConcurrentModificationException
 * 2，导致原因
 *
 *
 * 3，解决方法
 *      new Vector<>();
 *      Collections.synchronizedList(new ArrayList<>());
 *      new CopyOnWriteArrayList<>();
 *
 * 4，优化建议
 */
public class ArrayListDemo {
    public static void main(String[] args) {
//        new ArrayList<Integer>();

//        List<String> list = Arrays.asList("a","b","c");
//        list.stream().forEach(System.out:: println);
        List<String> list = new CopyOnWriteArrayList<>();//new Vector<>(); //new ArrayList<>();


        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }


    }

}
