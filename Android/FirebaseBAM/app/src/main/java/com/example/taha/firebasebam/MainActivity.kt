package com.example.taha.firebasebam

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var myAuthListener : FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initMyAuthListener()

        var currentUser = FirebaseAuth.getInstance().currentUser

        tvEposta.text = currentUser?.email.toString()
        tvUid.text = currentUser?.uid
        tvKullaniciAdi.text = currentUser?.displayName.toString()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.anamenu,menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId)
        {
            R.id.menuCikisYap -> cikisYap()
            R.id.menuHesapBilgileri -> {
                var intent = Intent(this@MainActivity,KullaniciDetayActivity::class.java)
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun cikisYap() {

        FirebaseAuth.getInstance().signOut()
        var intent = Intent(this@MainActivity,LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()

    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onResume() {
        super.onResume()

        var user = FirebaseAuth.getInstance().currentUser
        if(user == null)
        {
            cikisYap()
        }

    }

    private fun initMyAuthListener()
    {
        myAuthListener = object : FirebaseAuth.AuthStateListener {
            override fun onAuthStateChanged(p0: FirebaseAuth) {

                var kullanici = p0.currentUser
                if(kullanici != null)
                {

                }else
                {
                    cikisYap()
                }
            }

        }
    }

}
