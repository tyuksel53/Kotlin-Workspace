package com.example.taha.firebasebam

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.taha.firebasebam.model.SohbetOdası
import com.google.firebase.database.FirebaseDatabase

class SohbetOdasiActivity : AppCompatActivity() {

    lateinit var currentChatRoom:SohbetOdası
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sohbet_odasi)
        mesajlariGetir()
        supportActionBar?.title = currentChatRoom.sohbetOdasiAdi.toString()
        supportActionBar?.subtitle = "Toplam Mesaj: ${currentChatRoom.sohbetOdasiMesajlari?.size}"
    }

    private fun mesajlariGetir() {

        currentChatRoom = intent.getSerializableExtra("sohbetOdasiId") as SohbetOdası


    }
}
