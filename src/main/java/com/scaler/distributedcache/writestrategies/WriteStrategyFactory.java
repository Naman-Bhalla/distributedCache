package com.scaler.distributedcache.writestrategies;

public class WriteStrategyFactory {
    public static IWriteStrategy getWriteStrategy(WriteStrategy writeStrategy) {
        if (writeStrategy.equals(WriteStrategy.WRITE_THROUGH)) {
            return new WriteThroughCacheWriteStrategy();
        } else if (writeStrategy.equals(WriteStrategy.WRITE_BACK)) {
            return new WriteBackCacheWriteStrategy();
        }
        return null;
    }
}

