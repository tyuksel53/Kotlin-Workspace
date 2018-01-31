package com.example.taha.burclar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

class BurclarBaseAdaptor(context:Context,burcBilgiler:ArrayList<Burc>): BaseAdapter() {

    var tumBurclar:ArrayList<Burc>

    var context: Context

    init{
        tumBurclar = burcBilgiler
        this.context = context
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

        var tek_satir_view = convertView
        var holder:BurclarArrayAdapter.ViewHolder
        if(tek_satir_view == null)
        {
            var inflater = LayoutInflater.from(context)

            tek_satir_view = inflater.inflate(R.layout.teksatir,parent,false)
            holder = BurclarArrayAdapter.ViewHolder(tek_satir_view)
            tek_satir_view.tag = holder
        }else
        {
            holder = tek_satir_view.getTag() as BurclarArrayAdapter.ViewHolder
        }


        var currentItem = getItem(position) as Burc

        holder.burcImageView.setImageResource(currentItem.burcResmi)
        holder.burcAd.text = currentItem.burcAdi
        holder.burcTarih.text = currentItem.burcTarihi


        return tek_satir_view!!
    }

    override fun getItem(position: Int): Any {
        return tumBurclar.get(position)
    }

    override fun getItemId(position: Int): Long { /* veritabanlarında kullanılıyor*/
        return 0
    }

    override fun getCount(): Int {
        return tumBurclar.size
    }

}