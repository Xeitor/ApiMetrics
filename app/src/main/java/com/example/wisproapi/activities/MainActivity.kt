package com.example.wisproapi.activities

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wisproapi.R
import com.example.wisproapi.retrofit_models.PaymentHandler
import com.example.wisproapi.viewmodels.PaymentsViewModel


class MainActivity : AppCompatActivity() {
    var adapter: MyRecyclerViewAdapter? = null
    private var textView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Toolbar
        val toolbar: Toolbar = findViewById<Toolbar>(R.id.my_toolbar)
        toolbar.title = "Wispr Api"
        setSupportActionBar(toolbar)

//        Textview
        textView = findViewById(R.id.statuscode)
        val textViewV2 = textView

//        Getting payment object and updating view
        val view_model: PaymentsViewModel by viewModels()
        view_model.get_live_payment().observe(this, androidx.lifecycle.Observer<PaymentHandler> { new->

            //SetupReciclerView
            val recyclerView: RecyclerView = findViewById(R.id.reciclerview_widget)
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = MyRecyclerViewAdapter(this@MainActivity, view_model.livePayment.value!!.payments)
            recyclerView.adapter = adapter

            textViewV2?.append("Total: " + view_model.livePayment.value!!.total.toString())
//
//            val dividerItemDecoration = DividerItemDecoration(
//                recyclerView.context,
//                this.getResources().getConfiguration().orientation
//            )
//            recyclerView.addItemDecoration(dividerItemDecoration)
        })
    }

    fun onItemClick(view: View?, position: Int) {
        Toast.makeText(
            this,
            "You clicked " + adapter?.getItem(position).toString() + " on row number " + position,
            Toast.LENGTH_SHORT
        ).show()
    }
}