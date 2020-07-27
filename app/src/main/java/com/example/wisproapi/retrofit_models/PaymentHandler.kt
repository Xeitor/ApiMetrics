package com.example.wisproapi.retrofit_models

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

class PaymentHandler() {

    var payments: MutableList<PaymentObject> = ArrayList()
    var total: Float = 0F
    var total_cool_format: String = ""
    var clientes: HashSet<String> = HashSet()

    var dayly_payments: Int = 0
    var dayly_total: Float = 0F
    var dayly_clients: HashSet<String> = HashSet()

    fun addPayments(toAdd: List<PaymentObject>) {
        var aux: Float = 0F
        for (payment in toAdd) {
            payments.add(payment)
            clientes.add(payment.client_name.toString())
            var temp: String = payment.amount + "F"
            aux += temp.toFloat()

            if (payment.payment_date!!.contains("2020-05-06")) {
                dayly_clients.add(payment.client_name.toString())
                dayly_payments++
                var temp: String = payment.amount + "F"
                dayly_total += temp.toFloat()
            }
        }
        total += aux
    }
}