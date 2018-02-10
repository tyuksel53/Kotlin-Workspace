package com.example.taha.firebasebam

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {

    var zundi = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnKayitOl.setOnClickListener {
            btnKayitOl.isEnabled = false
            if(edEmail.text.isNotEmpty() && edPassword.text.isNotEmpty() && edPasswordConfirm.text.isNotEmpty())
            {
                var emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)
                var checkEmail = emailPattern.matcher(edEmail.text)
                if(checkEmail.find())
                {
                    if(edPasswordConfirm.text.toString() == edPassword.text.toString())
                    {
                        yeniKayitOlustur(edEmail.text.toString(),edPasswordConfirm.text.toString())

                    }else
                    {
                        Toast.makeText(this,"Şifreler eşleşmiyor",Toast.LENGTH_LONG).show()
                    }
                }else
                {
                    Toast.makeText(this,"Lütfen düzgün formatta mail adresi girin",Toast.LENGTH_LONG).show()
                }


            }else
            {
                Toast.makeText(this,"Lütfen Bütün alanları doldurun",Toast.LENGTH_LONG).show()
            }
            btnKayitOl.isEnabled = true
        }


    }

    private fun yeniKayitOlustur(email: String, password: String) {

        progressBarGoster()
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener {

                    progressBarGizle()
                    if(it.isSuccessful)
                    {
                        onayMailGonder()
                        veritabaninaKaydet()
                        FirebaseAuth.getInstance().signOut()
                        var intent = Intent(this@RegisterActivity,LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }else
                    {
                        Toast.makeText(this,"Bir seyler ters gitti. Hata ${it.exception?.message}",Toast.LENGTH_LONG).show()
                    }

                }
    }

    private fun onayMailGonder(){
        var kullanici = FirebaseAuth.getInstance().currentUser
        if( kullanici != null)
        {
            kullanici.sendEmailVerification().addOnCompleteListener {
                if(it.isSuccessful) {
                    Toast.makeText(this@RegisterActivity,"Hesabınızı aktifleştirmek için size mail gönderdik.",Toast.LENGTH_LONG).show()
                }else {
                    Toast.makeText(this@RegisterActivity,"Sıçtı : ${it.exception?.message}",Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun progressBarGoster()
    {
        pbNewUser.visibility = View.VISIBLE
    }
    private fun progressBarGizle()
    {
        pbNewUser.visibility = View.INVISIBLE
    }

    private fun veritabaninaKaydet(){
        var yeniKullanici = Kullanici()
        yeniKullanici.isim = edEmail.text.toString().substring(0,edEmail.text.toString().indexOf("@"))
        yeniKullanici.kullanici_Id = FirebaseAuth.getInstance()?.uid
        yeniKullanici.profil_resim = ""
        yeniKullanici.telefon = "123"
        yeniKullanici.seviye = 1

        FirebaseDatabase.getInstance().reference
                .child("kullanici")
                .child(FirebaseAuth.getInstance().currentUser?.uid)
                .setValue(yeniKullanici)
                .addOnCompleteListener {
                    if(it.isSuccessful)
                    {
                        Toast.makeText(this@RegisterActivity,"Kullanici eklendi",Toast.LENGTH_LONG).show()
                    }else
                    {

                    }
                }
    }

}
