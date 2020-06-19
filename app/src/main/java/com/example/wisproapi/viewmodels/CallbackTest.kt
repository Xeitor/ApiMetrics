package com.example.wisproapi.viewmodels

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CallbackTest<T>: Callback<T> {
    override fun onFailure(call: Call<T>?, t: Throwable?) {
        TODO("Not yet implemented")
    }

    override fun onResponse(call: Call<T>?, response: Response<T>?) {
        TODO("Not yet implemented")
    }
}