package com.example.musictest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.R
import com.example.musictest.model.view.DisplayFragment
import com.example.musictest.model.view.SearchFragment
import com.example.musictest.model.view.Communicator
import com.example.musictest.model.view.adapter.MusicAdapter

private const val  TAG = "MainActivity"

class MainActivity : AppCompatActivity(), Communicator {

    private lateinit var musicList: RecyclerView
    private lateinit var adapter: MusicAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.search_fragment, SearchFragment())
            .commit()
    }

 override fun sendDataToSearch(musicType: String){
     supportFragmentManager.beginTransaction()
         .add(R.id.display_fragment,
             DisplayFragment.newInstance(
               musicType))
         .addToBackStack(null)
         .commit()
    }
}