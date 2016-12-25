package com.andromed.codes.universalresourcehandler.parsers;

import android.util.Log;

import com.andromed.codes.universalresourcehandler.resource.loader.ResourceLoader;

import org.apache.commons.io.IOUtils;

import java.io.IOException;

/**
 * Created by Utilisateur on 09/09/2016.
 */

public abstract class BaseResourceParser extends ResourceLoader {

    /**
     * Use this function to get any resource data from a specified url after loading it to cache.
     * this function get the resource data as an InputStream Object and the load it into cache if
     * the cache does not contain the data.
     * @param resourceUrl specifies the Url that contains the resource data.
     * @return the resource data from the cache.
     */
    public String loadResources(String resourceUrl) {
        try {
            String data;
            if (loadFromCache(resourceUrl) != null) {
                data = (String) loadFromCache(resourceUrl);
            } else {
                data = IOUtils.toString(getResourceDataFromUrl(resourceUrl), "UTF-8");
                loadInCache(resourceUrl, data);
            }
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Use this function to get the data from the Json String if you want only to address a child
     * node from inside a full Json data
     * @param args function args.
     * @return result as an object
     */
    protected abstract Object getDataFromJson(Object... args);

    /**
     * Use this function to get the data from the Json String if you want to map a json resource
     * data to a java resource object using the provided scheme
     * @param args function args.
     * @return result as an object
     */
    protected abstract Object getFullDataFromJson(Object... args);
}
