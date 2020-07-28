package com.example.wisproapi.retrofit_models

class PaymentHandler() {

    var payments: MutableList<PaymentObject> = ArrayList()
    var total: Float = 0F
    var clientes: HashSet<String> = HashSet()

    var dayly_payments: Int = 0
    var dayly_total: Float = 0F
    var dayly_clients: HashSet<String> = HashSet()

    var total_daily_cool_format: String? = "00"

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

    /**
     * Recursive implementation, invokes itself for each factor of a thousand, increasing the class on each invokation.
     * @param n the number to format
     * @param iteration in fact this is the class from the array c
     * @return a String representing the number n formatted in a cool looking way.
     */
}