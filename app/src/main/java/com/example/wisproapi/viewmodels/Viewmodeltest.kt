package com.example.wisproapi.viewmodels

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.wisproapi.repositories.WisproRepository
import com.example.wisproapi.retrofit_models.JsonPayments
import com.example.wisproapi.retrofit_models.Payment
import com.example.wisproapi.retrofit_models.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class Viewmodeltest:ViewModel() {

    val request = ServiceBuilder.buildService(JsonPayments::class.java)
    val listCallV2: Call<Payment> = request.getPostsV2(2,100, "64dc19d7-1227-4741-9fe3-de3f476aa203")

    var wisproRepository = WisproRepository()
    var payment_object: MutableLiveData<Payment> = wisproRepository!!.getPayments()

}