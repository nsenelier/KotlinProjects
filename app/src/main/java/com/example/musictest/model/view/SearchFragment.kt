package com.example.musictest.model.view

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.musictest.R
import com.example.musictest.databinding.SearchFragmentBinding
import com.example.musictest.model.view.adapter.MusicTabAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

private const val TAG ="Search Fragment"
class SearchFragment: Fragment() {
    private lateinit var  binding: SearchFragmentBinding
    private lateinit var musicType: String
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var communicator: Communicator


    override fun onAttach(context: Context) {
        super.onAttach(context)
        when(context){
            is Communicator -> communicator = context
            else -> throw Exception("Incorrect Host Activity")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = SearchFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        initViews()
        return binding.root
    }

    private fun initViews() {
        tabLayout = binding.root.findViewById(R.id.tab_layout)
        viewPager = binding.root.findViewById(R.id.view_pager)
        viewPager.adapter = MusicTabAdapter(binding.root.context as FragmentActivity)
        TabLayoutMediator(tabLayout, viewPager){ tab,index->
            when(index){
                0 -> {
                    tab.text = "Pop"
                    //tab.setIcon(R.drawable.ic_baseline_pause_circle_24)
                    musicType = tab.text.toString()
                    sendSearchParams()
                }
                1 -> {
                    tab.text = "Classic"
                    //tab.setIcon(R.drawable.ic_baseline_pause_circle_24)
                }
                2 -> {
                    tab.text = "Rock"
                    //tab.setIcon(R.drawable.ic_baseline_pause_circle_24)
                }

                else -> {throw  Resources.NotFoundException("Item Not Found")}
            }
        }.attach()
        binding.apply {
            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    musicType = tab.text.toString()
                    viewPager!!.currentItem = tab.position
                    Log.d(TAG, "onTabSelected: $musicType")
                    sendSearchParams()
                }
                override fun onTabUnselected(tab: TabLayout.Tab) {

                }
                override fun onTabReselected(tab: TabLayout.Tab) {

                }
            })
        }
    }
    private fun sendSearchParams(){

        communicator.sendDataToSearch(musicType)
    }
}