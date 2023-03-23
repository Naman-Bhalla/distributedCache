package com.scaler.distributedcache.evictionstrategies;

public class EvictionStrategyFactory {
    public static IEvictionStrategy getEvictionStrategy(EvictionStrategy evictionStrategy) {
        switch (evictionStrategy) {
            case LRU:
                return new LruEvictionStrategy();
            case LFU:
                return new LfuEvictionStrategy();
            default:
                return null;
        }
    }
}
