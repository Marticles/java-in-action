package com.marticles;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 基于LinkedHashMap实现LRU Cache
 *
 * @author Marticles
 * @description LRUCache
 * @date 2019/4/15
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private int maxCacheSize;

    public LRUCache(int maxCacheSize) {
        // 当accessOrder为true时表示为使用的顺序，false为插入顺序
        super(16, 0.75f, true);
        this.maxCacheSize = maxCacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        // 当达到最大容量时删除最近最久未使用的元素
        return size() > maxCacheSize;
    }
}
