package com.example.taha.firebasebam

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
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
                        Toast.makeText(this,"Kullanici Eklendi",Toast.LENGTH_LONG).show()
                        onayMailGonder()
                        FirebaseAuth.getInstance().signOut()
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
}
