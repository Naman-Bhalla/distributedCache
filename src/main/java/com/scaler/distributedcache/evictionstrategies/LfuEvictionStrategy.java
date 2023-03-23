package com.scaler.distributedcache.evictionstrategies;

import com.scaler.distributedcache.CacheOperation;

public class LfuEvictionStrategy<K> implements IEvictionStrategy<K> {

    @Override
    public void notify(K key, CacheOperation operation) {

    }

    @Override
    public K evict() {
        return null;
    }
}
