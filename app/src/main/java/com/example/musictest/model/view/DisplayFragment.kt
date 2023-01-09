package com.example.musictest.model.view

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.musictest.model.remote.Network
import com.example.musictest.model.MusicResponse
import com.example.musictest.model.view.adapter.MusicAdapter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musictest.databinding.DisplayFragmentBinding


private const val TAG ="Display Fragment"
class DisplayFragment: Fragment() {
    companion object{
        const val EXTRA_MUSIC = "EXTRA_MUSIC"
        fun newInstance(musicType: String) = DisplayFragment().apply {
            arguments = Bundle().apply {
                putString(EXTRA_MUSIC, musicType)
            }
        }
    }

    private  lateinit var binding: DisplayFragmentBinding
    //This property is only valid between onCreateView
    private lateinit var musicType: String
    private val adapter: MusicAdapter by lazy{
        MusicAdapter(mutableListOf())
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DisplayFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        arguments?.let {
            musicType = it.getString(EXTRA_MUSIC) ?: ""
            getMusicData(musicType)
        }
        initViews()
        return binding.root

    }



    private fun getMusicData(musicType: String
    ){
        Log.d(TAG, "getMusicData: $musicType")
        Network().api
            .getNextMusicPage(
                musicType
                )
            .enqueue(
                object: Callback<MusicResponse> {
                    override fun onResponse(
                        call: Call<MusicResponse>,
                        response: Response<MusicResponse>
                    ) {
                        // You have a MusicResponse
                        // You have a Empty Response
                        if( response.isSuccessful)
                        {
                            response.body()?.let{
                                updateAdapter(it)
                            }
                        }else{
                            Log.d(TAG, "onResponse: ${response.message()}")
                        }
                    }
                    override fun onFailure(call: Call<MusicResponse>, t: Throwable) {
                        Log.d(TAG, "onFailure: ${t.message}")
                        t.printStackTrace()
                    }

                }
            )


    }
    //https://itunes.apple.com/search?amp;media=music&amp;entity=song&amp;limit=50
    private fun initViews(){
        binding.apply {
            musicListResult.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            musicListResult.adapter = adapter
            //musicListResult.addOnScrollListener(scrollListener)
        }
    }
    private fun updateAdapter(newDataSet: MusicResponse) {
        adapter.updateDataSet(newDataSet.results)

    }

    /*private val scrollListener = object: RecyclerView.OnScrollListener(){
        private  var visibleItemCount = 0
        private  var totalItemCount = 0
        private  var pastItemVisible = 0

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            Log.d(TAG, "onScrolled: dy: $dy")

            if (dy > 0){
                visibleItemCount = layoutManager.childCount
                totalItemCount = layoutManager.itemCount
                pastItemVisible = layoutManager.findFirstVisibleItemPosition()

                Log.d(TAG, "onScrolled: visible: $visibleItemCount, total: $totalItemCount")
                if (visibleItemCount + pastItemVisible >= totalItemCount){
                    fetchMoreData(totalItemCount)
                }
            }

        }
    }*/

   /* private fun fetchMoreData(nextStartIndex: Int) {
        getMusicData(musicType, nextStartIndex)
    }*/

}