package com.example.wisproapi.repositories

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.wisproapi.retrofit_models.ApiClientRequest
import com.example.wisproapi.retrofit_models.ClientAccount
import com.example.wisproapi.retrofit_models.ServiceBuilder
import okhttp3.Headers
import okhttp3.internal.http2.Header
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AccountRepository(context: Context) {

    val request = ServiceBuilder.buildServiceApiClient(ApiClientRequest::class.java)
    val editor = context?.getSharedPreferences("client_information", MODE_PRIVATE).edit()

    fun authenticateUser(): MutableLiveData<ClientAccount> {

        var data = MutableLiveData<ClientAccount>()
        val call: Call<ClientAccount> = request.authenticate("testing@gmail.com", "123456")

        call.clone().enqueue(object : Callback<ClientAccount> {
            override fun onResponse(call: Call<ClientAccount>, response: Response<ClientAccount>) {
                data.value = response.body()
                val access_token = response.headers().get("Access-Token:")
                val client = response.headers()["Client:"]
                editor?.putString("email", data.value?.data?.email)
                editor?.putString("id", data.value?.data?.id.toString())
                editor?.putString("uid", data.value?.data?.uid)
                editor?.putString("access_token", response.headers().get("Access-Token").toString())
                editor?.putString("client", client)
                editor?.apply()
                Log.d(TAG, "LOG")
                Log.d(TAG, response.headers().toString())
                Log.d(TAG, "testing")
                Log.d(TAG,response.headers().name(0))
                Log.d(TAG, "testing 2")
                Log.d(TAG, response.headers().get("Access-Token").toString())
            }
            override fun onFailure(call: Call<ClientAccount>, t: Throwable) {
                // No internet error
            }
        })
        return data
    }
}
