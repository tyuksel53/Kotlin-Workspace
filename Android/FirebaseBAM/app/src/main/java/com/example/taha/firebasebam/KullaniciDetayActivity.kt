package com.example.taha.firebasebam

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_kullanici_detay.*

class KullaniciDetayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kullanici_detay)

        edUpdateEmail.setText(FirebaseAuth.getInstance().currentUser?.email.toString())
        edUpdateKullaniciAdi.setText(FirebaseAuth.getInstance().currentUser?.displayName.toString())

        btnUpdateSifre.setOnClickListener {
            var kullanici = FirebaseAuth.getInstance().currentUser

            if(kullanici != null)
            {
                FirebaseAuth.getInstance()
                        .sendPasswordResetEmail(FirebaseAuth.getInstance().currentUser?.email.toString())
                        .addOnCompleteListener {
                            if(it.isSuccessful)
                            {
                                Toast.makeText(this@KullaniciDetayActivity,"Şifre sıfırlama maili göderildi", Toast.LENGTH_LONG).show()
                            }else
                            {
                                Toast.makeText(this@KullaniciDetayActivity,"Sıçtı", Toast.LENGTH_LONG).show()
                            }
                        }

            }

        }

        btnUpdateSaveChanges.setOnClickListener {

            if(edUpdateEmail.text.isNotEmpty() && edUpdateKullaniciAdi.text.isNotEmpty())
            {
                var currentUser = FirebaseAuth.getInstance().currentUser
                if(edUpdateKullaniciAdi.text.equals(currentUser?.displayName))
                {

                }

            }else
            {
                Toast.makeText(this@KullaniciDetayActivity,"WOWOWOWOWWO bütün alanları doldur",Toast.LENGTH_LONG).show()
            }

        }

    }
}
