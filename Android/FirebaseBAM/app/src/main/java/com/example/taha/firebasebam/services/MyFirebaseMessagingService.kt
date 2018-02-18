package com.example.taha.firebasebam.services

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.Ringtone
import android.media.RingtoneManager
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.example.taha.firebasebam.MainActivity
import com.example.taha.firebasebam.R
import com.example.taha.firebasebam.SohbetOdasiActivity
import com.example.taha.firebasebam.model.SohbetOdası
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService :FirebaseMessagingService() {

    var okunmayiBekleyen_MesajSayisi:Long = 0

    override fun onMessageReceived(p0: RemoteMessage?) {

        if(!activityKontrolEt())
        {
            var baslik = p0?.data?.get("baslik")
            var icerik = p0?.data?.get("icerik")
            var sohbet_odasi_id = p0?.data?.get("sohbet_odasi_id")
            var data = p0?.data

            Log.e("FCM","Başlık:$baslik icerik:$icerik Sohbet Odasi:$sohbet_odasi_id Data:$data")

            var ref = FirebaseDatabase.getInstance().reference
                    .child("sohbetOdalari")
                    .orderByKey()
                    .equalTo(sohbet_odasi_id)
                    .addListenerForSingleValueEvent(object:ValueEventListener{
                        override fun onCancelled(p0: DatabaseError?) {

                        }

                        override fun onDataChange(p0: DataSnapshot?) {

                            var data = p0?.children?.iterator()?.next()

                            var currentChatRoom = SohbetOdası()

                            var nesneMap = (data?.getValue() as HashMap<String,Object>)

                            currentChatRoom.sohbetOdasiAdi = nesneMap.get("sohbetOdasiadi").toString()
                            currentChatRoom.sohbetOdasi_Id = nesneMap.get("sohbetOdasi_Id").toString()

                            var gornenMesajSayisi = data?.child("currentUsersIn_TheRoom")
                                    .child(FirebaseAuth.getInstance().uid)
                                    .child("okunanMesajSayisi")
                                    .getValue().toString().toLong()
                            var toplamMesajSayisi = data?.child("sohbetOdasiMesajlari").childrenCount
                            okunmayiBekleyen_MesajSayisi = toplamMesajSayisi - gornenMesajSayisi

                            bildirimGonder(baslik,icerik,currentChatRoom)


                        }

                    })
        }



    }

    private fun bildirimGonder(baslik: String?, icerik: String?, currentChatRoom: SohbetOdası) {


        var intent = Intent(this,MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        intent.putExtra("sohbetOdasiId",currentChatRoom.sohbetOdasi_Id)

        var bildirimPendding = PendingIntent.getActivity(this,10,intent,PendingIntent.FLAG_UPDATE_CURRENT)


        var bildirimId = notificationIdOlustur(currentChatRoom.sohbetOdasi_Id!!)

        var builder = NotificationCompat.Builder(this,currentChatRoom.sohbetOdasi_Id!!)

        builder.setSmallIcon(R.drawable.ic_accessibility)
                .setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.ic_accessibility))
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentTitle(baslik)
                .setContentText(icerik)
                .setSubText("$okunmayiBekleyen_MesajSayisi Yeni mesaj")
                .setStyle(NotificationCompat.BigTextStyle().bigText(icerik))
                .setNumber(3131)
                .setOnlyAlertOnce(true)
                .setContentIntent(bildirimPendding)

        var notificationMeneger = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationMeneger.notify(bildirimId,builder.build())

    }

    private fun activityKontrolEt():Boolean{
        return SohbetOdasiActivity.isActivityOpen
    }

    private fun notificationIdOlustur(sohbetOdasiId:String):Int{

        var id = 0

        for(i in 4..8)
        {
            id += sohbetOdasiId[i].toInt()
        }

        return id

    }

}