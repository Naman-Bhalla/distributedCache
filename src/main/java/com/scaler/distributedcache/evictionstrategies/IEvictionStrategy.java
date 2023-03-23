package com.scaler.distributedcache.evictionstrategies;

import com.scaler.distributedcache.CacheOperation;

public interface IEvictionStrategy<K> {

    void notify(K key, CacheOperation operation);

    K evict();
}
