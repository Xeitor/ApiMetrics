package com.example.wisproapi.repositories

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import com.example.wisproapi.helpers.CustomDate
import com.example.wisproapi.retrofit_models.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AccountRepository() {

    val request = ServiceBuilder.buildServiceApiClient(ApiClientRequest::class.java)

    fun authenticateUser(): MutableLiveData<ClientAccount> {
        var data = MutableLiveData<ClientAccount>()
        val call: Call<ClientAccount> = request.authenticate("testing@gmail.com", "1234566")
        call.clone().enqueue(object : Callback<ClientAccount> {
            override fun onResponse(call: Call<ClientAccount>, response: Response<ClientAccount>) {
                data.value = response.body()
            }
            override fun onFailure(call: Call<ClientAccount>, t: Throwable) {

            }
        })
        return data
    }
}
