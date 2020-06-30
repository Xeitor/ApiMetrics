package com.example.wisproapi.repositories

import androidx.lifecycle.MutableLiveData
import com.example.wisproapi.retrofit_models.JsonPayments
import com.example.wisproapi.retrofit_models.Payment
import com.example.wisproapi.retrofit_models.ServiceBuilder
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WisproRepository {

    // Makes one call returns observer
    fun getPaymentsRx(call: Observable<Payment>): Observable<Payment?>? {
        return call.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getMontlyPaymentsRx(call: Observable<Payment>): Observable<Payment?>? {
        return call.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }

    // Merge multiple calls into one observer
    fun getMultiplePayments(call_list: MutableList<Observable<Payment>>): Observable<Payment> {
        return Observable.merge(call_list).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
    }


    //Livedata method
    fun getPayments(call: Call<Payment>): MutableLiveData<Payment> {
        var data = MutableLiveData<Payment>()
        call.clone().enqueue(object : Callback<Payment> {
            override fun onResponse(call: Call<Payment>, response: Response<Payment>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<Payment>, t: Throwable) {
                TODO()
            }
        })
        return data
    }
}
