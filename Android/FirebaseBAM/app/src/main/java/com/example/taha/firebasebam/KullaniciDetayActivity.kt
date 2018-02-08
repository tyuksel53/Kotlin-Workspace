package com.example.taha.firebasebam

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_kullanici_detay.*

class KullaniciDetayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kullanici_detay)

        edUpdateEmail.setText(FirebaseAuth.getInstance().currentUser?.email.toString())
        edUpdateKullaniciAdi.setText("Kullanıcı adi tanımlanmadı")

        btnUpdateSifre.setOnClickListener {
            
        }

    }
}
