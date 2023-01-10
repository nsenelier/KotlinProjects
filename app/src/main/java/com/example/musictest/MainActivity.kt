package com.example.musictest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.musictest.model.view.DisplayFragment
import com.example.musictest.model.view.SearchFragment
import com.example.musictest.model.view.Communicator


//private const val  TAG = "MainActivity"

class MainActivity : AppCompatActivity(), Communicator {

    //private lateinit var musicList: RecyclerView
    //private lateinit var adapter: MusicAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.search_fragment, SearchFragment())
            .commit()
    }

 override fun sendDataToSearch(musicType: String){
     supportFragmentManager.beginTransaction()
         .replace(R.id.display_fragment,
             DisplayFragment.newInstance(
               musicType))
         .addToBackStack("")
         .commit()
    }
}