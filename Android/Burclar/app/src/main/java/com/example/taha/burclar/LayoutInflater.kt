package com.example.taha.burclar

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.activity_layout_inflater.*

class LayoutInflater : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_inflater)

        var inflater = layoutInflater
       /* *//*var inflater1 = LayoutInflater.from(this)
        var inflater2 = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        *//*

        var view = inflater.inflate(R.layout.layout_text_view,null)

        icerdekiLayout.addView(view)

        Log.e("INFLATION","${view.parent}")
        Log.e("INFLATION","${view.layoutParams}")
        */

        var view = inflater.inflate(R.layout.layout_text_view,rootLayout)
        Log.e("INFLATION","${view.parent}")
        Log.e("INFLATION","${view.layoutParams}")



    }
}
