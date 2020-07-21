package com.example.wisproapi.activities.home

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
import com.example.wisproapi.retrofit_models.PaymentHandler
import com.example.wisproapi.viewmodels.PaymentsViewModel
import com.google.android.material.tabs.TabLayout

class HomeFragment : Fragment() {

    var adapter: MyRecyclerViewAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.statuscode)

        var tabLayout:TabLayout = root.findViewById(R.id.tablayout)
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"))
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"))
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"))


        val view_model: PaymentsViewModel by viewModels()
        view_model.get_live_payment().observe(viewLifecycleOwner, androidx.lifecycle.Observer { new->
            //SetupReciclerView
            val recyclerView: RecyclerView = root.findViewById(R.id.reciclerview_widget)
            recyclerView.layoutManager =
                LinearLayoutManager(this.context)
            adapter = MyRecyclerViewAdapter(this.context, view_model.livePayment.value!!.payments)
            recyclerView.adapter = adapter

            textView.append("Total: " + view_model.livePayment.value!!.total.toString())
//
//            val dividerItemDecoration = DividerItemDecoration(
//                recyclerView.context,
//                this.getResources().getConfiguration().orientation
//            )
//            recyclerView.addItemDecoration(dividerItemDecoration)
        })
        return root
    }
}
