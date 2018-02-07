package com.example.taha.youtubeplaylist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.taha.youtubeplaylist.Library.ApiClient
import com.example.taha.youtubeplaylist.Library.ApiInterface
import com.example.taha.youtubeplaylist.Library.PlaylistData
import com.example.taha.youtubeplaylist.Library.RecyclerViewAdapter.PlayListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    val API_KEY = "AIzaSyB260owhGgViJn_1eCyKr6MDUBYqiieM0o"
    val playlistId = "LLexH9SCdSTccX2a2M087sBg"
    var gelenVeri:PlaylistData? =  null
    var items:List<PlaylistData.Items>? = null
    var myAdapter:PlayListAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var apiInterface = ApiClient.client?.create(ApiInterface::class.java)

        var result = apiInterface?.tumListeleriGetir(playlistId,API_KEY,"50")

        result?.enqueue(object:Callback <PlaylistData>{
            override fun onFailure(call: Call<PlaylistData>?, t: Throwable?) {
                Log.e("SIÇTI",""+t?.printStackTrace())
            }

            override fun onResponse(call: Call<PlaylistData>?, response: Response<PlaylistData>?) {
                Log.e("TAMAMLANDI",call?.request()?.url()?.toString())
                var body = response?.body()
                items = response?.body()?.items
                myAdapter = PlayListAdapter(items!!)
                supportActionBar?.subtitle = "Toplam vide sayisi: ${items?.size}"
                /*for(i in items!!)
                    Log.e("TAMAMLANDI!", i.snippet?.title)*/
                myRecyclerView.adapter = myAdapter
                var myManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)
                myRecyclerView.layoutManager = myManager
            }

        })

    }


}
