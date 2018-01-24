package com.example.taha.gezegendekackioyum

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity() , View.OnClickListener {

    val kiloToPound = 2.2045
    val MARS = 0.38
    val VENUS = 0.91
    val JUPITER = 2.34
    val POUND_TO_KILO = 0.45359237

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*btnHesapla.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                Log.e("Taha","Buttona Basildi")
            }
        })*/

        tvSonuc.text = savedInstanceState?.getString("sonuc")

        Glide.with(this).load(R.drawable.title).into(imageView)

        cbVenus.setOnClickListener(this) /* o anki main activity*/
        cbJupiter.setOnClickListener(this)
        cbMars.setOnClickListener(this)


        /*btnHesapla.setOnClickListener {
            var tmp = edKilo.text.toString()
            var kullaniciAgirlik = tmp.toDoubleOrNull()
            if(kullaniciAgirlik != null)
            {
                var marsdakiAgirlik = poundToKilo(kullaniciAgirlik * MARS)
                tvSonuc.text = "${marsdakiAgirlik.formatla(2)} KG"
            }else
            {
                Toast.makeText(this,"Bir kilo girin",Toast.LENGTH_LONG).show()
            }
        }*/



    }

    fun kiloToPound(kilo:Double):Double{


        return kilo.toDouble() * kiloToPound
    }

    override  fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString("sonuc",tvSonuc.text.toString())
    }
    fun poundToKilo(pound:Double):Double{

        return  pound * POUND_TO_KILO
    }
    fun Double.formatla(kacTaneBasamak:Int) = java.lang.String.format("%.${kacTaneBasamak}f",this)

    override fun onClick(v: View?) {

        v as CheckBox
        var isCheck = v.isChecked
        when(v.id)
        {
            R.id.cbVenus -> if (  isCheck && !TextUtils.isEmpty(edKilo.toString()))
            {
                cbMars.isChecked = false
                cbJupiter.isChecked = false
                hesaplaAgirlikPound(edKilo.text.toString().toDoubleOrNull(),v)
            }
            R.id.cbMars -> if (  isCheck && !TextUtils.isEmpty(edKilo.toString()))
            {
                cbVenus.isChecked = false
                cbJupiter.isChecked = false
                hesaplaAgirlikPound(edKilo.text.toString().toDoubleOrNull(),v)
            }
            R.id.cbJupiter ->if (  isCheck && !TextUtils.isEmpty(edKilo.toString()))
            {
                cbMars.isChecked = false
                cbVenus.isChecked = false
                hesaplaAgirlikPound(edKilo.text.toString().toDoubleOrNull(),v)
            }
        }
    }
    fun hesaplaAgirlikPound(pound:Double?,checkbox:CheckBox)
    {
        var sonuc = 0.0
        if(pound != null)
        {
            sonuc = when(checkbox.id)
            {
                R.id.cbJupiter -> pound * JUPITER
                R.id.cbVenus -> pound * VENUS
                R.id.cbMars -> pound * MARS
                else -> 0.0
            }
        }else
        {
            Toast.makeText(this,"Deger gir lan",Toast.LENGTH_SHORT).show()
        }


        tvSonuc.text = "Kilonuz: ${kiloToPound(sonuc).formatla(2)}"
    }
}