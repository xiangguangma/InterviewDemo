package com.my.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * author: Ma Xiangguang
 * date: 2020/3/13 11:22
 * version: 1.0
 * @author HuaWei
 */
public class RedisDemo {
    public static void main(String[] args) throws IllegalAccessException {

        Jedis jedis = new Jedis("localhost");

        System.out.println("连接成功");
//        testString(jedis);

        testHash(jedis, new User(12, "xiaoming", 20));

        jedis.close();
    }

    private static void testHash(Jedis jedis, User u) throws IllegalAccessException {

        String key = "users";


        if (jedis.exists(key)) {
            Map<String, String> map = jedis.hgetAll(key);
            System.out.println("map = " + map);
        } else {
            Field[] declaredFields = u.getClass().getDeclaredFields();

            for (Field declaredField : declaredFields) {
                System.out.println("declaredField = " + declaredField.getName());
                Type genericType = declaredField.getGenericType();
                System.out.println("genericType = " + genericType);


            }
        }
    }

    private static void testString(Jedis jedis) {
        //查看服务是否运行
        System.out.println("服务正在运行: " + jedis.ping());


        Map<String, String> map = jedis.hgetAll("h1");
        System.out.println(map);


        jedis.set("test", "testValue");
        jedis.expire("test", 3);

        // 线程等待 3 秒
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String test = jedis.get("test");
        System.out.println(test);

        User user = new User(11, "xiaohua", 20);
        String h2 = jedis.set("h2", user.toString());
        System.out.println("h2 = " + h2);
    }
}
