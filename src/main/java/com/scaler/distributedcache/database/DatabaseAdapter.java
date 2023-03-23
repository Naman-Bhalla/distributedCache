package com.scaler.distributedcache.database;

import java.util.concurrent.Future;

public interface DatabaseAdapter<K, V> {
    Future<Void> put(K key, V value);

    Future<V> get(K key);

    Future<Void> remove(K key);
}
