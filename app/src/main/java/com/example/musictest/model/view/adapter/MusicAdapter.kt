package com.example.musictest.model.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.media.MediaPlayer
import android.media.AudioManager
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.musictest.R
import com.example.musictest.databinding.MusicItemLayoutBinding
import com.example.musictest.model.Music


import com.squareup.picasso.Picasso

private const val TAG ="Music Adapter"
class MusicAdapter(private val dataSet: MutableList<Music>): RecyclerView.Adapter<MusicAdapter.MusicViewHolder>(){

    private lateinit var mediaPlayer: MediaPlayer

    class MusicViewHolder(private val binding: MusicItemLayoutBinding):
        RecyclerView.ViewHolder(binding.root){

        //Fix only input is tab selection
        fun onBind(musicItem: Music) {
            binding.apply {
                musicArtistName.text = musicItem.artistName
                musicCollectionName.text = musicItem.collectionName
                musicTrackPrice.text = musicItem.trackPrice.toString()
                Picasso.get()
                    .load(musicItem.artworkUrl100)//You can resize in code
                    .resize(300,300)
                    .into(musicCoverItem)
            }
        }
        val itemCollection: TextView = binding.root.findViewById(R.id.music_collection_name)
        val itemArtistName: TextView = binding.root.findViewById(R.id.music_artist_name)
        val itemTrackPrice: TextView = binding.root.findViewById(R.id.music_track_price)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MusicViewHolder (
            MusicItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    // this is for the fragment tabs


    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        mediaPlayer = MediaPlayer()
        holder.onBind(dataSet[position])
        holder.itemCollection.text = dataSet[position].collectionName
        holder.itemArtistName.text = dataSet[position].artistName
        holder.itemTrackPrice.text = dataSet[position].trackPrice.toString()
        holder.itemView.setOnClickListener{
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
            if(!mediaPlayer.isPlaying){
                try{
                    mediaPlayer.setDataSource(dataSet[position].previewUrl)
                    mediaPlayer.prepare()
                    mediaPlayer.start()
                }catch(e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    override fun getItemCount()= dataSet.size


    fun updateDataSet(results: List<Music>) {
        val originalSize = dataSet.size - 1
        dataSet.addAll(results)
        notifyItemRangeInserted(originalSize, 10)
    }

}
