package com.example.taha.manzara.Library

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.taha.manzara.R
import kotlinx.android.synthetic.main.tek_satir.view.*
import java.util.zip.Inflater

class ManzaraAdapter(tumManzaralar:ArrayList<Manzara>): RecyclerView.Adapter<ManzaraAdapter.ManzaraViewHolder>() {

    var manzaralar:ArrayList<Manzara>

    init {
        this.manzaralar = tumManzaralar
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ManzaraViewHolder {

        var inflater = LayoutInflater.from(parent?.context)
        var myLayout = inflater.inflate(R.layout.tek_satir,parent,false)

        return ManzaraViewHolder(myLayout)
    }

    override fun getItemCount(): Int { /* ilk çalışan bölüm  */

        return manzaralar.size
    }

    override fun onBindViewHolder(holder: ManzaraViewHolder?, position: Int) {

        holder?.setData(position,manzaralar[position])
    }


    inner class ManzaraViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        var layout = itemView as CardView

        var baslik = layout.tvBaslik
        var aciklama = layout.tvAciklama
        var resim = layout.imgManzara

        var imgSil = layout.imgSil
        var imgKopyala = layout.imgKopyala

        init {


        }

        fun setData(position: Int,manzaraBilgi:Manzara) {

            baslik.text = manzaraBilgi.baslik
            aciklama.text = manzaraBilgi.aciklama
            resim.setImageResource(manzaraBilgi.resim)

            imgSil.setOnClickListener {
                manzaralar.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position,manzaralar.size)
            }
            imgKopyala.setOnClickListener {
                
                var tmp = manzaralar[position]
                manzaralar.add(position,tmp)
                notifyItemInserted(position)
                notifyItemRangeChanged(position,manzaralar.size)
                
            }
            layout.setOnClickListener {
                Log.e("zundi","mundi")
            }

        }


    }

}