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
    val listCallV2: Call<Payment> = request.getPostsV2(2,100, "64dc19d7-1227-4741-9fe3-de3f476aa203")
    val listCallV1: Call<Payment> = request.getPostsV2(1,100, "64dc19d7-1227-4741-9fe3-de3f476aa203")
    val listCallRx: Observable<Payment> = requestrx.getPostsRx(1,100, "64dc19d7-1227-4741-9fe3-de3f476aa203")
    val listCallRx2: Observable<Payment> = requestrx.getPostsRx(2,100, "64dc19d7-1227-4741-9fe3-de3f476aa203")
    val montlycallRx: Observable<Payment> = requestrx.getmontlyPaymentsRx("2020-05-01T00:00:00.000-03:00","2020-06-30T00:00:00.000-03:00",1,100, "9d168f07-2c58-493d-9d98-55baf59d6f6b")
    val calls: MutableList<Observable<Payment>> = ArrayList()
    private val monthly_payments = ArrayList<Payment>()
    private val live_monthly_payments = MutableLiveData<ArrayList<Payment>>()

    var wisproRepository = WisproRepository()
    var payment_object: MutableLiveData<Payment> = wisproRepository.getPayments(listCallV2)
    var payments_rx: Observable<Payment?>? = wisproRepository.getPaymentsRx(listCallRx)
    var multiple_payments: Observable<Payment>? = null
    var real_montly_payments: Observable<Payment?>? = wisproRepository.getMontlyPaymentsRx(listCallRx)
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
