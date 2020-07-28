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
import com.example.wisproapi.activities.MyRecyclerViewAdapter
import com.example.wisproapi.viewmodels.PaymentsViewModel

class ReciclerViewFragment : Fragment() {

    var adapter: MyRecyclerViewAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_recicler_view, container, false)

        val swipe_refresh_layout: SwipeRefreshLayout = root!!.findViewById(R.id.swiperefresh)
        val top_to_padding = 100

        swipe_refresh_layout.setOnRefreshListener(OnRefreshListener {
            Thread(Runnable {
                try {
                    swipe_refresh_layout.isRefreshing = true;
                    val view_model: PaymentsViewModel by viewModels()
                    view_model.get_live_payment()
                    swipe_refresh_layout.isRefreshing = false;
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }).start()
        })


        PaymentsViewModel.livePayment.observe(viewLifecycleOwner, androidx.lifecycle.Observer { new->
            //SetupReciclerView
            val recyclerView: RecyclerView = root!!.findViewById(R.id.reciclerview_widget)
            recyclerView.layoutManager =
                LinearLayoutManager(this.context)
            adapter = MyRecyclerViewAdapter(this.context, PaymentsViewModel.livePayment.value!!.payments)
            recyclerView.adapter = adapter
        })
        return root
    }
}
