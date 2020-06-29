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

    val calls: MutableList<Observable<Payment>> = ArrayList()

    private val monthly_payments = ArrayList<Payment>()
    private val live_monthly_payments = MutableLiveData<ArrayList<Payment>>()

    fun addIssuePost(payment: Payment) {
        monthly_payments.add(payment)
        live_monthly_payments.value = monthly_payments
    }

    fun getPaymentsRx(): Observable<Payment?>? {
        return listCallRx.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getPayments(): MutableLiveData<Payment> {
        var data = MutableLiveData<Payment>()
        listCallV2.clone().enqueue(object : Callback<Payment> {
            override fun onResponse(call: Call<Payment>, response: Response<Payment>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<Payment>, t: Throwable) {
                TODO()
            }
        })
        return data
    }
    fun getmontlhyPayments(): Observable<Payment> {
        calls.add(listCallRx)
        calls.add(listCallRx2)

        val payments: List<Payment> = ArrayList()

        return Observable.merge(calls).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
        // Will be triggered if all requests will end successfully (4xx and 5xx also are successful requests too)
    }

    fun convertV2(a: Payment, b:Payment): List<Payment> {
        var lista: MutableList<Payment> = ArrayList()
        lista.add(a)
        lista.add(b)
        return lista
    }
}
