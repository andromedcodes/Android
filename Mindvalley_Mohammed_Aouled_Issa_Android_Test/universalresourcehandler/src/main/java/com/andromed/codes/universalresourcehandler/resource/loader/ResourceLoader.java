package com.andromed.codes.universalresourcehandler.resource.loader;

import com.andromed.codes.universalresourcehandler.cache.MemoryCache;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Utilisateur on 09/09/2016.
 */
public class ResourceLoader extends BaseResourceLoader {


    /**
     * Use this method to load data into the memory cache
     * @param resourceKey the key of the resource being loaded in cache
     * @param resource the resource being loaded in cache
     */
    @Override
    protected void loadInCache(String resourceKey, Object resource) {
        if (MemoryCache.getInstance().getLruCache().get(resourceKey) == null) {
            MemoryCache.getInstance().getLruCache().put(resourceKey, resource);
        }
    }

    /**
     * Use this function to load data from memory cache.
     * @param resourceKey the key of the resource being loaded
     * @return the resource loaded from the cache (if it exists)
     */
    @Override
    protected Object loadFromCache(String resourceKey) {
        return MemoryCache.getInstance().getLruCache().get(resourceKey);
    }

    /**
     * Use this function to load the resource from a given url as an InputStream object
     * @param resourceUrl specifies the Url of the resource
     * @return the InputStream from the Url
     * @throws IOException thrown exception
     */
    @Override
    protected InputStream getResourceDataFromUrl(String resourceUrl) throws IOException {
        URL mUrl = new URL(resourceUrl);
        HttpURLConnection mConnection = (HttpURLConnection) mUrl.openConnection();
        mConnection.setDoInput(true);
        mConnection.connect();

        return mConnection.getInputStream();
    }

}
