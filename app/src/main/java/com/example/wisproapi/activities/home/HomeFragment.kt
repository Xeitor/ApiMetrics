package com.example.wisproapi.activities.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.wisproapi.R
import com.example.wisproapi.activities.home.viewpager.PagerAdapter
import com.example.wisproapi.activities.home.viewpager.ReciclerViewFragment
import com.example.wisproapi.activities.home.viewpager.ViewPagerAlpha
import com.example.wisproapi.helpers.MyRecyclerViewAdapter
import com.example.wisproapi.viewmodels.PaymentsViewModel
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.InterstitialAd
import me.relex.circleindicator.CircleIndicator


class HomeFragment : Fragment() {

    private lateinit var demoCollectionPagerAdapter: PagerAdapter
    private lateinit var viewPager: ViewPagerAlpha
    val view_model: PaymentsViewModel by viewModels()
    var adapter: MyRecyclerViewAdapter? = null
    lateinit var mAdView : AdView
    var progressBar: ProgressBar? = null
    private lateinit var mInterstitialAd: InterstitialAd
    private lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        root = inflater.inflate(R.layout.fragment_home, container, false)
        //ViewPager setup
        viewPagerSetUp()

        // ReciclerView fragment setup
        reciclerViewConfig()

        //LoadPayments
        loadPayments()

        //Missing token information
        checkForMissingToken()

        //Linear ProgressBar config
        progressBarConfig()

        return root
    }

    override fun onResume() {
        super.onResume()
        loadPayments()
    }

    fun loadPayments(){
        Thread(Runnable {
            try {
                progressBar!!.visibility = VISIBLE
                view_model.get_live_payment()
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }).start()
    }
    fun displayInterstitial() {
        if (mInterstitialAd.isLoaded) {
            mInterstitialAd.show()
        }
    }
    fun checkForMissingToken() {
        var missingTokenTextView: TextView = root.findViewById(R.id.text_gallery)
        var prefs: SharedPreferences = requireActivity().getSharedPreferences("isp_information", Context.MODE_PRIVATE)
        var isp_id = prefs.getString("isp_id", null) //"No name defined" is the default value.
        if (isp_id.isNullOrEmpty()) missingTokenTextView.text = "Ups! Al parecer aún no configuras un token id.\nDirígite a la ventana de ajustes para hacerlo"
    }
    fun reciclerViewConfig(){
        val fragmentManager = activity?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        val fragment = ReciclerViewFragment()
        fragmentTransaction?.add(R.id.relative_layout, fragment)
        fragmentTransaction?.commit()
    }
    fun viewPagerSetUp(){
        demoCollectionPagerAdapter = PagerAdapter(childFragmentManager)
        viewPager = root!!.findViewById(R.id.view_pager)
        viewPager.adapter = demoCollectionPagerAdapter
        val indicator = root.findViewById(R.id.indicator) as CircleIndicator
        indicator.setViewPager(viewPager)
    }

    fun progressBarConfig(){
        progressBar = root.findViewById(R.id.progressBar)
        PaymentsViewModel.livePayment.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            progressBar!!.visibility = INVISIBLE
        })

    }

}
