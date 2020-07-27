package com.example.wisproapi.retrofit_models

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query
import java.util.*
import io.reactivex.Observable

interface JsonPayments {
    @GET("/api/v1/invoicing/payments")
    fun getPostsV2(@Query("payment_date_before") payment_date_before: String,@Query("payment_date_after") payment_date_after: String, @Query("page") page: Int, @Query("per_page") per_page: Int, @Header("Authorization") authorization: String): Call<Payment>

    @GET("/api/v1/invoicing/payments")
    fun getPostsRx(@Query("page") page: Int, @Query("per_page") per_page: Int, @Header("Authorization") authorization: String): Observable<Payment>

    @GET("/api/v1/invoicing/payments")
    fun getmontlyPaymentsRx(@Query("payment_date_before") payment_date_before: String,@Query("payment_date_after") payment_date_after: String, @Query("page") page: Int, @Query("per_page") per_page: Int, @Header("Authorization") authorization: String): Observable<Payment>
}