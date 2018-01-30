package com.example.taha.ortalamahesaplama

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.yeni_ders_layout.view.*
import es.dmoral.toasty.Toasty


class MainActivity : AppCompatActivity() {

    private val DERSLER = arrayOf("Gezgin Robotlara Giriş","Proje Yönetimi ve İş Güvenliği",
            "Yapay Zeka","Programalama Dilleri Prensipleri", "Bilgisayar Ağlarında Güvenlik",
            "Sayısal Video Temelleri","USD")

    private var tumDersler = arrayListOf<Dersler>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hesaplaVisibility()

        var adapter = ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,DERSLER)

        edDersAd.setAdapter(adapter)

        btnDersEkle.setOnClickListener {

            if(!edDersAd.text.isNullOrBlank())
            {

                var inflater = LayoutInflater.from(this)
                /*var inflater2 = getService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                */

                var yeniDersView = inflater.inflate(R.layout.yeni_ders_layout,null)

                yeniDersView.yeniEdDersAd.setText(edDersAd.text.toString())
                yeniDersView.yeniEdDersAd.setAdapter(adapter)

                yeniDersView.yeniSpnDersKredi.setSelection(spnDersKredi.selectedItemPosition)
                yeniDersView.yeniSpnDersNotu.setSelection(spnDersNotu.selectedItemPosition)

                yeniDersView.btnDersEkleSil.setOnClickListener {

                    scLinearLayout.removeView(yeniDersView)
                    hesaplaVisibility()
                }

                scLinearLayout.addView(yeniDersView)
                btnHesapla.visibility = View.VISIBLE
                sifirla()
                Toasty.success(this,"Ders Eklendi",Toast.LENGTH_SHORT).show()
            }else
                Toasty.error(this,"Ders Adı bos olamaz",Toast.LENGTH_SHORT).show()


        }

    }


    fun spinnerElemanBul(spinner:Spinner,query:String):Int{

        var index = 0
        for(i in 0..spinner.count)
        {
            if(spinner.getItemAtPosition(i).toString() == query)
            {
                index = i
                break
            }
        }

        return index

    }

    fun hesaplaVisibility(){

        if(scLinearLayout.childCount == 0)
        {
            btnHesapla.visibility = View.INVISIBLE
        }else
        {
            btnHesapla.visibility = View.VISIBLE
        }

    }

    fun sifirla()
    {
        edDersAd.setText("")
        spnDersKredi.setSelection(0)
        spnDersNotu.setSelection(0)
    }
    fun ortalamaHesapla(v: View)
    {
        for(i in 0 until scLinearLayout.childCount)
        {
            var satir = scLinearLayout.getChildAt(i)
            var ders = Dersler(dersAdi = satir.yeniEdDersAd.text.toString(),
                    dersKredi = satir.yeniSpnDersKredi.selectedItem.toString(),
                    dersNot = satir.yeniSpnDersNotu.selectedItem.toString())
            tumDersler.add(ders)
        }

        var krediSayisi = 0.0
        var notlarToplam = 0.0

        for(i in tumDersler)
        {
            krediSayisi += i.dersKredi!!.toDouble()
            notlarToplam += i.dersNot!!.toDouble() * i.dersKredi!!.toDouble()
        }
        Toasty.info(this,"Ortalama: ${notlarToplam / krediSayisi}",Toast.LENGTH_SHORT).show()
        tumDersler.clear()
    }
}
