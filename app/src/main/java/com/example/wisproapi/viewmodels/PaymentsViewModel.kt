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
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class PaymentsViewModel:ViewModel() {

    //Requests
    val request = ServiceBuilder.buildService(JsonPayments::class.java)
    val requestrx = ServiceBuilder.buildServiceRx(JsonPayments::class.java)
    val listCallV2: Call<Payment> = request.getPostsV2(2,100, "64dc19d7-1227-4741-9fe3-de3f476aa203")
    val listCallV1: Call<Payment> = request.getPostsV2(1,100, "64dc19d7-1227-4741-9fe3-de3f476aa203")
    val listCallRx: Observable<Payment> = requestrx.getPostsRx(1,100, "64dc19d7-1227-4741-9fe3-de3f476aa203")
    val listCallRx2: Observable<Payment> = requestrx.getPostsRx(2,100, "64dc19d7-1227-4741-9fe3-de3f476aa203")
    val montlycallRx: Observable<Payment> = requestrx.getmontlyPaymentsRx("2020-06-01T00:00:00.000-03:00",1,100, "9d168f07-2c58-493d-9d98-55baf59d6f6b")
    val calls: MutableList<Observable<Payment>> = ArrayList()
    private val monthly_payments = ArrayList<Payment>()
    private val live_monthly_payments = MutableLiveData<ArrayList<Payment>>()

    var wisproRepository = WisproRepository()
    var payment_object: MutableLiveData<Payment> = wisproRepository.getPayments(listCallV2)
    var payments_rx: Observable<Payment?>? = wisproRepository.getPaymentsRx(listCallRx)
    var multiple_payments: Observable<Payment>? = null
    var real_montly_payments: Observable<Payment?>? = wisproRepository.getMontlyPaymentsRx(listCallRx)

    init {
        //Add requests to list
        calls.add(listCallRx)
        calls.add(listCallRx2)

        multiple_payments = wisproRepository.getMultiplePayments(calls)
    }
}