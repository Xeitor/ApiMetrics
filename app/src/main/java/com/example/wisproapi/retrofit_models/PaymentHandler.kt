package com.example.wisproapi.retrofit_models

class PaymentHandler() {
    var payments: MutableList<PaymentObject> = ArrayList()
    var total: Float = 0F
        get() = makeTotal()
    var test: Float = 0.1F

    fun makeTotal(): Float {
        var aux: Float = 0F
        for (payment in payments) {
            var temp: String = payment.amount + "F"
            aux += temp.toFloat()
        }
        return aux
    }

    fun addPayments(toAdd: List<PaymentObject>) {
        for (payment in toAdd) {
            payments.add(payment)
        }
    }
}