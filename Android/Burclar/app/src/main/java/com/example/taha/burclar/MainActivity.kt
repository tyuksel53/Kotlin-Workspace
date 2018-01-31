package com.example.taha.burclar

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var tumBurcBilgileri:ArrayList<Burc>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = this.resources.getColor(R.color.colorStatusBar)
        }

        /*val MY_BURCLAR = resources.getStringArray(R.array.burclar)

        val MY_TARIHLER = resources.getStringArray(R.array.burcTarih)

        val MY_IMGS = arrayOf(R.drawable.koc1,R.drawable.boga2,R.drawable.ikizler3,R.drawable.yengec4,
                R.drawable.aslan5,R.drawable.basak6,R.drawable.terazi7,R.drawable.akrep8,R.drawable.yay9,
                R.drawable.oglak10,R.drawable.kova11,R.drawable.balik12)*/

        /*var adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,BURCLAR)
*/      /*var adapter = BurclarArrayAdapter(this,
                R.layout.teksatir,R.id.tvBurcAdi,MY_BURCLAR,MY_TARIHLER,MY_IMGS)*/

        createDataSource()
        var adapter = BurclarBaseAdaptor(this,tumBurcBilgileri)
        listBurclar.adapter = adapter


        /*listBurclar.setOnItemClickListener(object:AdapterView.OnItemClickListener{
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                var gecici = view as TextView  *//* gelen viewin ne olduğunu bilmiyoruz bu yüzden casting *//*

                Toast.makeText(this@MainActivity,"Tıklanıldı -> ${view.text}" +
                        " Position: $position",Toast.LENGTH_SHORT).show()
            }

        })*/
        /*listBurclar.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            Toast.makeText(this,"Bastin",Toast.LENGTH_SHORT).show()
        }*/


        listBurclar.setOnItemClickListener { parent, view, position, id ->

           /* var gecici = view as TextView   gelen viewin ne olduğunu bilmiyoruz bu yüzden casting

            Toast.makeText(this@MainActivity,"Tıklanıldı -> ${view.text}" +
                    " Position: $position",Toast.LENGTH_SHORT).show()*/
            var intent = Intent(this@MainActivity,DetayActivity::class.java)
            intent.putExtra("position",position)
            intent.putExtra("detay",tumBurcBilgileri[position] )
            startActivity(intent)

        }


    }

    private fun createDataSource()
    {
        tumBurcBilgileri = ArrayList<Burc>(12)

        var burcIsimler = this.resources.getStringArray(R.array.burclar)
        var burcTarihler = this.resources.getStringArray(R.array.burcTarih)

        var burcResimler = arrayOf(R.drawable.koc1,R.drawable.boga2,R.drawable.ikizler3,R.drawable.yengec4,
                R.drawable.aslan5,R.drawable.basak6,R.drawable.terazi7,R.drawable.akrep8,R.drawable.yay9,
                R.drawable.oglak10,R.drawable.kova11,R.drawable.balik12)
        var burcBuyukResimler = arrayOf(R.drawable.koc_buyuk1,R.drawable.boga_buyuk2,R.drawable.ikizler_buyuk3,
                R.drawable.yengec_buyuk4, R.drawable.aslan_buyuk5,R.drawable.basak_buyuk6,R.drawable.terazi_buyuk7,
                R.drawable.akrep_buyuk8,R.drawable.yay_buyuk9, R.drawable.oglak_buyuk10,R.drawable.kova_buyuk11,
                R.drawable.balik_buyuk12)

        var burcAciklama = this.resources.getStringArray(R.array.burcGenelOzellikler)

        for(i in 0..11)
        {
            var tmp = Burc(burcAdi = burcIsimler[i],burcResmi = burcResimler[i] , burcTarihi = burcTarihler[i],
                    burcBilgi = burcAciklama[i],burcBuyukResim = burcBuyukResimler[i])

            tumBurcBilgileri.add(tmp)
        }


    }
}
