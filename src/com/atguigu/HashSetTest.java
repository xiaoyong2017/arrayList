package com.atguigu;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class HashSetTest {
    //Exception in thread "t1" java.util.ConcurrentModificationException
    public static void main(String args[]) {
        //Set<Object> set = new HashSet<>();
        //Set<Object> set = Collections.synchronizedSet(new HashSet<>());
        CopyOnWriteArraySet set = new CopyOnWriteArraySet();
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().replace("-", "").substring(0, 6));
                System.out.println(set);
            }, "t1").start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
    }
}
