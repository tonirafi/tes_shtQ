package com.tes.tesshtq.utils

import android.content.Context
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

import java.util.concurrent.TimeUnit


class RestAdapter(private val context: Context) {


    fun callApi(): RestApi? {
        val retrofit: Retrofit
        try {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val gson = GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create()

            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(10, TimeUnit.MINUTES)
                .readTimeout(10, TimeUnit.MINUTES)
                .connectTimeout(REQ_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(REQ_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(REQ_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()

            return retrofit.create(RestApi::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

    }


    fun callApi(baseUrl: String): RestApi? {

        val retrofit: Retrofit
        try {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val gson = GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create()

            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .connectTimeout(REQ_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(REQ_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(REQ_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
            return retrofit.create(RestApi::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

    }

    companion object {
        private val REQ_TIMEOUT = 90
    }
}



