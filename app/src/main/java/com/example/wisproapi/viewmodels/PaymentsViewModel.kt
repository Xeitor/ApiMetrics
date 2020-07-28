package com.example.wisproapi.viewmodels

import android.annotation.SuppressLint
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.wisproapi.repositories.WisproRepository
import com.example.wisproapi.retrofit_models.JsonPayments
import com.example.wisproapi.retrofit_models.Payment
import com.example.wisproapi.retrofit_models.PaymentHandler
import com.example.wisproapi.retrofit_models.ServiceBuilder
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class PaymentsViewModel:ViewModel() {
    var wisproRepository = WisproRepository()
    val request = ServiceBuilder.buildService(JsonPayments::class.java)
    val requestrx = ServiceBuilder.buildServiceRx(JsonPayments::class.java)


    //Making call with WisproRepository
    var payment: Observable<Payment?>? = wisproRepository.getPaymentsRx()
    var multiple_payments = wisproRepository.getMultiplePayments()
    var multiple_payments_refresh = wisproRepository.getMultiplePaymentsRefresh()
    var multiple_paymentsV2 = wisproRepository.getPaymentsRxV2(requestrx.getmontlyPaymentsRx("2020-03-01T00:00:00.000-03:00","2020-03-01T00:00:00.000-03:00",2,100, "9d168f07-2c58-493d-9d98-55baf59d6f6b"))

    companion object {
        val livePayment: MutableLiveData<PaymentHandler> by lazy {
            MutableLiveData<PaymentHandler>()
        }
    }
    @SuppressLint("CheckResult")
    fun get_live_payment(): MutableLiveData<PaymentHandler> {
        val payment_handler = PaymentHandler()
        multiple_payments.subscribe({
            //Onnext
            payment_handler.addPayments(it?.data!!)
        }, {
            //Onerror
        },{
            //Oncompleted
            livePayment.value = payment_handler
        })
        return livePayment
    }
    @SuppressLint("CheckResult")
    fun get_refresh(): MutableLiveData<PaymentHandler> {
        val payment_handler = PaymentHandler()
        multiple_payments_refresh.subscribe({
            //Onnext
            payment_handler.addPayments(it?.data!!)
        }, {
            //Onerror
        },{
            //Oncompleted
            livePayment.value = payment_handler
        })
        return livePayment
    }
}