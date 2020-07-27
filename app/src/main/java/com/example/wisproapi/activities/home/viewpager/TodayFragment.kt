package com.example.wisproapi.activities.home.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wisproapi.R
import com.example.wisproapi.activities.MyRecyclerViewAdapter
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
        val textViewCantPagos: TextView = root!!.findViewById(R.id.cant_pagos)
        val textViewCantClientes: TextView = root!!.findViewById(R.id.cant_clientes)
        val fecha: TextView = root!!.findViewById(R.id.fecha)
        fecha.text = "Hoy" + "\n" + "22 Sep"

        PaymentsViewModel.livePayment.observe(viewLifecycleOwner, androidx.lifecycle.Observer { new->
            textViewTotal.text = PaymentsViewModel.livePayment.value!!.dayly_total.toString() + "\nTotal"
            textViewCantPagos.text = PaymentsViewModel.livePayment.value!!.dayly_payments.toString() + "\nPagos"
            textViewCantClientes.text = PaymentsViewModel.livePayment.value!!.dayly_clients.size.toString() + "\nClientes"

        })
        return root
    }
}
