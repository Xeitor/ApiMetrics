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
    private val wispro = Retrofit.Builder()
        .baseUrl("https://www.cloud.wispro.co")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val wisproRx = Retrofit.Builder()
        .baseUrl("https://www.cloud.wispro.co")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

    val apiClient = Retrofit.Builder()
        .baseUrl("https://enigmatic-temple-77864.herokuapp.com")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun<T> buildService(service: Class<T>): T {
        return wispro.create(service)
    }

    fun<T> buildServiceApiClient(service: Class<T>): T {
        return apiClient.create(service)
    }


    fun<T> buildServiceRx(service: Class<T>): T {
        return wisproRx.build().create(service)
    }
}