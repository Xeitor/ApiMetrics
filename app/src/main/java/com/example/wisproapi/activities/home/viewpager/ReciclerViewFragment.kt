package com.example.wisproapi.activities.home.viewpager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.example.wisproapi.R
import com.example.wisproapi.helpers.MyRecyclerViewAdapter
import com.example.wisproapi.viewmodels.PaymentsViewModel

class ReciclerViewFragment : Fragment() {

    var adapter: MyRecyclerViewAdapter? = null
    val swipe_refresh_layout: SwipeRefreshLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_recicler_view, container, false)

        val swipe_refresh_layout: SwipeRefreshLayout = root!!.findViewById(R.id.swiperefresh)

        swipe_refresh_layout.setOnRefreshListener(OnRefreshListener {
            loadPayments()
        })
        
        PaymentsViewModel.livePayment.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            //SetupReciclerView
            val recyclerView: RecyclerView = root.findViewById(R.id.reciclerview_widget)
            recyclerView.layoutManager = LinearLayoutManager(this.context)
//            val list_Test = Collections.reverse(aymentsViewModel.livePayment.value!!.payments)
            adapter = MyRecyclerViewAdapter(
                this.context,
                PaymentsViewModel.livePayment.value!!.payments.reversed()
            )
            recyclerView.adapter = adapter
            swipe_refresh_layout!!.isRefreshing = false
        })
        return root
    }

    fun loadPayments(){
        Thread(Runnable {
            try {
                val view_model: PaymentsViewModel by viewModels()
                view_model.get_live_payment()
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }).start()
    }
}
