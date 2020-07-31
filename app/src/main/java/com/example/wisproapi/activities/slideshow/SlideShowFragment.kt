package com.example.wisproapi.activities.slideshow


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.wisproapi.R
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

class SlideShowFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var root: View = inflater.inflate(R.layout.fragment_slideshow, container, false)
//        val textView: EditText = root.findViewById(R.id.isp_id_input)
        var button: Button = root.findViewById(R.id.save_button)

        //Get current ispvalue
        var prefs: SharedPreferences = requireActivity().getSharedPreferences("isp_information", Context.MODE_PRIVATE)
        var isp_id = prefs.getString("isp_id", "") //"No name defined" is the default value.

        var editText: EditText = root.findViewById(R.id.editText1)
        if (isp_id!!.isEmpty()) editText.text = isp_id

        button.setOnClickListener {
            btnValidateInputClick(root)
            val layoutUserName = requireView().findViewById(R.id.layoutIspId) as TextInputLayout
            val strUsername: String = layoutUserName.editText!!.text.toString()
            val editor = context?.getSharedPreferences("isp_information", Context.MODE_PRIVATE)?.edit()
            editor?.putString("isp_id", strUsername)
            editor?.apply()
        }
        return root
    }

    private fun btnValidateInputClick(view: View?) {
        val layoutUserName = requireView().findViewById(R.id.layoutIspId) as TextInputLayout
        val strUsername: String = layoutUserName.editText!!.text.toString()

        if (strUsername.length == 36) {
            Snackbar.make(requireView(), strUsername, Snackbar.LENGTH_SHORT).show()
            layoutUserName.isErrorEnabled = false
        } else {
            layoutUserName.error = "Se requieren 31 caracteres"
            layoutUserName.isErrorEnabled = true
        }
    }
}
