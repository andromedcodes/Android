package com.andromed.codes.universalresourcehandler.cache;

import android.util.LruCache;

/**
 * Created by Utilisateur on 09/09/2016.
 */
public class MemoryCache {

    public static MemoryCache instance;
    private LruCache<String, Object> mLruCache;
    private final int maxResourceSize = (int) Runtime.getRuntime().maxMemory() / 1024;
    private final int cacheSize = maxResourceSize / 10;

    /**
     * Constructor of the memory Cache
     */
    public MemoryCache() {
        mLruCache = new LruCache<String, Object>(cacheSize) {
            @Override
            protected int sizeOf(String key, Object value) {
                return super.sizeOf(key, value) / 10;
            }
        };
    }

    /**
     * Use this method when ever the instance of the memory cache is needed.
     * @return instance of the existing memory cache
     */
    public static MemoryCache getInstance() {
        if (instance == null) {
            instance = new MemoryCache();
        }
        return instance;
    }

    /**
     * Use this method to get the Cache Object
     * @return Cache Object
     */
    public LruCache<String, Object> getLruCache() {
        return mLruCache;
    }
}
