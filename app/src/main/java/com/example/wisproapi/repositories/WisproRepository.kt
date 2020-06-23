package com.example.wisproapi.repositories

import androidx.lifecycle.MutableLiveData
import com.example.wisproapi.retrofit_models.JsonPayments
import com.example.wisproapi.retrofit_models.Payment
import com.example.wisproapi.retrofit_models.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WisproRepository() {

    val request = ServiceBuilder.buildService(JsonPayments::class.java)
    val listCallV2: Call<Payment> = request.getPostsV2(2,100, "64dc19d7-1227-4741-9fe3-de3f476aa203")
    val listCallV1: Call<Payment> = request.getPostsV2(1,100, "64dc19d7-1227-4741-9fe3-de3f476aa203")

    val calls: MutableList<Call<Payment>> = ArrayList()

    private val monthly_payments = ArrayList<Payment>()
    private val live_monthly_payments = MutableLiveData<ArrayList<Payment>>()

    fun addIssuePost(payment: Payment) {
        monthly_payments.add(payment)
        live_monthly_payments.value = monthly_payments
    }

    fun getMonthlyPayments(): MutableLiveData<ArrayList<Payment>> {
        calls.add(listCallV1)
        calls.add(listCallV2)

        

        listCallV2.clone().enqueue(object : Callback<Payment> {
            override fun onResponse(call: Call<Payment>, response: Response<Payment>) {
                addIssuePost(response.body())
            }
            // Error case is left out for brevity.
            override fun onFailure(call: Call<Payment>, t: Throwable) {
                TODO()
            }
        })
        return live_monthly_payments
    }

    fun getPayments(): MutableLiveData<Payment> {
        var data = MutableLiveData<Payment>()
        listCallV2.clone().enqueue(object : Callback<Payment> {
            override fun onResponse(call: Call<Payment>, response: Response<Payment>) {
                data.value = response.body()
            }
            // Error case is left out for brevity.
            override fun onFailure(call: Call<Payment>, t: Throwable) {
                TODO()
            }
        })
        return data
    }

}