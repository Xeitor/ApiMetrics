package com.example.wisproapi.activities.gallery

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.wisproapi.R
import com.example.wisproapi.repositories.AccountRepository

class GalleryFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_gallery, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        var prefs: SharedPreferences = requireActivity().getSharedPreferences("client_information", Context.MODE_PRIVATE)

        var repository = AccountRepository(requireContext())
        repository.authenticateUser().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            textView.text = it.data?.uid + it.data?.allow_password_change.toString() + prefs.getString("access_token", "defvalue")
        })

        return root
    }
}
