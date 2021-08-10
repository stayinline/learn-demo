package com.myself.test;

import org.apache.tomcat.jni.Thread;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;

public class TestJDK {

    public static void main(String[] args) {
        ClassLoader classLoader;

        Compiler compiler;

        System system;

        AbstractList abstractList;

        ThreadPoolExecutor threadPoolExecutor;

        Condition condition;

        AbstractQueuedSynchronizer abstractQueuedSynchronizer;

        LinkedBlockingQueue linkedBlockingQueue;
        LinkedBlockingDeque linkedBlockingDeque;


        ArrayList arrayList;

        AbstractMap abstractMap;

        AbstractSet abstractSet;

        HashMap hashMap;

        Hashtable hashtable;


        HashSet hashSet;

        LinkedHashMap<Integer, Integer> linkedHashMap = new LinkedHashMap<>();
        testLinkedHashMap(linkedHashMap);


        LinkedHashSet linkedHashSet;

        TreeMap treeMap;

        TreeSet treeSet;

        Queue queue;

        ConcurrentHashMap concurrentHashMap;

        CompletableFuture completableFuture;

        LinkedList linkedList;


    }

    private static void testLinkedHashMap(LinkedHashMap<Integer, Integer> linkedHashMap) {
        linkedHashMap.put(1, 1);
        linkedHashMap.put(2, 2);
        linkedHashMap.put(3, 3);
        linkedHashMap.put(13, 3);
        linkedHashMap.put(12, 3);
        linkedHashMap.put(14, 3);
        System.out.println(linkedHashMap);
        linkedHashMap.get(2);
        System.out.println(linkedHashMap);


    }
}
