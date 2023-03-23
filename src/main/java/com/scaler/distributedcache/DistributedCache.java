package com.scaler.distributedcache;

import com.scaler.distributedcache.database.DatabaseAdapter;
import com.scaler.distributedcache.evictionstrategies.EvictionStrategy;
import com.scaler.distributedcache.evictionstrategies.EvictionStrategyFactory;
import com.scaler.distributedcache.evictionstrategies.IEvictionStrategy;
import com.scaler.distributedcache.writestrategies.IWriteStrategy;
import com.scaler.distributedcache.writestrategies.WriteStrategy;
import com.scaler.distributedcache.writestrategies.WriteStrategyFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class DistributedCache<K, V> implements Cache<K, V> {
    private int numberOfNodes;
    private int numberOfReplicas;
    private int prefetchSize;
    private IWriteStrategy writeStrategy;
    private IEvictionStrategy evictionStrategy;
    private DatabaseAdapter<K, V> database;
    private ConsistentHashing consistentHashing;
    private List<CacheNode> cacheNodes;

    public DistributedCache(EvictionStrategy evictionStrategy,
                            WriteStrategy writeStrategy,
                            int numberOfNodes,
                            int numberOfReplicas,
                            int prefetchSize,
                            DatabaseAdapter<K, V> database) {
        this.numberOfNodes = numberOfNodes;
        this.numberOfReplicas = numberOfReplicas;
        this.prefetchSize = prefetchSize;
        this.writeStrategy = WriteStrategyFactory.getWriteStrategy(writeStrategy);
        this.evictionStrategy = EvictionStrategyFactory.getEvictionStrategy(evictionStrategy);
        this.database = database;
        List<CacheNode> cacheNodes = new ArrayList<>();
        for (int i = 0; i < numberOfNodes; ++i) {
            cacheNodes.add(new CacheNode(this.evictionStrategy));
        }
        this.consistentHashing = new ConsistentHashing(numberOfReplicas, cacheNodes);
    }

    @Override
    public Future<V> get(K key) {
        return null;
    }

    @Override
    public Future<Void> put(K key, V value) {
        return null;
    }

    @Override
    public Future<Void> remove(K key) {
        CacheNode cacheNode = consistentHashing.get(String.valueOf(key));

        if (cacheNode != null) {
            return cacheNode.remove(key);
        }

        return CompletableFuture.completedFuture(null);
    }
}
