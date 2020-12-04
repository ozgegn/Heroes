package com.ozgegn.heroes.data.remote

import android.content.Context
import android.graphics.Bitmap
import android.util.LruCache
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.Volley

class HeroesSingleton constructor(context: Context) {

    val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }

    val imageLoader: ImageLoader by lazy {
        ImageLoader(requestQueue, object : ImageLoader.ImageCache {
            private val cache = LruCache<String, Bitmap>(20)

            override fun getBitmap(url: String?): Bitmap = cache.get(url)

            override fun putBitmap(url: String?, bitmap: Bitmap?) {
                cache.put(url, bitmap)
            }

        })
    }

    fun <T> addToRequestQueue(req: Request<T>) {
        requestQueue.add(req)
    }

    companion object {

        @Volatile
        private var mInstance: HeroesSingleton? = null
        fun getInstance(context: Context) = mInstance ?: synchronized(this) {
            mInstance ?: HeroesSingleton(context).also {
                mInstance = it
            }
        }

    }

}