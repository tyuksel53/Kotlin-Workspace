package com.example.taha.firebasebam

import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.android.synthetic.main.activity_kullanici_detay.*

class KullaniciDetayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kullanici_detay)

        var currentUser = FirebaseAuth.getInstance().currentUser!!

        edUpdateCurrentPass.setText("")
        edUpdateKullaniciAdi.setText(currentUser.displayName.toString())

        btnUpdateSifre.setOnClickListener {

            if(currentUser != null)
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

            if(edUpdateKullaniciAdi.text.isNotEmpty())
            {
                if(edUpdateKullaniciAdi.text.toString() != currentUser?.displayName.toString())
                {
                    var bilgileriGuncelle = UserProfileChangeRequest.Builder()
                            .setDisplayName(edUpdateKullaniciAdi.text.toString())
                            .build()

                    currentUser.updateProfile(bilgileriGuncelle)
                            .addOnCompleteListener {
                                if(it.isSuccessful)
                                {
                                    Toast.makeText(this@KullaniciDetayActivity,"Bilgiler Güncellendi",Toast.LENGTH_LONG).show()
                                }else
                                {
                                    Toast.makeText(this@KullaniciDetayActivity,"Sıçtı",Toast.LENGTH_LONG).show()
                                }
                            }
                }

            }else
            {
                Toast.makeText(this@KullaniciDetayActivity,"WOWOWOWOWWO bütün alanları doldur",Toast.LENGTH_LONG).show()
            }

        }

        btnUpdateMailAndPassword.setOnClickListener {

            if(edUpdateCurrentPass.text.isNotEmpty())
            {

                var credential = EmailAuthProvider.getCredential(currentUser.email.toString(),edUpdateCurrentPass.text.toString())
                currentUser.reauthenticate(credential)
                        .addOnCompleteListener {
                            if(it.isSuccessful)
                            {
                                clUpdateArea.visibility = View.VISIBLE

                                btnUpdateMail.setOnClickListener {
                                    mailGuncelle()
                                }

                                btnUpdatePass.setOnClickListener {
                                    sifreGuncelle()
                                }

                            }else
                            {
                                Toast.makeText(this@KullaniciDetayActivity,"Suanki şifreniz yanlıştır",Toast.LENGTH_LONG).show()
                                clUpdateArea.visibility = View.INVISIBLE
                            }
                        }

            }else
            {
                Toast.makeText(this,"Şuanki şifreni girin",Toast.LENGTH_LONG).show()
            }

        }
    }

    private fun mailGuncelle() {

        val kullanici = FirebaseAuth.getInstance().currentUser

        if(kullanici != null)
        {
            FirebaseAuth.getInstance().fetchProvidersForEmail(edUpdateMail.text.toString())
                    .addOnCompleteListener{

                        if(it.isSuccessful)
                        {
                            if(it.result.providers?.size == 1)
                            {
                                mesajGoster(this@KullaniciDetayActivity,"Var olan email girdiniz")
                            }else
                            {
                                kullanici.updateEmail(edUpdateMail.text.toString())
                                        .addOnCompleteListener {
                                            if(it.isSuccessful)
                                            {
                                                mesajGoster(this@KullaniciDetayActivity,"Mail değiştirildi")
                                                var intent = Intent(this@KullaniciDetayActivity,LoginActivity::class.java)
                                                FirebaseAuth.getInstance().signOut()
                                                startActivity(intent)
                                                finish()
                                            }else
                                            {
                                                mesajGoster(this@KullaniciDetayActivity,"Sıçtı")
                                            }
                                        }
                            }
                        }

                    }
        }

    }

    private fun sifreGuncelle() {

        val kullanici = FirebaseAuth.getInstance().currentUser

        if(kullanici != null)
        {
            kullanici.updatePassword(edUpdatePass.text.toString())
                    .addOnCompleteListener {
                        if(it.isSuccessful)
                        {
                            mesajGoster(this@KullaniciDetayActivity,"Şifre Güncellendi")
                        }
                    }
        }

    }

    private fun mesajGoster(context:Context,mesaj:String,isDurationLong:Boolean = true)
    {
        if(isDurationLong)
        {
            Toast.makeText(context,mesaj,Toast.LENGTH_LONG).show()
        }else
        {
            Toast.makeText(context,mesaj,Toast.LENGTH_SHORT).show()
        }

    }
}
