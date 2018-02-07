package com.example.taha.youtubeplaylist.Library

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Taha on 07-Feb-18.
 */
object ApiClient{

    val BASE_URL = "https://www.googleapis.com/youtube/v3/"

    private var retrofit:Retrofit? = null

    val client:Retrofit?
        get() {
            if(retrofit == null)
            {
                retrofit = Retrofit.
                        Builder().
                        baseUrl(BASE_URL).
                        addConverterFactory(GsonConverterFactory.create()).
                        build()
            }

            return retrofit
        }

}