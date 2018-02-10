package com.example.taha.firebasebam

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_kullanici_detay.*

class KullaniciDetayActivity : AppCompatActivity() , ProfilResimFragment.onProfilResimListener {


    var isPermissionGived = false


    override fun getResimYol(resimPath: Uri?) {

    }

    override fun resimBitmap(bitmap: Bitmap?) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kullanici_detay)

        var currentUser = FirebaseAuth.getInstance().currentUser!!
        var db = FirebaseDatabase.getInstance().reference
        edUpdateCurrentPass.setText("")
        db.child("kullanici").child(currentUser.uid).child("isim")
                .addValueEventListener(object:ValueEventListener{
                    override fun onCancelled(p0: DatabaseError?) {

                    }

                    override fun onDataChange(p0: DataSnapshot?) {
                        edUpdateKullaniciAdi.setText(p0?.value.toString())
                    }

                })
        db.child("kullanici").child(currentUser.uid).child("telefon")
                .addValueEventListener(object:ValueEventListener{
                    override fun onCancelled(p0: DatabaseError?) {

                    }

                    override fun onDataChange(p0: DataSnapshot?) {
                        edUpdatePhone.setText(p0?.value.toString())
                    }

                })

        tvUpdateMail.text = currentUser.email.toString()

        tvUpdateForgetPass.setOnClickListener {

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

            if(edUpdateKullaniciAdi.text.isNotEmpty() && edUpdatePhone.text.isNotEmpty())
            {
                var bilgileriGuncelle = UserProfileChangeRequest.Builder()
                        .setDisplayName(edUpdateKullaniciAdi.text.toString())
                        .build()

                currentUser.updateProfile(bilgileriGuncelle)
                        .addOnCompleteListener {
                            if(it.isSuccessful)
                            {
                                Toast.makeText(this@KullaniciDetayActivity,"Bilgiler Güncellendi",Toast.LENGTH_LONG).show()
                                FirebaseDatabase.getInstance().reference
                                        .child("kullanici")
                                        .child(currentUser.uid.toString())
                                        .child("isim").setValue(edUpdateKullaniciAdi.text.toString())

                                FirebaseDatabase.getInstance().reference
                                        .child("kullanici")
                                        .child(currentUser.uid)
                                        .child("telefon").setValue(edUpdatePhone.text.toString())
                            }else
                            {
                                Toast.makeText(this@KullaniciDetayActivity,"Sıçtı",Toast.LENGTH_LONG).show()
                            }
                        }

            }else
            {
                Toast.makeText(this@KullaniciDetayActivity,"WOWOWOWOWWO bütün alanları doldur",Toast.LENGTH_LONG).show()
            }

        }

        tvUpdatePassAndMail.setOnClickListener {

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

        ivUpdateResim.setOnClickListener {

            if(isPermissionGived)
            {
                var fragment = ProfilResimFragment()
                fragment.show(supportFragmentManager,"fotoseç")
            }else
            {
                izinleriIste()
            }

        }
    }

    private fun izinleriIste() {

        var izinler = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE
        ,android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.CAMERA)

        if(ContextCompat.checkSelfPermission(this,izinler[0]) == PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(this,izinler[1]) == PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(this,izinler[2]) == PackageManager.PERMISSION_GRANTED)
        {
            isPermissionGived = true
        }else
        {
            ActivityCompat.requestPermissions(this,izinler,150)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        if(requestCode == 150)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED
                && grantResults[1] == PackageManager.PERMISSION_GRANTED
                && grantResults[2] == PackageManager.PERMISSION_GRANTED)
            {
                var fragment = ProfilResimFragment()
                fragment.show(supportFragmentManager,"fotoseç")
            }else
            {
                Toast.makeText(this,"Tüm izinleri ver lan",Toast.LENGTH_LONG).show()
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
