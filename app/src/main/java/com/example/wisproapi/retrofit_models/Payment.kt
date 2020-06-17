package com.example.wisproapi.retrofit_models
import com.google.gson.annotations.SerializedName

class Payment: BaseRequest() {
    @SerializedName("data") val data: List<PaymentObject> = ArrayList()
}
class PaymentObject {
    @SerializedName("id")  val id: String? = null
    @SerializedName("amount")  val amount: String? = null
    @SerializedName("client_name")  val client_name: String? = null
    @SerializedName("transaction_kind")  val transaction_kind: String? = null
}