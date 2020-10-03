package com.example.wisproapi.repositories

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.wisproapi.retrofit_models.ApiClientRequest
import com.example.wisproapi.retrofit_models.ClientAccount
import com.example.wisproapi.retrofit_models.ResponseStatus
import com.example.wisproapi.retrofit_models.ServiceBuilder
import okhttp3.Headers
import okhttp3.internal.http2.Header
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AccountRepository(context: Context) {

    val request = ServiceBuilder.buildServiceApiClient(ApiClientRequest::class.java)
    val editor = context?.getSharedPreferences("client_information", MODE_PRIVATE).edit()


    //Login method
    fun authenticateUser(): MutableLiveData<ResponseStatus> {

        var data = MutableLiveData<ClientAccount>()
        var responseStatusLive = MutableLiveData<ResponseStatus>()
        var responseStatusHelper = ResponseStatus()
        val call: Call<ClientAccount> = request.authenticate("testing@gmail.com", "123456")

        call.clone().enqueue(object : Callback<ClientAccount> {
            override fun onResponse(call: Call<ClientAccount>, response: Response<ClientAccount>) {
                if (response.code() == 200) {
                    updateAccount(response)
                    responseStatusHelper.status = true
                    responseStatusLive.value = responseStatusHelper
                } else {
                    responseStatusHelper.status = false
                    responseStatusLive.value = responseStatusHelper
                }
            }
            override fun onFailure(call: Call<ClientAccount>, t: Throwable) {
                // No internet error
            }
        })
        return responseStatusLive
    }

    fun validateToken() {

    }

    fun updateAccount(response: Response<ClientAccount>) {
        var clientAccount = response.body()

        editor?.putString("email", clientAccount!!.data?.email)
        editor?.putString("id", clientAccount!!.data?.id.toString())
        editor?.putString("uid", clientAccount!!.data?.uid)
        editor?.putString("access_token", response.headers().get("Access-Token").toString())
        editor?.putString("client", response.headers()["Client:"])
        editor?.apply()
    }
}
