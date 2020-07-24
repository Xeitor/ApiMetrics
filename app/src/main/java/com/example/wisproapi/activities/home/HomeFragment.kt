package com.example.wisproapi.activities.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.wisproapi.activities.MyRecyclerViewAdapter
import com.example.wisproapi.viewmodels.PaymentsViewModel
import com.google.android.material.tabs.TabLayout
import com.example.wisproapi.R
import me.relex.circleindicator.CircleIndicator

class HomeFragment : Fragment() {

    private lateinit var demoCollectionPagerAdapter: PagerAdapter
    private lateinit var viewPager: ViewPagerAlpha

    var adapter: MyRecyclerViewAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)

        demoCollectionPagerAdapter = PagerAdapter(childFragmentManager)
        viewPager = root!!.findViewById(R.id.view_pager)
        viewPager.adapter = demoCollectionPagerAdapter

        val indicator = root!!.findViewById(R.id.indicator) as CircleIndicator
        indicator.setViewPager(viewPager)

        val fragmentManager = activity?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()

        val fragment = ReciclerViewFragment()
        fragmentTransaction?.add(R.id.relative_layouttest, fragment)
        fragmentTransaction?.commit()

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
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {



    }
}
