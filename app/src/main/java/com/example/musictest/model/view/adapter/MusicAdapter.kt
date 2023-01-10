package com.example.musictest.model.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.media.MediaPlayer
import android.media.AudioManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musictest.databinding.MusicItemLayoutBinding
import com.example.musictest.model.Music
import com.squareup.picasso.Picasso


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

        holder.itemView.setOnClickListener{
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
            //If music is clicked and no music is playing.
            // However, if another music item is clicked stop and play other choice
            if(!mediaPlayer.isPlaying){
                mediaPlayer.setDataSource(dataSet[position].previewUrl)
                mediaPlayer.prepare()
                mediaPlayer.start()

            } else{
                //This would require the user to know to double click
                //Click to pause and click to play new item
                mediaPlayer.pause()
                mediaPlayer.reset()
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
