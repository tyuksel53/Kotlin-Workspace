package com.example.taha.firebasebam

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var myAutListener: FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initMyAutListener()
        tvKayitOl.setOnClickListener {

            var intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)

        }

        btnLogin.setOnClickListener {

            if (edLoginEmail.text.isNotEmpty() && edLoginPassword.text.isNotEmpty()) {
                pbLogin.visibility = View.VISIBLE
                FirebaseAuth.getInstance().signInWithEmailAndPassword(edLoginEmail.text.toString(), edLoginPassword.text.toString())
                        .addOnCompleteListener {
                            pbLogin.visibility = View.INVISIBLE
                            if (it.isSuccessful) {

                                if(it.result.user.isEmailVerified)
                                {

                                }else
                                {
                                    FirebaseAuth.getInstance().signOut()
                                }

                            } else {
                                Toast.makeText(this, "Bir hata oldu. Hata: ${it.exception?.message}", Toast.LENGTH_LONG).show()
                            }
                        }

            } else {
                Toast.makeText(this@LoginActivity, "Lütfen bos alanları doldurun", Toast.LENGTH_LONG).show()
            }

        }
        tvOnayMail.setOnClickListener {
            var dialogGoser = OnayMailDialog()
            dialogGoser.show(supportFragmentManager,"gosterDialog")
        }

        tvSifremiUnuttum.setOnClickListener {
            var sifreDialog = SifremiUnuttumDialogFragment()
            sifreDialog.show(supportFragmentManager,"sifreDialog")
        }

    }

    override fun onStart() {
        super.onStart()
        FirebaseAuth.getInstance().addAuthStateListener(myAutListener)
    }

    override fun onStop() {
        super.onStop()
        FirebaseAuth.getInstance().removeAuthStateListener(myAutListener)
    }

    private fun initMyAutListener() {
        myAutListener = object : FirebaseAuth.AuthStateListener {
            override fun onAuthStateChanged(p0: FirebaseAuth) {
                var kullanici = p0.currentUser
                if(kullanici != null)
                {
                    if(kullanici.isEmailVerified)
                    {
                        Toast.makeText(this@LoginActivity,"Mail Onaylanmış. Giriş  yapabilir",Toast.LENGTH_LONG).show()
                        var intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()

                    }else
                    {
                        Toast.makeText(this@LoginActivity,"Mail Adresinizi Doğrulayınız",Toast.LENGTH_LONG).show()
                    }
                }

            }

        }
    }

}
