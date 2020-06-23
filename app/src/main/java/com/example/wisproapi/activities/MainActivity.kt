package com.example.wisproapi.activities

import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wisproapi.R
import com.example.wisproapi.retrofit_models.Payment
import com.example.wisproapi.viewmodels.PaymentsViewModel

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

        //textview
        textView = findViewById(R.id.statuscode)
        val textViewV2 = textView

        //Getting payment object and updating view
        val view_model: PaymentsViewModel by viewModels()
        view_model.payment_object?.observe(this, Observer<Payment> { new ->

            textViewV2?.append(view_model.payment_object?.value?.status.toString())
            //ReciclerView setup
            val recyclerView: RecyclerView = findViewById(R.id.reciclerview_widget)
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = MyRecyclerViewAdapter(
                this@MainActivity,
                view_model.payment_object.value!!.data!!
            )
            recyclerView.adapter = adapter
        })
//        var payments: List<PaymentObject> = ArrayList()
//        val view_model: PaymentsViewModel by viewModels()
//
//        view_model.getUser()
//        view_model.live_response?.observe(this, Observer<Payment> { new ->
//
//            payments = view_model.live_response.value?.data!!
//            textViewV2?.append(view_model.live_response?.value?.status.toString())
//
//            //ReciclerView setup
//            val recyclerView: RecyclerView = findViewById(R.id.rvAnimals)
//            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
//            adapter = MyRecyclerViewAdapter(
//                this@MainActivity,
//                payments
//            )
//            recyclerView.adapter = adapter
//        })
    }

    fun onItemClick(view: View?, position: Int) {
        Toast.makeText(
            this,
            "You clicked " + adapter?.getItem(position).toString() + " on row number " + position,
            Toast.LENGTH_SHORT
        ).show()
    }
}
// Interface driven callback
////Step 1: Create an interface for the callback method
//interface ClickEventHandler {
//    public void handleClick();
//}
//
////Step 2: Create a callback handler
////implementing the above interface
//class ClickHandler implements ClickEventHandler {
//    public void handleClick() {
//        System.out.println("Clicked");
//    }
//}
//
////Step 3: Create event generator class
//class Button {
//    public void onClick(ClickEventHandler clickHandler) {
//        clickHandler.handleClick();
//    }
//}
//
//public class Tester {
//    public static void main(String[] args) {
//        Button button = new Button();
//        ClickHandler clickHandler = new ClickHandler();
//        //pass the clickHandler to do the default operation
//        button.onClick(clickHandler);
//
//        Button button1 = new Button();
//        //pass the interface to implement own operation
//        button1.onClick(new ClickEventHandler() {
//            @Override
//            public void handleClick() {
//                System.out.println("Button Clicked");
//            }
//        });
//    }
//}

// Retrofit creation
//        val retrofit = Retrofit.Builder().baseUrl("https://www.cloud.wispro.co")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        val jsonInvoices:JsonPayments = retrofit.create(
//            JsonPayments::class.java
//        )
//        val listCall: Call<Payment> = jsonInvoices.getPostsV2(2, 100)

//        listCallV2.enqueue(object : Callback<Payment> {
//            override fun onResponse(call: Call<Payment>?, response: Response<Payment>
//            ) {
//                if (!response.isSuccessful) {
//                    textView?.setText("Code " + response.code())
//                    return
//                }
//                val payments = response.body()
//
//                var content = "${payments.status}"
//
//                textView?.append(content)
//
//                // set up the RecyclerView
//                val recyclerView: RecyclerView = findViewById(R.id.rvAnimals)
//                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
//                adapter = MyRecyclerViewAdapter(this@MainActivity, payments.data)
//                recyclerView.adapter = adapter
//            }
//
//            override fun onFailure(call: Call<Payment>, t: Throwable?) {
//                textView?.setText("Error")
//            }
//        })