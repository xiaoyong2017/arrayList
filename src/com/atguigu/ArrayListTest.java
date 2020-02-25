package com.atguigu;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ArrayListTest {
    //线程不安全demo演示
    //Exception in thread "t1" java.util.ConcurrentModificationException
    public static void main(String args[]) {
        List list = new ArrayList(); // java.util.ConcurrentModificationException
        // List list = new Vector();//  解决办法1 在ArrayList的基础上方法上加synchronized
//        List list = Collections.synchronizedList(new ArrayList()); //解决办法二 ：使用集合工具类Collections提供法人方法
//        CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<>(); //解决办法三：使用CopyOnWriteArrayList读写分离思想
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().replace("-", "").substring(0, 6));
                System.out.println(list);
            }, "t1").start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

    }

 /*   public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add("a");
        list.add("b");
        list.add("c");
        Iterator iterator = list.iterator();
        list.add("d");

        while (iterator.hasNext()) {
            Object next = iterator.next();
            System.out.println(next);
        }
    }*/


}
