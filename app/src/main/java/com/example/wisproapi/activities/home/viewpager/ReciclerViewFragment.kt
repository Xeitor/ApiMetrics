package com.example.wisproapi.activities.home.viewpager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wisproapi.R
import com.example.wisproapi.activities.MyRecyclerViewAdapter
import com.example.wisproapi.retrofit_models.PaymentHandler
import com.example.wisproapi.viewmodels.PaymentsViewModel

class ReciclerViewFragment : Fragment() {

    var adapter: MyRecyclerViewAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_recicler_view, container, false)

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
