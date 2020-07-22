package com.example.wisproapi.activities.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.*
import androidx.viewpager.widget.ViewPager
import com.example.wisproapi.R
import com.example.wisproapi.activities.MyRecyclerViewAdapter
import com.example.wisproapi.viewmodels.PaymentsViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import android.content.Context
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View.OnTouchListener
import androidx.viewpager2.widget.ViewPager2
import com.example.wisproapi.OnSwipeTouchListener
import com.google.android.material.tabs.TabLayout

class HomeFragment : Fragment() {

    private lateinit var demoCollectionPagerAdapter: PagerAdapter
    private lateinit var viewPager: ViewPager

    var adapter: MyRecyclerViewAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)
//        sceneRoot.setOnClickListener {
//            TransitionManager.go(anotherScene, fadeTransition)
//        }
//
//        anotherScene.setEnterAction{
//            sceneRoot.setOnClickListener{
//                TransitionManager.go(aScene, fadeTransition)
//            }
//        }
//        aScene.setEnterAction{
//            sceneRoot.setOnClickListener{
//                TransitionManager.go(anotherScene, fadeTransition)
//            }
//        }

        val view_model: PaymentsViewModel by viewModels()
        view_model.get_live_payment().observe(viewLifecycleOwner, androidx.lifecycle.Observer { new->
            //SetupReciclerView
            val recyclerView: RecyclerView = root.findViewById(R.id.reciclerview_widget)
            recyclerView.layoutManager =
                LinearLayoutManager(this.context)
            adapter = MyRecyclerViewAdapter(this.context, view_model.livePayment.value!!.payments)
            recyclerView.adapter = adapter
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        demoCollectionPagerAdapter = PagerAdapter(childFragmentManager)
        viewPager = view.findViewById(R.id.view_pager)
        viewPager.adapter = demoCollectionPagerAdapter

        val tabLayout:TabLayout = view.findViewById(R.id.tab_layout)
        tabLayout.setupWithViewPager(viewPager)
    }
}
