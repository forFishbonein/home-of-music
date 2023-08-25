/*
 * @Time : 2022/8/9 16:59
 * @Author : hao
 * @File : UserThreadLocal.java
 * @Software : IntelliJ IDEA
 */
package com.hao.springbootmusic.utils;

import com.hao.springbootmusic.dao.pojo.HaoSysUser;

public class UserThreadLocal {

    private UserThreadLocal(){} //设置为私有的，不能new

    //ThreadLocal：线程变量隔离：相当于给每个线程开辟一个存储区，并且这些存储区之间没有任何的关系！
    //也就是说我们想在每个线程里面存储线程的特定信息时就会用到ThreadLocal
    //这里我们就是给每一个线程存储一个用户信息，以便查看每个访问的用户是谁！
    private static final ThreadLocal<HaoSysUser> LOCAL = new ThreadLocal<>(); //属性

    public static void put(HaoSysUser sysUser) {

        LOCAL.set(sysUser);
    }
    public static HaoSysUser get() {

        return LOCAL.get();
    }
    public static void remove(){

        LOCAL.remove();
    }
}

