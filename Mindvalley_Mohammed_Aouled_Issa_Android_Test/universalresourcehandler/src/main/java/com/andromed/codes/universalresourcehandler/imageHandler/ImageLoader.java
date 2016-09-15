package com.andromed.codes.universalresourcehandler.imageHandler;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;

import com.andromed.codes.universalresourcehandler.callbacks.CallbackInterface;
import com.andromed.codes.universalresourcehandler.resource.loader.ResourceLoader;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Utilisateur on 09/09/2016.
 */
public class ImageLoader extends ResourceLoader {


    /**
     * Use this method to load an image from a url and save into cache, saving into cache only
     * happens for the first time the application is loaded.
     * @param context specifies the calling activity context (used later to communicate back data
     *                to the main UI thread)
     * @param imageUrl specifies the url that contains the image to be downloaded
     * @param requestedWidth specifies the requested image width
     * @param requestedHeight specifies the requested image height
     * @param callbackInterface an interface used to communicate data back to the main UI thread
     *                          when the task is finished
     */
    public void loadImage(final Context context, @NonNull final String imageUrl, final int requestedWidth,
                          final int requestedHeight, final CallbackInterface callbackInterface) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Object result = loadScaledDownImage(imageUrl, requestedWidth, requestedHeight);
                ((Activity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callbackInterface.onSuccess(result);
                    }
                });
            }
        }).start();
    }

    /**
     * Use this method to load an image bundle (list of images) from a url and save them into cache,
     * saving into cache only happens for the first time the application is loaded
     * @param context specifies the calling activity context (used later to communicate back data
     *                to the main UI thread)
     * @param imageUrls specifies the list of urls of the images to be downloaded
     * @param requestedWidth specifies the requested images width
     * @param requestedHeight specifies the requested images height
     * @param callbackInterface an interface used to communicate data back to the main UI thread
     *                          when the task is finished. The call back interface is also used to
     *                          communicate back results whenever an image is downloaded and saved
     *                          into the memory cache.
     */
    public void loadImageBundle(final Context context, @NonNull final ArrayList<String> imageUrls, final int requestedWidth,
                          final int requestedHeight, final CallbackInterface callbackInterface) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final ArrayList<Bitmap> imageBundle = new ArrayList<>();
                for (String url :
                        imageUrls) {
                    final Bitmap result = loadScaledDownImage(url, requestedWidth, requestedHeight);
                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            callbackInterface.onSuccess(result);
                        }
                    });
                    imageBundle.add(result);
                }
            }
        }).start();
    }

    /**
     * use this function to detect weather a scaled down version of the image or the actual image
     * version should be downloaded based on both requested image width and height.
     * @param imageUrl specifies the url that contains the image to be downloaded
     * @param reqWidth specifies the requested image width
     * @param reqHeight specifies the requested image height
     * @return the bitmap of the image loaded from cache or downloaded from a Url
     */
    protected Bitmap loadScaledDownImage(String imageUrl, int reqWidth, int reqHeight) {
        if (loadFromCache(imageUrl) != null) {
            return (Bitmap) loadFromCache(imageUrl);
        } else {
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeStream(super.getResourceDataFromUrl(imageUrl),
                        null, options);
                options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
                options.inJustDecodeBounds = false;

                Bitmap mImage = BitmapFactory.decodeStream(super.getResourceDataFromUrl(imageUrl),
                        null, options);
                loadInCache(imageUrl, mImage);
                return mImage;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Use this function to calculate the inSampleSize of the resulted image based on the given
     * requested image width and height
     * @param options Bitmap option to get the real image width and height
     * @param reqWidth specifies the image requested width
     * @param reqHeight specifies the image requested height
     * @return specifies the InSampleSize
     */
    protected int calculateInSampleSize(BitmapFactory.Options options,
                                     int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
}
