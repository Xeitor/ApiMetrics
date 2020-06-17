package com.example.wisproapi.retrofit_models

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface JsonPayments {
    @GET("/api/v1/invoicing/payments")
    fun getPostsV2(@Query("page") page: Int, @Query("per_page") per_page: Int, @Header("Authorization") authorization: String): Call<Payment>
}