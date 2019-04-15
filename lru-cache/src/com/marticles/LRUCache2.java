package com.marticles;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * 基于HashMap与LinkedList实现LRU Cache
 * 链表尾部为最近最久未访问的元素
 *
 * @author Marticles
 * @description LRUCache2
 * @date 2019/4/15
 */
public class LRUCache2<K, V> {
    private int maxCacheSize;
    private LinkedList<K> linkedList;
    private HashMap<K, V> hashMap;

    public LRUCache2(int maxCacheSize) {
        this.maxCacheSize = maxCacheSize;
        this.linkedList = new LinkedList<>();
        this.hashMap = new HashMap<>();
    }

    public V get(K key) {
        if (hashMap.containsKey(key)) {
            // 删除队尾的元素
            linkedList.removeLastOccurrence(key);
            // 在队首加入元素
            linkedList.addFirst(key);
            return hashMap.get(key);
            // 如果key不存在返回null
        } else {
            return null;
        }
    }

    public void put(K key, V value) {
        // 如果达到了最大容量
        if (linkedList.size() == maxCacheSize) {
            // 删除链表队尾的元素
            // HashMap中也要删除对应元素
            hashMap.remove(linkedList.removeLast());
            // 加入新元素
            linkedList.addFirst(key);
            hashMap.put(key, value);
        // 如果没有达到最大容量
        } else {
            linkedList.addFirst(key);
            hashMap.put(key, value);
        }

    }

    @Override
    public String toString() {
        return "LRUCache2{" +
                "linkedList=" + linkedList +
                ", hashMap=" + hashMap +
                '}';
    }
}
