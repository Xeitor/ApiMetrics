package com.example.wisproapi.activities.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wisproapi.R
import com.example.wisproapi.helpers.MyRecyclerViewAdapter
import com.example.wisproapi.repositories.AccountRepository
import com.example.wisproapi.viewmodels.PaymentsViewModel

class GalleryFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_gallery, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)

        var repository = AccountRepository()
        repository.authenticateUser().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            textView.text = it.data!!.email + it.data!!.uid
        })
        return root
    }
}
