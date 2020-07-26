package com.example.wisproapi.activities.home.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wisproapi.R
import com.example.wisproapi.activities.MyRecyclerViewAdapter
import com.example.wisproapi.retrofit_models.PaymentHandler
import com.example.wisproapi.viewmodels.PaymentsViewModel

class MonthFragment : Fragment() {
    var adapter: MyRecyclerViewAdapter? = null
    var payment_handler: MutableLiveData<PaymentHandler> = MutableLiveData()
    private var root: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        root = inflater.inflate(R.layout.scene_a, container, false)
        val textViewTotal: TextView = root!!.findViewById(R.id.total)
        val textViewCantPagos: TextView = root!!.findViewById(R.id.cant_pagos)
        val textViewCantClientes: TextView = root!!.findViewById(R.id.cant_clientes)

        PaymentsViewModel.livePayment.observe(viewLifecycleOwner, androidx.lifecycle.Observer { new->
            textViewTotal.text = PaymentsViewModel.livePayment.value!!.total.toString() + "\nTotal"
            textViewCantPagos.text = PaymentsViewModel.livePayment.value!!.payments.size.toString() + "\nPagos"
            textViewCantClientes.text = PaymentsViewModel.livePayment.value!!.clientes.size.toString() + "\nClientes"

        })

        return root
    }
}
