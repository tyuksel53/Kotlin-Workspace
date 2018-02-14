package com.example.taha.firebasebam

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_sohbet.*

class SohbetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sohbet)


        fabYeniSohbet.setOnClickListener {

            var dialog = YeniSohbetFragment()
            dialog.show(supportFragmentManager,"yeni sohbet")

        }

    }
}
