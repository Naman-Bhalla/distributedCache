package com.scaler.distributedcache;

import com.scaler.distributedcache.evictionstrategies.IEvictionStrategy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class CacheNode<K, V> {
    private int id;
    private Map<K, V> cache;
    private IEvictionStrategy evictionStrategy;

    public CacheNode(IEvictionStrategy evictionStrategy) {
        cache = new HashMap<>();
        this.evictionStrategy = evictionStrategy;
    }

    public int getId() {
        return id;
    }


    public Future<V> get(K key) {
        return null;
    }

    public Future<Void> put(K key, V value) {
        return null;
    }

    public Future<Void> remove(K key) {
        cache.remove(key);
        evictionStrategy.notify(key, CacheOperation.DELETE);
        return CompletableFuture.completedFuture(null);
    }
}
