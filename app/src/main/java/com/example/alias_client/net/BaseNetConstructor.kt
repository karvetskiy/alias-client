package com.example.alias_client.net

import android.util.Log
import com.grapesnberries.curllogger.CurlLoggerInterceptor
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

class BaseNetConstructor {

    private val httpClient = OkHttpClient().newBuilder()
        .addInterceptor(HttpLoggingInterceptor { message -> Log.i( "MyAPP","interceptor : $message") }
            .setLevel(HttpLoggingInterceptor.Level.BODY))
        .addInterceptor(CurlLoggerInterceptor())

    fun <S> create(mainServetUrl: String, serviceClass: Class<S>):S{
        val retrofit = Retrofit.Builder()

        try{
            retrofit.baseUrl(mainServetUrl)
        } catch (err: IllegalArgumentException){
            err.printStackTrace()
            retrofit.baseUrl(mainServetUrl + "/")
        }

        return retrofit
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                httpClient.addInterceptor { chain ->
                    chain.proceed(chain.request().newBuilder()
                        .method(chain.request().method(), chain.request().body())
                        .headers(
                            Headers.of(mapOf(
                                "MyHeader" to "Hello"
                            )))
                        .build()
                        )}
                    .build()
            )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(serviceClass)
    }

}