package com.example.wisproapi.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wisproapi.R
import com.example.wisproapi.retrofit_models.Payment
import com.example.wisproapi.retrofit_models.PaymentHandler
import com.example.wisproapi.retrofit_models.PaymentObject
import com.example.wisproapi.viewmodels.PaymentsViewModel
import io.reactivex.disposables.Disposable
import io.reactivex.internal.util.HalfSerializer.onNext
import rx.Observer
import rx.Subscriber

class MainActivity : AppCompatActivity() {
    var adapter: MyRecyclerViewAdapter? = null
    private var textView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Toolbar
        val toolbar: Toolbar = findViewById<Toolbar>(R.id.my_toolbar)
        toolbar.title = "Wispro Api"
        setSupportActionBar(toolbar)

        //Textview
        textView = findViewById(R.id.statuscode)
        val textViewV2 = textView

        //Getting payment object and updating view
        val view_model: PaymentsViewModel by viewModels()
        val payment_handler: PaymentHandler = PaymentHandler()
    }

    fun onItemClick(view: View?, position: Int) {
        Toast.makeText(
            this,
            "You clicked " + adapter?.getItem(position).toString() + " on row number " + position,
            Toast.LENGTH_SHORT
        ).show()
    }
}