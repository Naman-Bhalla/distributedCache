package com.scaler.distributedcache;

import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHashing {
    private final int numberOfReplicas;
    private final SortedMap<Integer, CacheNode> circle = new TreeMap<>();

    public ConsistentHashing(int numberOfReplicas, Collection<CacheNode> nodes) {
        this.numberOfReplicas = numberOfReplicas;
        for (CacheNode node : nodes) {
            add(node);
        }
    }

    public synchronized void add(CacheNode node) { // 1 -> "1" -> "10".hashCode() "11".hashCode() "12".hashCode()
        for (int i = 0; i < numberOfReplicas; i++) {
            circle.put((String.valueOf(node.getId()) + i).hashCode(), node);
        }
    }

    public synchronized void remove(CacheNode node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            circle.remove((String.valueOf(node.getId() + i)).hashCode());
        }
    }

    public CacheNode get(String key) {
        if (circle.isEmpty()) {
            return null;
        }
        int hash = key.hashCode();
        if (!circle.containsKey(hash)) {
            SortedMap<Integer, CacheNode> tailMap = circle.tailMap(hash);
            hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        }
        return circle.get(hash);
    }
}