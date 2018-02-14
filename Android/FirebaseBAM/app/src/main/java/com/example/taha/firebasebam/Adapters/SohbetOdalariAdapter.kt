package com.example.taha.firebasebam.Adapters

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.taha.firebasebam.R
import com.example.taha.firebasebam.model.Kullanici
import com.example.taha.firebasebam.model.SohbetOdası
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.singlelinechatroom.view.*

/**
 * Created by Taha on 14-Feb-18.
 */
class SohbetOdalariAdapter(var sohbetOdalari:ArrayList<SohbetOdası>): RecyclerView.Adapter<SohbetOdalariAdapter.SohbetOdasiViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SohbetOdasiViewHolder {

        var inflater = LayoutInflater.from(parent?.context)
        var singleLine = inflater.inflate(R.layout.singlelinechatroom,parent,false)

        return SohbetOdasiViewHolder(singleLine)
    }

    override fun getItemCount(): Int {

        return sohbetOdalari.size
    }

    override fun onBindViewHolder(holder: SohbetOdasiViewHolder?, position: Int) {

        var currentChatRoom = sohbetOdalari.get(position)

        holder?.setData(position,currentChatRoom)

    }


    inner class SohbetOdasiViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView) {

        var item = itemView as CardView

        var ownerImage = item.ivChatRoomOwner
        var ownerName = item.tvChatOwner
        var chatRoomName = item.tvChatRoomName
        var chatRoomDestroy = item.ivDeleteChatRoom
        var chatRoomMessageCount = item.tvToplamMesaj

        fun setData(position:Int,currentRoom:SohbetOdası)
        {
            var ref = FirebaseDatabase.getInstance().reference

            var getUser = ref.child("kullanici").orderByKey().equalTo(currentRoom.olusturanAdi)
                    .addListenerForSingleValueEvent(object : ValueEventListener{
                        override fun onCancelled(p0: DatabaseError?) {

                        }

                        override fun onDataChange(p0: DataSnapshot?) {
                            for(singleSnapShot in p0!!.children)
                            {
                                var user = singleSnapShot.getValue(Kullanici::class.java)
                                Picasso.with(itemView.context).load(user?.profil_resim).into(ownerImage)
                                ownerName.text = user?.isim
                            }
                        }

                    })
            chatRoomMessageCount.text = "Toplam mesaj sayisi: ${currentRoom.sohbetOdasiMesajlari?.size}"
            chatRoomName.text = currentRoom.sohbetOdasiAdi.toString()

            if(currentRoom.olusturanAdi == FirebaseAuth.getInstance().currentUser?.uid)
            {
                chatRoomDestroy.visibility = View.VISIBLE
            }

            chatRoomDestroy.setOnClickListener {
                sohbetOdalari.removeAt(position)
                FirebaseDatabase.getInstance().reference.child("sohbetOdalari")
                        .child(currentRoom.sohbetOdasi_Id).removeValue()
                notifyItemRemoved(position)
                notifyItemRangeChanged(0,sohbetOdalari.size)
            }
        }

    }
}