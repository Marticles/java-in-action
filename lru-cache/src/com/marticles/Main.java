package com.marticles;

public class Main {

    public static void main(String[] args) {
        // LRUCache<Integer, String> lruCache = new LRUCache<>(3);
        LRUCache2<Integer, String> lruCache = new LRUCache2<>(3);
        lruCache.put(1, "一");
        lruCache.put(2, "二");
        lruCache.put(3, "三");
        System.out.println("put前：" + lruCache);
        lruCache.put(4, "四");
        System.out.println("put后：" + lruCache);
        System.out.println("get前：" + lruCache);
        lruCache.get(2);
        System.out.println("get后：" + lruCache);
    }
}
