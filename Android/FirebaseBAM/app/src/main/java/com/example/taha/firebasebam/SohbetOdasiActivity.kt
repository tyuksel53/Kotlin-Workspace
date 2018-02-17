package com.example.taha.firebasebam

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.taha.firebasebam.Adapters.SohbetMesajlarAdapter
import com.example.taha.firebasebam.model.FCMModel
import com.example.taha.firebasebam.model.Kullanici
import com.example.taha.firebasebam.model.SohbetMesajlar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_sohbet_odasi.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet


class SohbetOdasiActivity : AppCompatActivity() {

    companion object {
        var isActivityOpen = false
    }

    var currentRoomId:String? = null
    var mAuthListner:FirebaseAuth.AuthStateListener? = null
    var ref:DatabaseReference? =  null
    var mesajlar:ArrayList<SohbetMesajlar>? = null
    var myAdapter:SohbetMesajlarAdapter? = null
    var mesajKeyler = HashSet<String>()
    var serverKey:String? = null
    var baseUrl = "https://fcm.googleapis.com/fcm/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sohbet_odasi)

        startAuthListener()
        mesajlariGetir()
        server_key_get()
/*        supportActionBar?.title = currentChatRoom.sohbetOdasiAdi.toString()
        supportActionBar?.subtitle = "Toplam Mesaj: ${currentChatRoom.sohbetOdasiMesajlari?.size}"*/

       /* var myAdapter = SohbetMesajlarAdapter(this@SohbetOdasiActivity,currentChatRoom.sohbetOdasiMesajlari!!)

        recyclerSohbetMesajlar.adapter = myAdapter
        recyclerSohbetMesajlar.layoutManager = LinearLayoutManager(this@SohbetOdasiActivity,LinearLayoutManager.VERTICAL,false)*/


        init()


    }

    private fun server_key_get()
    {
        var ref = FirebaseDatabase.getInstance().reference
                .child("server")
                .orderByValue()
                .addListenerForSingleValueEvent(object :ValueEventListener{
                    override fun onCancelled(p0: DatabaseError?) {

                    }

                    override fun onDataChange(p0: DataSnapshot?) {

                        var sinlgeSnapShot = p0?.children?.iterator()?.next()

                        serverKey = sinlgeSnapShot?.getValue().toString()
                        Log.e("SERVER","$serverKey")

                    }

                })

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

                var retrofit = Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()

                var myInterface = retrofit.create(FCMInterface::class.java)

                var ref = FirebaseDatabase.getInstance().reference
                        .child("sohbetOdalari")
                        .child(currentRoomId)
                        .child("currentUsersIn_TheRoom")
                        .orderByKey()
                        .addListenerForSingleValueEvent(object :ValueEventListener{

                            override fun onCancelled(p0: DatabaseError?) {

                            }

                            override fun onDataChange(p0: DataSnapshot?) {
                                for(kullaniciId in p0!!.children)
                                {
                                    var key = kullaniciId.key.toString()
                                    if(key != FirebaseAuth.getInstance().currentUser?.uid)
                                    {
                                        var newRef = FirebaseDatabase.getInstance().reference
                                                .child("kullanici")
                                                .orderByKey()
                                                .equalTo(key)
                                                .addListenerForSingleValueEvent(object :ValueEventListener{
                                                    override fun onCancelled(p0: DatabaseError?) {

                                                    }

                                                    override fun onDataChange(p0: DataSnapshot?) {

                                                        var tekKullanici = p0?.children?.iterator()?.next()
                                                        var mesajToken = tekKullanici?.getValue(Kullanici::class.java)?.mesaj_token

                                                        var header = HashMap<String,String>()
                                                        header.put("Content-type","application/json")
                                                        header.put("Authorization","key=$serverKey")

                                                        var data = FCMModel.Data("Yeni Mesaj Var","${edSohbetMesaj.text.toString()}","sohbet","$currentRoomId")

                                                        var bildirim = FCMModel(data,mesajToken.toString())

                                                        var istek = myInterface.bildirimGonder(header,bildirim)

                                                        istek.enqueue(object:Callback<Response<FCMModel>>{
                                                            override fun onFailure(call: Call<Response<FCMModel>>?, t: Throwable?) {
                                                                Log.e("RETROFIT","${t?.message}")
                                                            }

                                                            override fun onResponse(call: Call<Response<FCMModel>>?, response: Response<Response<FCMModel>>?) {
                                                                Log.e("RETROFİT","${response.toString()}")
                                                            }

                                                        })

                                                    }

                                                })
                                    }
                                }
                            }

                        })



                edSohbetMesaj.setText("")
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
            gorunenMesajlar(p0?.childrenCount)
        }

    }

    private fun gorunenMesajlar(okunanMesajSayisi:Long?) {

        var ref = FirebaseDatabase.getInstance().reference
                .child("sohbetOdalari")
                .child(currentRoomId)
                .child("currentUsersIn_TheRoom")
                .child(FirebaseAuth.getInstance().uid)
                .child("okunanMesajSayisi")
                .setValue(okunanMesajSayisi)

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
