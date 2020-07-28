package com.example.wisproapi.retrofit_models

import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

class PaymentHandler() {

    var payments: MutableList<PaymentObject> = ArrayList()
    var total: Float = 0F
    var clientes: HashSet<String> = HashSet()

    var dayly_payments: Int = 0
    var dayly_total: Float = 0F
    var dayly_clients: HashSet<String> = HashSet()


    fun addPayments(toAdd: List<PaymentObject>) {
        var aux: Float = 0F
        //DateTime
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.getDefault())
        val currentDateandTime = sdf.format(Calendar.getInstance().time).substring(0,10)

        for (payment in toAdd) {
            payments.add(payment)
            clientes.add(payment.client_name.toString())
            var temp: String = payment.amount + "F"
            aux += temp.toFloat()

            if (payment.payment_date!!.contains(currentDateandTime)) {
                dayly_clients.add(payment.client_name.toString())
                dayly_payments++
                var temp: String = payment.amount + "F"
                dayly_total += temp.toFloat()
            }
        }
        total += aux
    }

    /**
     * Recursive implementation, invokes itself for each factor of a thousand, increasing the class on each invokation.
     * @param n the number to format
     * @param iteration in fact this is the class from the array c
     * @return a String representing the number n formatted in a cool looking way.
     */
}