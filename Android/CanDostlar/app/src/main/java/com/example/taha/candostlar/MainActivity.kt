package com.example.taha.candostlar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.support.v7.widget.StaggeredGridLayoutManager
import com.example.taha.candostlar.Library.DostAdaptor
import com.example.taha.candostlar.Library.Dostlar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()/*, android.widget.SearchView.OnQueryTextListener*/ {

    var tumDostlar = ArrayList<Dostlar>()
    lateinit var myAdaptor:DostAdaptor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fillDataSource()

        myAdaptor = DostAdaptor(tumDostlar)

        recyclerDostlar.adapter = myAdaptor
        var layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        recyclerDostlar.layoutManager = layoutManager

        aramaView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                myAdaptor.myFilter.filter(newText)

                return false
            }

        })



    }


    fun fillDataSource()
    {
        var resimler = arrayOf(R.drawable.ani_cat_one,
                R.drawable.ani_cat_two,
                R.drawable.ani_cat_three,
                R.drawable.ani_cat_four,
                R.drawable.ani_cat_five,
                R.drawable.ani_cat_six,
                R.drawable.ani_cat_seven,

                R.drawable.ani_dog_one,
                R.drawable.ani_dog_two,
                R.drawable.ani_dog_three,
                R.drawable.ani_dog_four,
                R.drawable.ani_dog_five,

                R.drawable.ani_deer_one,
                R.drawable.ani_deer_two,
                R.drawable.ani_deer_three,
                R.drawable.ani_deer_four,

                R.drawable.bird_parrot_one,
                R.drawable.bird_parrot_two,
                R.drawable.bird_parrot_three,
                R.drawable.bird_parrot_four,
                R.drawable.bird_parrot_five,
                R.drawable.bird_parrot_six,
                R.drawable.bird_parrot_seven,
                R.drawable.bird_parrot_eight)

        var isimler = arrayOf("Kedi 1", "Kedi 2", "Kedi 3", "Kedi 4", "Kedi 5", "Kedi 6", "Kedi 7",
                "Köpek 1", "Köpek 2", "Köpek 3", "Köpek 4", "Köpek 5",
                "Geyik 1", "Geyik 2", "Geyik 3", "Geyik 4",
                " Papagan 1", " Papagan 2", " Papagan 3", " Papagan 4", " Papagan 5", " Papagan 6", " Papagan 7", " Papagan 8")

        for (i in 0 until resimler.size) {

            var eklenecekDost = Dostlar(isimler[i], resimler[i])
            tumDostlar.add(eklenecekDost)

        }
    }

/*    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.anamenu,menu)

        var itemId = menu?.findItem(R.id.app_bar_search)

        var searchView = itemId?.actionView as android.widget.SearchView

        searchView.setOnQueryTextListener(this)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {

        var text = newText!!.toLowerCase()

        val query = tumDostlar.filter {  it.isim.toLowerCase().contains(text)}

        myAdaptor.setFilter(query as ArrayList<Dostlar>)


        return true
    }*/

}
