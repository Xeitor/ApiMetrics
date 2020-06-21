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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//class UserProfileViewModel @Inject constructor(
//    savedStateHandle: SavedStateHandle,
//    userRepository: UserRepository
//) : ViewModel() {
//    val userId : String = savedStateHandle["uid"] ?:
//    throw IllegalArgumentException("missing user id")
//    val user : LiveData<User> = userRepository.getUser(userId)
//}

class PaymentsViewModel:ViewModel() {
    var userRepository = WisproRepository()
    var user: MutableLiveData<Payment> = userRepository!!.getUser()
}

    // Data for request
//    val request = ServiceBuilder.buildService(JsonPayments::class.java)
//    val listCallV2: Call<Payment> = request.getPostsV2(2,100, "64dc19d7-1227-4741-9fe3-de3f476aa203")
//
//    var live_response : MutableLiveData<Payment> = MutableLiveData()
//    //
//    fun getUser(): MutableLiveData<Payment> {
//        val data = MutableLiveData<Payment>()
//        listCallV2.clone().enqueue(object : Callback<Payment> {
//            override fun onResponse(call: Call<Payment>, response: Response<Payment>) {
//                live_response.value = response.body()
//            }
//            // Error case is left out for brevity.
//            override fun onFailure(call: Call<Payment>, t: Throwable) {
//                TODO()
//            }
//        })
//        return data
//    }
//
//    fun getGameList(): MutableLiveData<Payment>? {
//        return live_response
//    }