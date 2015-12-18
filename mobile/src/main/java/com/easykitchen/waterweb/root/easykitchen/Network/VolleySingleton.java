package com.easykitchen.waterweb.root.easykitchen.Network;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.easykitchen.waterweb.root.easykitchen.MyApplication;

/**
 * Created by root on 4/17/15.
 */
public class VolleySingleton {

    private static VolleySingleton sInstance = null;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private VolleySingleton(){
        //Running volley networking on a thread
        Thread t1 = new Thread(new ThreadOne());
        t1.start();
    }

    public static VolleySingleton getsInstance(){
        if(sInstance == null){// This is true if an instance does not exist
            sInstance = new VolleySingleton();// This is know as lazy instantiation
        }
        return sInstance;
    }

    public RequestQueue getmRequestQueue(){
        return mRequestQueue;
    }

    public ImageLoader getImageLoader(){
        return mImageLoader;
    }

    // Thread for volley networking
    private class ThreadOne implements Runnable{

        @Override
        public void run() {
            mRequestQueue = Volley.newRequestQueue(MyApplication.getAppContext());
            VolleySingleton.this.mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {

                private LruCache<String, Bitmap> cache = new LruCache<>((int) (Runtime.getRuntime().maxMemory()/1024/8));
                @Override
                public Bitmap getBitmap(String url) {
                    return cache.get(url);
                }

                @Override
                public void putBitmap(String url, Bitmap bitmap) {
                    cache.put(url, bitmap);
                }
            });
        }
    }
}
