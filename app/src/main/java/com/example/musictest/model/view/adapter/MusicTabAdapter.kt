package com.example.musictest.model.view.adapter


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.musictest.model.view.DisplayFragment



class MusicTabAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {

    //This adapter is created due to the utilization of viewPager2
    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> {DisplayFragment()}
            1 -> {DisplayFragment()}
            2 -> {DisplayFragment()}
            else -> throw Exception("No tab selected")
        }

    }



}
