package com.example.wisproapi.repositories

import androidx.lifecycle.MutableLiveData
import com.example.wisproapi.retrofit_models.JsonPayments
import com.example.wisproapi.retrofit_models.Payment
import com.example.wisproapi.retrofit_models.ServiceBuilder
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class WisproRepository {

    val request = ServiceBuilder.buildService(JsonPayments::class.java)
    val requestrx = ServiceBuilder.buildServiceRx(JsonPayments::class.java)


    //Return payments observable, receives calls
    fun getPaymentsRx(): Observable<Payment?>? {
        val montlycallRx: Observable<Payment> = requestrx.getmontlyPaymentsRx("2020-03-01T00:00:00.000-03:00","2020-03-01T00:00:00.000-03:00",2,100, "9d168f07-2c58-493d-9d98-55baf59d6f6b")
        return montlycallRx.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }
    fun getPaymentsRxV2(request: Observable<Payment>): Observable<Payment?>? {
        return request.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }

    //Implemenatation with livedata
    fun getPayments(): MutableLiveData<Payment> {
        val call: Call<Payment> = request.getPostsV2("2020-06-01T00:00:00.000-03:00",1,50, "9d168f07-2c58-493d-9d98-55baf59d6f6b")
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
        //DateTime
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.getDefault())
        val currentDateandTime = sdf.format(Calendar.getInstance().time)

        val text = StringBuffer(currentDateandTime)
        text.replace(11, 23, "00:00:00.000")
        text.replace(8,10,"01")

        var monthBeggin: String
        val myNameChars = currentDateandTime.toCharArray()
        myNameChars[8] = '0'
        myNameChars[9] = '1'
        monthBeggin = String(myNameChars)


        //BaseRequest Setup and list of calls
        var list_call: MutableList<Observable<Payment>> = ArrayList()
        var base_request: Call<Payment> = request.getPostsV2(text.toString(),1,50, "9d168f07-2c58-493d-9d98-55baf59d6f6b")
        var total_pages: Int = getTotalPagesHelper(base_request)

        for (x in 1..total_pages) {
            list_call.add(requestrx.getmontlyPaymentsRxV2(text.toString(),x,50, "9d168f07-2c58-493d-9d98-55baf59d6f6b"))
        }

        //Returns merged listcall
        return Observable.merge(list_call).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
    }
    fun getTotalPagesHelper(request2: Call<Payment>): Int {
        var total_pages: Int = 1
        var total_pages_from_request = request2.execute().body()?.meta?.pagination_info?.total_pages?.toInt()
        if (total_pages_from_request != null) {
            return total_pages_from_request
        } else return total_pages
    }
}
