package com.my.model;

/**
 * @author: Ma Xiangguang
 * @date: 2020/4/1 10:49
 * @version: 1.0
 */
public class Singleton {

    private Singleton() {
    }

    private static Singleton singleton = null;

    public static Singleton getInstance(){
        if (singleton == null){
            singleton = new Singleton();
        }
        return singleton;
    }

    public static void main(String[] args) {

        System.out.println("hello world");
    }

}
