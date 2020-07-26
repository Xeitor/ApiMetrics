package com.example.wisproapi.retrofit_models

class PaymentHandler() {

    var payments: MutableList<PaymentObject> = ArrayList()
    var total: Float = 0F
    var clientes: HashSet<String> = HashSet()

    fun addPayments(toAdd: List<PaymentObject>) {
        var aux: Float = 0F
        for (payment in toAdd) {
            payments.add(payment)
            clientes.add(payment.client_name.toString())
            var temp: String = payment.amount + "F"
            aux += temp.toFloat()
        }
        total += aux
    }
}