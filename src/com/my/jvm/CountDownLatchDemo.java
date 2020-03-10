package com.my.jvm;

import lombok.Getter;

import java.util.concurrent.CountDownLatch;

enum CountyEnum {

    ONE(1,"齐"),
    TWO(2,"楚"),
    THREE(3,"燕"),
    FOUR(4,"赵"),
    FIVE(5,"韩"),
    SIX(6,"魏");

    @Getter
    private Integer retCode;
    @Getter
    private String retMessage;

    CountyEnum(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public static CountyEnum forEach_CountyEnum(int index){
        CountyEnum[] countyEnums = CountyEnum.values();
        for (CountyEnum anEnum : countyEnums) {
            if (index == anEnum.retCode){
                return anEnum;
            }
        }
        return null;
    }
}

/**
 * author: Ma Xiangguang
 * date: 2020/3/10 13:57
 * version: 1.0
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName());
            },CountyEnum.forEach_CountyEnum(i).getRetMessage()).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t 关门");
    }
}
