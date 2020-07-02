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

    val request = ServiceBuilder.buildService(JsonPayments::class.java)
    val requestrx = ServiceBuilder.buildServiceRx(JsonPayments::class.java)

    //Return payments observable, receives calls
    fun getPaymentsRx(): Observable<Payment?>? {
        val montlycallRx: Observable<Payment> = requestrx.getmontlyPaymentsRx("2020-03-01T00:00:00.000-03:00",2,100, "9d168f07-2c58-493d-9d98-55baf59d6f6b")
        return montlycallRx.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }

    //Implemenatation with livedata
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

    //Implemenatation with list of calls
    fun getMultiplePayments(): Observable<Payment> {
        var list_call: MutableList<Observable<Payment>> = ArrayList()

        list_call.add(requestrx.getmontlyPaymentsRx("2020-03-01T00:00:00.000-03:00",1,20, "9d168f07-2c58-493d-9d98-55baf59d6f6b"))
        list_call.add(requestrx.getmontlyPaymentsRx("2020-03-01T00:00:00.000-03:00",2,20, "9d168f07-2c58-493d-9d98-55baf59d6f6b"))
        return Observable.merge(list_call).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
    }
}
