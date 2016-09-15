package com.andromed.codes.universalresourcehandler.resource.loader;

import android.util.LruCache;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Utilisateur on 09/09/2016.
 */
public abstract class BaseResourceLoader {


    /**
     * Use this method to load data into the memory cache
     * @param resourceKey the key of the resource being loaded in cache
     * @param resource the resource being loaded in cache
     */
    protected abstract void loadInCache(String resourceKey, Object resource);

    /**
     * Use this function to load data from memory cache.
     * @param resourceKey the key of the resource being loaded
     * @return the resource loaded from the cache (if it exists)
     */
    protected abstract Object loadFromCache(String resourceKey);

    /**
     * Use this function to load the resource from a given url as an InputStream object
     * @param resourceUrl specifies the Url of the resource
     * @return the InputStream from the Url
     * @throws IOException thrown exception
     */
    protected abstract InputStream getResourceDataFromUrl(String resourceUrl) throws IOException;

}
