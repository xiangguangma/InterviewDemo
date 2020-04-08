package com.my.redis;

import lombok.*;


/**
 * @author: Ma Xiangguang
 * @date: 2020/3/13 14:39
 * @version: 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    public int id;
    private String name;
    public int age;

}
