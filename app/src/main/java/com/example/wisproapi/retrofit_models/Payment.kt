package com.example.wisproapi.retrofit_models
import androidx.lifecycle.LiveData
import com.google.gson.annotations.SerializedName

class Payment: BaseRequest() {
    @SerializedName("data") val data: List<PaymentObject>? = null
}
class PaymentObject {
    @SerializedName("id")  val id: String? = null
    @SerializedName("public_id")  val public_id: Integer? = null
    @SerializedName("amount")  val amount: String? = null
    @SerializedName("client_name")  val client_name: String? = null
    @SerializedName("transaction_kind")  val transaction_kind: String? = null
    @SerializedName("payment_date")  val payment_date: String? = null
    @SerializedName("email_collector")  val email_collector: String? = null
}