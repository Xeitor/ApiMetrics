package com.example.wisproapi.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.wisproapi.R
import com.example.wisproapi.helpers.CustomDate
import com.google.android.gms.ads.*
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var mAdView : AdView
    private lateinit var mInterstitialAd: InterstitialAd

    override fun onCreate(savedInstanceState: Bundle?) {
        val myIntent = Intent(this@MainActivity, LoginActivity::class.java)
        myIntent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY


        var logged_in: Boolean = true

        if (logged_in) {
            startActivity(myIntent)
            killActivity()
        }

        setTheme(R.style.AppThemeV2)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_final)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_login), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        //Configure navheader info
        val header: View = navView.getHeaderView(0)
        val date: TextView = header.findViewById(R.id.fecha_hoy)
        date.text = CustomDate.getCompleteDate()

//        val isp_id: TextView = header.findViewById(R.id.isp_id_navheader)
//        isp_id.text = getSharedPreferences("isp_information", Context.MODE_PRIVATE).getString("isp_id", "")

//        val editor = getSharedPreferences("isp_information", Context.MODE_PRIVATE).edit()
//        editor.putString("isp_id", "")
//        editor.apply()
    }
    fun setupAdd(){
        //AdView requests
        MobileAds.initialize(this) {}
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = "ca-app-pub-3940256099942544/1033173712"
        mInterstitialAd.loadAd(adRequest)
        mInterstitialAd.adListener = object : AdListener() {
            override fun onAdLoaded() {
                displayInterstitial()
            }
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        mInterstitialAd.loadAd(AdRequest.Builder().build())
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun displayInterstitial() {
        if (mInterstitialAd.isLoaded) {
            mInterstitialAd.show()
        }
    }
    private fun killActivity() {
        finish()
    }
}
