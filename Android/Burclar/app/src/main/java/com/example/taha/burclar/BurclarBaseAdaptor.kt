package com.example.taha.burclar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.teksatir.view.*

/**
 * Created by Taha on 30-Jan-18.
 */
class BurclarBaseAdaptor(context:Context): BaseAdapter() {

    var tumBurclar:ArrayList<Burclar>
    var context: Context
    init{
        tumBurclar = ArrayList(12)
        this.context = context
        var burcAdlar = context.resources.getStringArray(R.array.burclar)

        var burcTarihler = context.resources.getStringArray(R.array.burcTarih)

        val burcResimler = arrayOf(R.drawable.koc1,R.drawable.boga2,R.drawable.ikizler3,R.drawable.yengec4,
                R.drawable.aslan5,R.drawable.basak6,R.drawable.terazi7,R.drawable.akrep8,R.drawable.yay9,
                R.drawable.oglak10,R.drawable.kova11,R.drawable.balik12)

        for(i in 0 .. 11)
        {
            var tmp = Burclar(burcAdlar[i],burcTarihler[i],burcResimler[i])
            tumBurclar.add(tmp)
        }
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


        var currentItem = getItem(position) as Burclar

        holder.burcImageView.setImageResource(currentItem.imgBurc)
        holder.burcAd.text = currentItem.burcAdi
        holder.burcTarih.text = currentItem.burcTarih


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

data class Burclar(var burcAdi:String,var burcTarih:String,var imgBurc:Int)
{

}