package com.example.wisproapi.activities.home.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.wisproapi.helpers.CustomDate
import com.example.wisproapi.R
import com.example.wisproapi.helpers.MyRecyclerViewAdapter
import com.example.wisproapi.viewmodels.PaymentsViewModel

class TodayFragment : Fragment() {
    var adapter: MyRecyclerViewAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.scene_a, container, false)
        val textViewTotal: TextView = root!!.findViewById(R.id.total)
        val textViewCantPagos: TextView = root.findViewById(R.id.cant_pagos)
        val textViewCantClientes: TextView = root.findViewById(R.id.cant_clientes)
        val fecha: TextView = root.findViewById(R.id.fecha)
        fecha.text = "Hoy" + "\n" + CustomDate.currentDay + "/" + CustomDate.currentMonth

        PaymentsViewModel.livePayment.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            textViewTotal.text = "$ " + coolFormat(PaymentsViewModel.livePayment.value!!.dayly_total.toDouble(), 0) + "\nTotal"
            textViewCantPagos.text = PaymentsViewModel.livePayment.value!!.dayly_payments.toString() + "\nPagos"
            textViewCantClientes.text = PaymentsViewModel.livePayment.value!!.dayly_clients.size.toString() + "\nClientes"
        })
        return root
    }
    private val c = charArrayOf('k', 'm', 'b', 't')
    private fun coolFormat(n: Double, iteration: Int): String? {
        val d = n.toLong() / 100 / 10.0
        val isRound =
            d * 10 % 10 == 0.0 //true if the decimal part is equal to 0 (then it's trimmed anyway)
        return if (d < 1000) //this determines the class, i.e. 'k', 'm' etc
            (if (d > 99.9 || isRound || !isRound && d > 9.99) //this decides whether to trim the decimals
                d.toInt() * 10 / 10 else d.toString() + "" // (int) d * 10 / 10 drops the decimal
                    ).toString() + "" + c[iteration] else coolFormat(d, iteration + 1)
    }
}
