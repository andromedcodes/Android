# README #

**Universal Resource Handler** is a general purpose library based on **Java & Android API** for downloading any kind of resource (Images, Json data, Xml etc...) from a provided URL. **Universal Resource Handler** downloading and caching any given resource is  *asynchronously* processed in a separate background thread to make sure the UI main thread is not frozen or blocked by any mean for a better UX and code design.

Thanks to it's memory caching system, saving or loading from the memory cache is never been easier. All resources are downloaded from a URL (or CDN) and then cached in the memory for any instant later access. The memory caching system is based on the LRU (Last Recently Used) algorithm, which is a powerful caching algorithm that automatically evicts resources being held in the cache and not used for a certain amount of time.

## What is this repository for? ##

* Universal Resource Handle Java Library & Android demo APK.
* Version 0.9 (preview release)
* [Mohammed Aouled Issa](https://bitbucket.org/andromedcodes/)

## How do I get set up? ##

### Step One : Get the Library ###

* Download the library and the demo source code from this link : (https://andromedcodes@bitbucket.org/andromedcodes/mindvalley_mo_aouledissa_android_test.git) to get started.

### Step Two : Library Integration ###

* Dependencies
    * Make sure you comple the library as a project in your build.gradle file as follows: 
```
#!java

dependencies {
    compile project (':universalresourcehandler')
}
```

### Step Three : Parse Json Data ###

* **Universal Resource Handler** library has a built-in **Json** parser based on **Jasckson Java Library** & **Annotations**. To parse a Json resource make sure you instantiate the **JsonParser** class in your main thread as follows:

```
#!java
JsonParser mJsonParser = new JsonParser();
```

* **Before** starting any Json parsing process, make sure you build the correct **Data Model** (Object Scheme) to match your **Root** Json Array or at least one **Child Node** Json object. You can use this ["Amazing" online Tool](http://www.jsonschema2pojo.org/) to create a **POJO** from your Json Array (please make sure you select Json as Source Type and Jackson 2.X as Annotation Style.

   * **Example** : Given the following Json Array:
       
```
#!json
{
  "type":"object"
}

```

Java **Data Model** is:

```
#!java
package com.example;

....

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
"type"
})
public class Example {

   @JsonProperty("type")
   private String type;
   @JsonIgnore
   private Map<String, Object> additionalProperties = new HashMap<String, Object>();

   @JsonProperty("type")
   public String getType() {
      return type;
   }

   @JsonProperty("type")
   public void setType(String type) {
      this.type = type;
   }

   @JsonAnyGetter
   public Map<String, Object> getAdditionalProperties() {
      return this.additionalProperties;
   }

   @JsonAnySetter
   public void setAdditionalProperty(String name, Object value) {
      this.additionalProperties.put(name, value);
   }

}
```



* **JsonParser** object is task driven thread. All task are posted to the Thread through the **Prepare** method that takes a **Runnable** object as a parameter which specifies the desired task.


```
#!java
    public void prepare(Runnable task) {
        task.run();
    }
```

* To parse all the Json resource (**Root**) please use the **getFullDataFromJson** which takes three parameters:
    /**
     * Use this function to get the data from the Json String if you want to map a json resource
     * data to a java resource object using the provided scheme(Model)
     * param/ url : **Json resource Url**
     * param/ scheme : **Data Model (scheme)**
     * param/ callback : a **CallbackInterface** to handle data in both success or failure scenarios.
     */ 

```
#!java

jsonParser.getFullDataFromJson("http://something.exp/JsonData", RootObject.class,
                        new CallbackInterface() {
                            @Override
                            public void onSuccess(Object result) {
                                // Do something
                            }

                            @Override
                            public void onFailure() {
                                //Do something
                            }
});
```

* To parse only a node inside a Json resource (**Child Node**) please use the **getDataFromJson** which takes three parameters:
    /**
     * Use this function to get the data from the Json String if you want to map a json resource
     * data to a java resource object using the provided scheme(Model)
     * param/ url : **Json resource Url**
     * param/ scheme : **Data Model (scheme)**
     * param/ callback : a **CallbackInterface** to handle data in both success or failure scenarios.
     */ 

   * **Example:**
```
#!java

jsonParser.getDataFromJson("http://something.exp/JsonData", ChildObject.class,
                        new CallbackInterface() {
                            @Override
                            public void onSuccess(Object result) {
                                // Do something
                            }

                            @Override
                            public void onFailure() {
                                //Do something
                            }
});
```

**Please note** that **Universal Resource Handler** download the Json data and stores it **Automatically** in the memory cache for any later use.


### Download Images ###
* **Universal Resource Downloader** has a built-in Asynchronous image/image list automatic downloading and caching feature .
   * **Get Started**
To load any image resource (single image or a list of images) please use the **ImageLoader** Object and make sure you instantiate it in your main UI thread as follows.

```
#!java

ImageLoader mImageLoader = new ImageLoader();
```

   * **Download & Cache a Single Image from Url:** to load an image from a given Url please use this method which create a new thread for the download and caching process. Thanks to the callback interfaces methods you can communicate back the result from the background thread to the main thread in both success and failure scenarios.


```
#!java

loadImage(final Context context, @NonNull final String imageUrl, final int requestedWidth,
                          final int requestedHeight, final CallbackInterface callbackInterface) {}
```
* Example

```
#!java

mImageLoader.loadImage(ImageListActivity.this, url, 100, 100, new CallbackInterface() {
                                    @Override
                                    public void onSuccess(Object result) {
                                        //Do something
                                    }
                                    @Override
                                    public void onFailure() {
                                        //Do something
                                    }
                                });
```
* **Download & Cache a list of images from Url/multiple url:** to load a list image from a given Url please use this method which create a new thread for the download and caching process. Thanks to the callback interfaces methods you can communicate back the result from the background thread to the main thread in both success and failure scenarios.


```
#!java

loadImageBundle(final Context context, @NonNull final ArrayList<String> imageUrls, final int requestedWidth,
                          final int requestedHeight, final CallbackInterface callbackInterface){}
```
* Example:


```
#!java

mImageLoader.loadImageBundle(ImageListActivity.this, urlss, 100, 100, new CallbackInterface() {
                                    @Override
                                    public void onSuccess(Object result) {
                                        //Do something
                                    }
                                    @Override
                                    public void onFailure() {
                                        //Do something
                                    }
                                });
```
