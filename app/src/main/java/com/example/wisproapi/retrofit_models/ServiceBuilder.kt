package com.example.wisproapi.retrofit_models

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {

    private val client = OkHttpClient.Builder().build()
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.cloud.wispro.co")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val retrofitRx = Retrofit.Builder()
        .baseUrl("https://www.cloud.wispro.co")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

    fun<T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }

    fun<T> buildServiceRx(service: Class<T>): T {
        return retrofitRx.build().create(service)
    }
}