package com.example.taha.myapp

import android.content.res.Configuration
import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.deneme.*
class MainActivity : AppCompatActivity() {

    var myVariable:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.deneme) /* activity layout ayarlama */


    }
}
