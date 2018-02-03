package com.example.taha.candostlar

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.health.TimerStat

class girisActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_giris)
    }

    override fun onResume() {
        super.onResume()

        object:CountDownTimer(1000,1000){
            override fun onFinish() {
                var intent = Intent(this@girisActivity,MainActivity::class.java)
                startActivity(intent)
            }

            override fun onTick(millisUntilFinished: Long) {

            }

        }.start()
    }
}
