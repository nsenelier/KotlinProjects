package com.example.musictest.model.remote

import com.example.musictest.model.MusicResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicService {
  /**
    Tab 1:
    https://itunes.apple.com/search?term=rock&amp;media=music&amp;entity=song&amp;limit=50
https://itunes.apple.com/search?amp;media=music&amp;entity=song&amp;limit=50

    Tab 2:
    https://itunes.apple.com/search?term=classick&amp;media=music&amp;entity=song&amp;limit=50

    Tab 3:
    https://itunes.apple.com/search?term=pop&amp;media=music&amp;entity=song&amp;limit=50
  ***/
  @GET(ENDPOINT)
  fun getNextMusicPage(
      @Query(PARAM_TERM) categoryChoice: String,
      //@Query(PARAM_START_INDEX) pageIndex: Int
  ): Call<MusicResponse>

}