package com.example.taha.firebasebam

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.taha.firebasebam.Adapters.SohbetMesajlarAdapter
import com.example.taha.firebasebam.model.Kullanici
import com.example.taha.firebasebam.model.SohbetMesajlar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_sohbet_odasi.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashSet


class SohbetOdasiActivity : AppCompatActivity() {

    var currentRoomId:String? = null
    var mAuthListner:FirebaseAuth.AuthStateListener? = null
    var ref:DatabaseReference? =  null
    var mesajlar:ArrayList<SohbetMesajlar>? = null
    var myAdapter:SohbetMesajlarAdapter? = null
    var mesajKeyler = HashSet<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sohbet_odasi)

        startAuthListener()
        mesajlariGetir()
/*        supportActionBar?.title = currentChatRoom.sohbetOdasiAdi.toString()
        supportActionBar?.subtitle = "Toplam Mesaj: ${currentChatRoom.sohbetOdasiMesajlari?.size}"*/

       /* var myAdapter = SohbetMesajlarAdapter(this@SohbetOdasiActivity,currentChatRoom.sohbetOdasiMesajlari!!)

        recyclerSohbetMesajlar.adapter = myAdapter
        recyclerSohbetMesajlar.layoutManager = LinearLayoutManager(this@SohbetOdasiActivity,LinearLayoutManager.VERTICAL,false)*/


        init()


    }

    private fun init() {

        ivSendMessage.setOnClickListener {
            if( edSohbetMesaj.text.toString().isNotEmpty() )
            {
                var mesaj = edSohbetMesaj.text.toString()
                var saveMessage = SohbetMesajlar()
                saveMessage.mesaj = mesaj
                saveMessage.kullanici_id = FirebaseAuth.getInstance().currentUser?.uid
                saveMessage.timestamp = getDate()


                var sql = FirebaseDatabase.getInstance().reference.child("sohbetOdalari").child(currentRoomId)
                        .child("sohbetOdasiMesajlari")

                var yeniId = sql.push().key

                sql.child(yeniId).setValue(saveMessage)
            }
        }
    }

    private fun getDate():String
    {

        var sdf = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale("tr"))
        var date = sdf.format(Date())

        return date
    }

    private fun startAuthListener() {
        mAuthListner = object : FirebaseAuth.AuthStateListener{
            override fun onAuthStateChanged(p0: FirebaseAuth) {

                var currentUser = p0.currentUser
                if(currentUser == null)
                {
                    var intent = Intent(this@SohbetOdasiActivity,LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }

            }

        }
    }

    override fun onStart() {
        super.onStart()
        FirebaseAuth.getInstance().addAuthStateListener(mAuthListner!!)

    }

    override fun onStop() {
        super.onStop()
        if(mAuthListner != null)
        {
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthListner!!)
        }
    }

    override fun onResume() {
        super.onResume()
        checkUser()
    }

    private fun checkUser() {
        var user = FirebaseAuth.getInstance().currentUser
        if(user == null)
        {
            var intent = Intent(this@SohbetOdasiActivity,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun mesajlariGetir() {

        currentRoomId = intent.getStringExtra("sohbetOdasiId")
        başlatMesajListener()


    }

    var mValueEventListener= object:ValueEventListener{
        override fun onCancelled(p0: DatabaseError?) {

        }

        override fun onDataChange(p0: DataSnapshot?) {
            sohbetMesajlariGetir()
        }

    }

    private fun sohbetMesajlariGetir() {

        if(mesajlar == null)
        {
            mesajlar = ArrayList<SohbetMesajlar>()

        }

        ref = FirebaseDatabase.getInstance().reference

        var sorgu = ref?.child("sohbetOdalari")
                ?.child(currentRoomId)
                ?.child("sohbetOdasiMesajlari")
                ?.addListenerForSingleValueEvent(object:ValueEventListener{
                    override fun onCancelled(p0: DatabaseError?) {

                    }

                    override fun onDataChange(p0: DataSnapshot?) {

                        for(singleSnapshot in p0!!.children)
                        {
                            var gececi = SohbetMesajlar()
                            var mesaj = singleSnapshot.getValue(SohbetMesajlar::class.java)

                            if(mesaj?.kullanici_id != null)
                            {
                                if( !mesajKeyler.contains(singleSnapshot.key) )
                                {
                                    mesajKeyler.add(singleSnapshot.key)
                                    var userId = mesaj.kullanici_id
                                    ref?.child("kullanici")?.orderByKey()
                                            ?.equalTo(userId)?.addListenerForSingleValueEvent(object:ValueEventListener{
                                        override fun onCancelled(p0: DatabaseError?) {

                                        }

                                        override fun onDataChange(p0: DataSnapshot?) {
                                            var founedUser = p0!!.children.iterator()?.next()
                                            mesaj.profil_resim = founedUser.getValue(Kullanici::class.java)?.profil_resim
                                            mesaj.adi = founedUser.getValue(Kullanici::class.java)?.isim
                                            mesajlar?.add(mesaj)
                                            myAdapter?.notifyDataSetChanged()
                                            recyclerSohbetMesajlar.scrollToPosition(mesajlar!!.size-1)
                                            if(myAdapter == null)
                                            {
                                                initMesajlar()
                                            }
                                        }

                                    })
                                }
                            }else
                            {
                                if(!mesajKeyler.contains(singleSnapshot.key))
                                {
                                    mesajKeyler.add(singleSnapshot.key)
                                    mesaj?.adi = ""
                                    mesaj?.profil_resim = ""
                                    mesajlar?.add(mesaj!!)
                                }

                                if(myAdapter == null)
                                {
                                    initMesajlar()
                                }

                            }

                        }

                    }

                })

    }

    private fun initMesajlar(){
        myAdapter = SohbetMesajlarAdapter(this@SohbetOdasiActivity,mesajlar!!)
        recyclerSohbetMesajlar.adapter = myAdapter
        recyclerSohbetMesajlar.layoutManager = LinearLayoutManager(this@SohbetOdasiActivity, LinearLayoutManager.VERTICAL,false)
        recyclerSohbetMesajlar.scrollToPosition(mesajlar?.size!! - 1)
    }

    private fun başlatMesajListener() {
        ref = FirebaseDatabase.getInstance().reference
                .child("sohbetOdalari").child(currentRoomId)
                .child("sohbetOdasiMesajlari")

        ref?.addValueEventListener(mValueEventListener)
    }
}
