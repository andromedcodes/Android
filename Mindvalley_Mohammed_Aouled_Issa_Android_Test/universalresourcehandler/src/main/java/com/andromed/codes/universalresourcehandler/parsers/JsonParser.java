package com.andromed.codes.universalresourcehandler.parsers;

import com.andromed.codes.universalresourcehandler.callbacks.CallbackInterface;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Utilisateur on 09/09/2016.
 */
public class JsonParser extends BaseResourceParser {

    Object res;

    /**
     * Use this method to specify the desired task to execute.
     * @param task task to be executed : (getDataFromJson or getFullDataFromJson).
     */
    public void prepare(Runnable task) {
        task.run();
    }

    /**
     * Use this function to get the data from the Json String if you want only to address a child
     * node from inside a full Json Url.
     * @param url
     * @param scheme
     * @param callback
     */
    public void getDataFromJson(final String url, final Class scheme, final CallbackInterface callback) {
        Thread loader = new Thread(new Runnable() {
            @Override
            public void run() {
                res = getDataFromJson(loadResources(url), scheme);
                callback.onSuccess(res);
            }
        });
        loader.start();
    }

    /**
     * Use this function to get the data from the Json String if you want to map a json resource
     * data to a java resource object using the provided scheme(Model)
     * @param url
     * @param scheme
     * @param callback
     */
    public void getFullDataFromJson(final String url, final Class scheme,
                                       final CallbackInterface callback) {
        Thread loader = new Thread(new Runnable() {
            @Override
            public void run() {
                res = getFullDataFromJson(loadResources(url), scheme);
                callback.onSuccess(res);
            }
        });
        loader.start();
    }

    static <T> ArrayList<T> listOf(Class<T> clazz) {
        return new ArrayList<T>();
    }

    @Override
    protected Object getDataFromJson(Object... args) {
        ArrayList<?> list;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        String data = ((String) args[0]);
        Class scheme = ((Class) args[1]);

        list = listOf(scheme);
        try {
            JsonNode root = objectMapper.readTree(data);
            ArrayList<Object> array = new ArrayList<>();
            for (JsonNode node :
                    root) {
                JsonNode child = node.path(scheme.getSimpleName().toLowerCase());
                String childJsonText = child.toString();
                array.add(objectMapper.readValue(childJsonText, scheme));
            }
            list = array;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    protected Object getFullDataFromJson(Object... args) {
        ArrayList<?> list;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        String data = ((String) args[0]);
        Class scheme = ((Class) args[1]);

        list = listOf(scheme);
        try {
            list = objectMapper.readValue(data,
                    objectMapper.getTypeFactory().constructCollectionType(
                            ArrayList.class, scheme)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
