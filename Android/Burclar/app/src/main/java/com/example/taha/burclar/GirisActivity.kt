package com.example.taha.burclar

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_giris.*

class GirisActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_giris)
        ivLogo.animation = AnimationUtils.loadAnimation(this,R.anim.sagadondur)

    }

    override  fun onResume() {
        super.onResume()
        goToMainActivity()
    }

    fun goToMainActivity(){
        object : CountDownTimer(5000,1000){
            override fun onFinish() {
                var intent = Intent(this@GirisActivity,MainActivity::class.java)
                startActivity(intent)
            }

            override fun onTick(millisUntilFinished: Long) {

            }

        }.start()
    }
}
