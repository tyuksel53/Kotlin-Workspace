package com.example.taha.ortalamahesaplama

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash.*

class ActivitySplash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        var gelenButton = AnimationUtils.loadAnimation(this,R.anim.frombottombutton)
        var gelenBalon = AnimationUtils.loadAnimation(this,R.anim.fromtopbalon)
        var gidenBalon = AnimationUtils.loadAnimation(this,R.anim.gohomebalon)
        var gidenButton = AnimationUtils.loadAnimation(this,R.anim.gohomebutton)
        btnGiris.animation = gelenButton
        balon.animation = gelenBalon


        btnGiris.setOnClickListener {

            btnGiris.startAnimation(gidenButton)
            balon.startAnimation(gidenBalon)

            object : CountDownTimer(1000,1000){
                override fun onTick(millisUntilFinished: Long) {

                }

                override fun onFinish() {
                    var intent = Intent(applicationContext,MainActivity::class.java)
                    startActivity(intent)
                }

            }.start()




        }
    }
}
