package io.github.ferman98.appconfig.component.detail.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import io.github.ferman98.appconfig.component.detail.fragment.DetailActivityFragment
import io.github.ferman98.appconfig.component.detail.fragment.DetailFragFragment

class DetailPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    companion object {
        val tabList = listOf("Activity", "Fragment")
    }

    override fun getItemCount(): Int = tabList.size

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DetailActivityFragment()
            else -> DetailFragFragment()
        }
    }
}