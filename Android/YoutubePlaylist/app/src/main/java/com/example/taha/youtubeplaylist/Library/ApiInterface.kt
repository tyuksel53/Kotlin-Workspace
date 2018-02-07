package com.example.taha.youtubeplaylist.Library

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Taha on 07-Feb-18.
 */
interface ApiInterface {
/*https://www.googleapis.com/youtube/v3/playlists?part=snippet&channelId=UCexH9SCdSTccX2a2M087sBg&maxResults=50&key={YOUR_API_KEY}*/
    @GET("playlistItems?part=snippet")
    fun tumListeleriGetir(@Query("playlistId") playlistId:String,
                          @Query("key") key:String,
                          @Query("maxResults")maxResults:String):Call<PlaylistData>


}