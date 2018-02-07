package com.example.taha.firebasebam

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tvKayitOl.setOnClickListener {

            var intent = Intent(this@LoginActivity,RegisterActivity::class.java)
            startActivity(intent)

        }

        btnLogin.setOnClickListener {

            if(edLoginEmail.text.isNotEmpty() && edLoginPassword.text.isNotEmpty())
            {
                pbLogin.visibility = View.VISIBLE
                FirebaseAuth.getInstance().signInWithEmailAndPassword(edLoginEmail.text.toString(),edLoginPassword.text.toString())
                        .addOnCompleteListener {
                            pbLogin.visibility = View.INVISIBLE
                            if(it.isSuccessful)
                            {
                                var intent = Intent(this@LoginActivity,MainActivity::class.java)
                                startActivity(intent)
                            }else
                            {
                                Toast.makeText(this,"Bir hata oldu. Hata: ${it.exception?.message}",Toast.LENGTH_LONG).show()
                            }
                        }

            }else
            {
                Toast.makeText(this@LoginActivity,"Lütfen bos alanları doldurun",Toast.LENGTH_LONG).show()
            }

        }

    }
}
