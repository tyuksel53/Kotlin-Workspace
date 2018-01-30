package com.example.taha.burclar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.teksatir.view.*

/**
 * Created by Taha on 30-Jan-18.
 */
class BurclarArrayAdapter(context: Context?, resource: Int, textViewResourceId: Int,var burcAdlar: Array<String>,
                          var burcTarihler:Array<String>,var burcResimler:Array<Int>)
    : ArrayAdapter<String>(context, resource, textViewResourceId, burcAdlar) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var tek_satir = convertView

        var holder:ViewHolder? = null

        if(tek_satir == null)
        {
            var inflater = LayoutInflater.from(context)
            tek_satir = inflater.inflate(R.layout.teksatir,parent,false)

            holder = ViewHolder(tek_satir)
            tek_satir.tag = holder
        }else
        {
            holder = tek_satir.getTag() as ViewHolder
        }

        holder.burcImageView.setImageResource(burcResimler[position])
        holder.burcAd.text = burcAdlar[position]
        holder.burcTarih.text = burcTarihler[position]

        return tek_satir!!
    }

    class ViewHolder(tek_satir:View){

        var burcImageView:ImageView
        var burcTarih:TextView
        var burcAd:TextView
        init{
            this.burcAd = tek_satir.tvBurcAdi
            this.burcImageView = tek_satir.imgBurcSembol
            this.burcTarih = tek_satir.tvTarih
        }
    }
}