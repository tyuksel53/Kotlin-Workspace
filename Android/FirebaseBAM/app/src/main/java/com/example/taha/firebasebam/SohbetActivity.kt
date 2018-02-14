package com.example.taha.firebasebam

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import com.example.taha.firebasebam.Adapters.SohbetOdalariAdapter
import com.example.taha.firebasebam.model.SohbetMesajlar
import com.example.taha.firebasebam.model.SohbetOdası
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_sohbet.*

class SohbetActivity : AppCompatActivity() {

    var tumSohbetOdalari:ArrayList<SohbetOdası>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sohbet)
        init()


    }

    fun init(){
        fabYeniSohbet.setOnClickListener {

            var dialog = YeniSohbetFragment()
            dialog.show(supportFragmentManager,"yeni sohbet")

        }
        getSohbetOdalari()
    }

    fun getSohbetOdalari()
    {
        tumSohbetOdalari = ArrayList<SohbetOdası>()

        var ref = FirebaseDatabase.getInstance().reference

        var sorgu = ref.child("sohbetOdalari").addListenerForSingleValueEvent(object:ValueEventListener{

            override fun onCancelled(p0: DatabaseError?) {

            }

            override fun onDataChange(p0: DataSnapshot?) {

                for(singleSnapshot in p0!!.children)
                {
                    var currentChatRoom = SohbetOdası()

                    var nesneMap = singleSnapshot.getValue() as HashMap<String,Object>

                    currentChatRoom.sohbetOdasi_Id = nesneMap.get("sohbetOdasi_Id").toString()
                    currentChatRoom.sohbetOdasiAdi = nesneMap.get("sohbetOdasiAdi").toString()
                    currentChatRoom.seviye = nesneMap.get("seviye").toString()
                    currentChatRoom.olusturanAdi = nesneMap.get("olusturanAdi").toString()

                    var tumMesajlar = ArrayList<SohbetMesajlar>()

                    for(i in singleSnapshot.child("sohbetOdasiMesajlari").children)
                    {
                        var okunanMesaj = i.getValue(SohbetMesajlar::class.java)
                        tumMesajlar.add(okunanMesaj!!)
                    }

                    currentChatRoom.sohbetOdasiMesajlari = tumMesajlar
                    tumSohbetOdalari!!.add(currentChatRoom)

                    /*currentChatRoom.olusturanAdi = singleSnapshot.getValue(SohbetOdası::class.java)?.olusturanAdi
                    currentChatRoom.seviye = singleSnapshot.getValue(SohbetOdası::class.java)?.seviye
                    currentChatRoom.sohbetOdasiAdi = singleSnapshot.getValue(SohbetOdası::class.java)?.sohbetOdasiAdi
                    currentChatRoom.sohbetOdasi_Id = singleSnapshot.getValue(SohbetOdası::class.java)?.sohbetOdasi_Id*/

                }

                Toast.makeText(this@SohbetActivity,"Tum ChatRoom : ${tumSohbetOdalari!!.size}",Toast.LENGTH_LONG).show()
                tumOdalariGetir()
            }

        })





    }

    fun tumOdalariGetir()
    {
        var myAdapter = SohbetOdalariAdapter(tumSohbetOdalari!!)

        recyclerViewSohbet.adapter = myAdapter
        recyclerViewSohbet.layoutManager = LinearLayoutManager(this@SohbetActivity,LinearLayout.VERTICAL ,false)

    }

}
