package com.example.wisproapi.activities.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.wisproapi.activities.MyRecyclerViewAdapter
import com.example.wisproapi.R
import com.example.wisproapi.activities.home.viewpager.PagerAdapter
import com.example.wisproapi.activities.home.viewpager.ReciclerViewFragment
import com.example.wisproapi.activities.home.viewpager.ViewPagerAlpha
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

        //ViewPager setup
        demoCollectionPagerAdapter =
            PagerAdapter(
                childFragmentManager
            )
        viewPager = root!!.findViewById(R.id.view_pager)
        viewPager.adapter = demoCollectionPagerAdapter
        val indicator = root!!.findViewById(R.id.indicator) as CircleIndicator
        indicator.setViewPager(viewPager)

        //ReciclerView fragment setup
        val fragmentManager = activity?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()

        val fragment =
            ReciclerViewFragment()
        fragmentTransaction?.add(R.id.relative_layouttest, fragment)
        fragmentTransaction?.commit()


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}
