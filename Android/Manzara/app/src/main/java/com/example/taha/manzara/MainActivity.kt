package com.example.taha.manzara

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.GridLayout
import android.widget.Toast
import com.example.taha.manzara.Library.Manzara
import com.example.taha.manzara.Library.ManzaraAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var tumManzaralar:ArrayList<Manzara>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fillDataSource()

        recyclerView.adapter = ManzaraAdapter(tumManzaralar)
        var linearLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager = linearLayoutManager


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.anamenu,menu)

        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        var itemId = item?.itemId
        when(itemId)
        {
            R.id.linearViewVertical -> {
                var linearLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
                recyclerView.layoutManager = linearLayoutManager

            }

            R.id.linearViewHorizontal -> {
                var linearLayoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
                recyclerView.layoutManager = linearLayoutManager

            }

            R.id.menuStaggaredVertical -> {
                var linearLayoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
                recyclerView.layoutManager = linearLayoutManager

            }

            R.id.menuStaggaredHorizantal -> {
                var linearLayoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.HORIZONTAL)
                recyclerView.layoutManager = linearLayoutManager
            }

            R.id.gridView -> {
                var linearLayoutManager = GridLayoutManager(this,2)
                recyclerView.layoutManager = linearLayoutManager

            }


        }

        return super.onOptionsItemSelected(item)
    }



    fun fillDataSource()
    {

        var tumResimler = arrayOf(R.drawable.thumb_1_0, R.drawable.thumb_1_1, R.drawable.thumb_1_2, R.drawable.thumb_1_3,
        R.drawable.thumb_1_4, R.drawable.thumb_1_5, R.drawable.thumb_1_6, R.drawable.thumb_1_7,
        R.drawable.thumb_1_8, R.drawable.thumb_1_9,

        R.drawable.thumb_2_0, R.drawable.thumb_2_1, R.drawable.thumb_2_2, R.drawable.thumb_2_3,
        R.drawable.thumb_2_4, R.drawable.thumb_2_5, R.drawable.thumb_2_6, R.drawable.thumb_2_7,
        R.drawable.thumb_2_8, R.drawable.thumb_2_9,

        R.drawable.thumb_3_0, R.drawable.thumb_3_1, R.drawable.thumb_3_2, R.drawable.thumb_3_3,
        R.drawable.thumb_3_4, R.drawable.thumb_3_5, R.drawable.thumb_3_6, R.drawable.thumb_3_7,
        R.drawable.thumb_3_8, R.drawable.thumb_3_9,

        R.drawable.thumb_4_0, R.drawable.thumb_4_1, R.drawable.thumb_4_2, R.drawable.thumb_4_3,
        R.drawable.thumb_4_4, R.drawable.thumb_4_5, R.drawable.thumb_4_6, R.drawable.thumb_4_7,
        R.drawable.thumb_4_8, R.drawable.thumb_4_9,

        R.drawable.thumb_5_0, R.drawable.thumb_5_1, R.drawable.thumb_5_2, R.drawable.thumb_5_3,
        R.drawable.thumb_5_4, R.drawable.thumb_5_5, R.drawable.thumb_5_6, R.drawable.thumb_5_7,
        R.drawable.thumb_5_8, R.drawable.thumb_5_9,

        R.drawable.thumb_6_0, R.drawable.thumb_6_1, R.drawable.thumb_6_2, R.drawable.thumb_6_3,
        R.drawable.thumb_6_4, R.drawable.thumb_6_5, R.drawable.thumb_6_6, R.drawable.thumb_6_7,
        R.drawable.thumb_6_8, R.drawable.thumb_6_9,

        R.drawable.thumb_7_0, R.drawable.thumb_7_1, R.drawable.thumb_7_2, R.drawable.thumb_7_3,
        R.drawable.thumb_7_4)

        tumManzaralar = ArrayList(tumResimler.size)

        for(i in 0 until tumResimler.size)
        {
            var manzara = Manzara("Manzara $i","Açıklama $i",tumResimler[i])
            tumManzaralar.add(manzara)
        }
    }
}
