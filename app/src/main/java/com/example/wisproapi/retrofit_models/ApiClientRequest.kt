package com.example.wisproapi.retrofit_models

import retrofit2.Call
import retrofit2.http.*

interface ApiClientRequest {
    @POST("api/v1/auth/sign_in")
    @FormUrlEncoded
    fun authenticate(@Field("email") email: String, @Field("password") password: String): Call<ClientAccount>
}