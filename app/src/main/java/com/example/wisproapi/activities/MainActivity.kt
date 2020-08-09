package com.example.wisproapi.activities

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
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var mAdView : AdView


    override fun onCreate(savedInstanceState: Bundle?) {
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
        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        //Configure navheader info
        val header: View = navView.getHeaderView(0)
        val date: TextView = header.findViewById(R.id.fecha_hoy)
        date.text = CustomDate.getCompleteDate()

        MobileAds.initialize(this) {}
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
//        val isp_id: TextView = header.findViewById(R.id.isp_id_navheader)
//        isp_id.text = getSharedPreferences("isp_information", Context.MODE_PRIVATE).getString("isp_id", "")

//        val editor = getSharedPreferences("isp_information", Context.MODE_PRIVATE).edit()
//        editor.putString("isp_id", "")
//        editor.apply()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
