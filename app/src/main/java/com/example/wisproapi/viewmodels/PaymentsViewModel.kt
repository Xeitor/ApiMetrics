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

    var wisproRepository = WisproRepository()
    var payment_object: MutableLiveData<Payment> = wisproRepository.getPayments()
    var payments_rx: Observable<Payment?>? = wisproRepository.getPaymentsRx()
    var montly_payments: Observable<Payment> = wisproRepository.getmontlhyPayments()
    var real_montly_payments: Observable<Payment?>? = wisproRepository.getMontlyPaymentsRx()
}