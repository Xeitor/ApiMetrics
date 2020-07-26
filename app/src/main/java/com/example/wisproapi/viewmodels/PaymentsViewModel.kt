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

    //Making call with WisproRepository
    var payment: Observable<Payment?>? = wisproRepository.getPaymentsRx()
    var multiple_payments = wisproRepository.getMultiplePayments()
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
}