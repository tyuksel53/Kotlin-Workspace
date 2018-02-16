package com.example.taha.firebasebam.Adapters

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.taha.firebasebam.R
import com.example.taha.firebasebam.model.SohbetMesajlar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.singlelinemessage.view.*
import java.util.zip.Inflater

/**
 * Created by Taha on 16-Feb-18.
 */
class SohbetMesajlarAdapter(var context: Context, var mesajlar:ArrayList<SohbetMesajlar>): RecyclerView.Adapter<SohbetMesajlarAdapter.SohbetMesajlarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SohbetMesajlarViewHolder {

        var inflater = LayoutInflater.from(parent?.context)

        var singleLine = inflater.inflate(R.layout.singlelinemessage,parent,false)

        return SohbetMesajlarViewHolder(singleLine)

    }

    override fun getItemCount(): Int {

        return mesajlar.size
    }

    override fun onBindViewHolder(holder: SohbetMesajlarViewHolder?, position: Int) {

        var currentMessage = mesajlar.get(position)
        holder?.setData(currentMessage,position)

    }


    inner class SohbetMesajlarViewHolder(itemView:View?):RecyclerView.ViewHolder(itemView){

        var layout = itemView as CardView
        var mesaj = layout.tvMessageOwnerText
        var tarih = layout.tvMessageOwnerTime
        var ownerName = layout.tvMessageOwnerName
        var img = layout.ivMessageOwnerImg

        fun setData(currentMessage:SohbetMesajlar,position:Int) {

            if(!currentMessage.profil_resim.isNullOrEmpty())
            {
                Picasso.with(context).load(currentMessage.profil_resim).resize(48,48).into(img)
            }

            tarih.text = currentMessage.timestamp

            if(!currentMessage.adi)

        }

    }

}