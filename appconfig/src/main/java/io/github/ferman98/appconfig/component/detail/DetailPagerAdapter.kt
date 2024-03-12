package io.github.ferman98.appconfig.component.detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class DetailPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    companion object {
        val tabList = listOf("Activity", "Fragment", "Console")
    }

    override fun getItemCount(): Int = tabList.size

    override fun createFragment(position: Int): Fragment {
        return DetailFragment(position + 1)
    }
}