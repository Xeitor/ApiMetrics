package com.example.wisproapi.activities.home.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class PagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager) {
    // Returns total number of pages
    override fun getCount(): Int {
        return NUM_ITEMS
    }

    // Returns the fragment to display for that page
    override fun getItem(position: Int): Fragment {
        if (position == 0)
            return TodayFragment()
        else
            return MonthFragment()
    }

    // Returns the page title for the top indicator
    override fun getPageTitle(position: Int): CharSequence? {
        return "Today $position"
    }

    companion object {
        private const val NUM_ITEMS = 2
    }
}
