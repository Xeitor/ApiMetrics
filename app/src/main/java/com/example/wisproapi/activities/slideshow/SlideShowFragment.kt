package com.example.wisproapi.activities.slideshow


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.SystemClock.sleep
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import br.com.simplepass.loadingbutton.customViews.CircularProgressButton
import com.example.wisproapi.R
import com.example.wisproapi.repositories.WisproRepository
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import es.dmoral.toasty.Toasty

class SlideShowFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var root: View = inflater.inflate(R.layout.fragment_slideshow, container, false)
//        val textView: EditText = root.findViewById(R.id.isp_id_input)
        var editText: TextView = root.findViewById(R.id.isp_id_actual)
        //Get current ispvalue
        var prefs: SharedPreferences = requireActivity().getSharedPreferences("isp_information", Context.MODE_PRIVATE)
         //"No name defined" is the default value.
        var live_isp_id: MutableLiveData<String> = MutableLiveData()
        var isp_id = prefs.getString("isp_id", "")
        live_isp_id.value = isp_id

        live_isp_id.observe(viewLifecycleOwner, androidx.lifecycle.Observer { new->
            editText.text = "Token actual: " + live_isp_id.value
        })

        var btn: CircularProgressButton =  root.findViewById(R.id.btn_id)
        btn.setOnClickListener {
            btnValidateInputClick(root)
            btn.startAnimation()

            val layoutUserName = requireView().findViewById(R.id.layoutIspId) as TextInputLayout
            val strUsername: String = layoutUserName.editText!!.text.toString()
            WisproRepository(requireActivity()).checkIspId(strUsername).subscribe({
                //Onnext
                if (it.status!!.equals(200)) {
                    live_isp_id.value = strUsername
                    val editor = context?.getSharedPreferences("isp_information", Context.MODE_PRIVATE)?.edit()
                    editor?.putString("isp_id", strUsername)
                    editor?.apply()
                    Toasty.success(requireActivity(), "Token configurado correctamente!", Toast.LENGTH_SHORT, true).show()
                } else{
                    Toasty.error(requireActivity(), "Token invalido", Toast.LENGTH_SHORT, true).show()
                }
                btn.revertAnimation()
            }, {
                //Onerror
            })
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
