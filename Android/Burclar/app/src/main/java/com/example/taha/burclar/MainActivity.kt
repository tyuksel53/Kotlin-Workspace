package com.example.taha.burclar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val BURCLAR = arrayOf("Koç","Boğa","İkizler","Yengeç","Aslan","Başak","Terazi",
            "Akrep","Yay","Oğlak","Kova","Balık")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val MY_BURCLAR = resources.getStringArray(R.array.burclar)

        val MY_TARIHLER = resources.getStringArray(R.array.burcTarih)

        val MY_IMGS = arrayOf(R.drawable.koc1,R.drawable.boga2,R.drawable.ikizler3,R.drawable.yengec4,
                R.drawable.aslan5,R.drawable.basak6,R.drawable.terazi7,R.drawable.akrep8,R.drawable.yay9,
                R.drawable.oglak10,R.drawable.kova11,R.drawable.balik12)*/

        /*var adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,BURCLAR)
*/      /*var adapter = BurclarArrayAdapter(this,
                R.layout.teksatir,R.id.tvBurcAdi,MY_BURCLAR,MY_TARIHLER,MY_IMGS)*/

        var adapter = BurclarBaseAdaptor(this)
        listBurclar.adapter = adapter

        /*listBurclar.setOnItemClickListener(object:AdapterView.OnItemClickListener{
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                var gecici = view as TextView  *//* gelen viewin ne olduğunu bilmiyoruz bu yüzden casting *//*

                Toast.makeText(this@MainActivity,"Tıklanıldı -> ${view.text}" +
                        " Position: $position",Toast.LENGTH_SHORT).show()
            }

        })*/

        listBurclar.setOnItemClickListener { parent, view, position, id ->

            /*var gecici = view as TextView  *//* gelen viewin ne olduğunu bilmiyoruz bu yüzden casting *//*

            Toast.makeText(this@MainActivity,"Tıklanıldı -> ${view.text}" +
                    " Position: $position",Toast.LENGTH_SHORT).show()*/

        }


    }
}