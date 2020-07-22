package com.example.wisproapi.activities.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.wisproapi.activities.gallery.GalleryFragment


class PagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager) {
    // Returns total number of pages
    override fun getCount(): Int {
        return NUM_ITEMS
    }

    // Returns the fragment to display for that page
    override fun getItem(position: Int): Fragment {
        var to_return:Fragment = PagerFragment()
        val fragment = PagerFragment()
         when (position) {
            0 -> to_return = fragment
             1 -> to_return = fragment
            else -> null
        }
        return to_return
    }

    // Returns the page title for the top indicator
    override fun getPageTitle(position: Int): CharSequence? {
        return "Today $position"
    }

    companion object {
        private const val NUM_ITEMS = 2
    }
}
