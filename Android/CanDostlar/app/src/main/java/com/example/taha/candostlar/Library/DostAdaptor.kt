package com.example.taha.candostlar.Library
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import com.example.taha.candostlar.DetayActivity
import com.example.taha.candostlar.R
import kotlinx.android.synthetic.main.tek_dost.view.*

/**
 * Created by Taha on 03-Feb-18.
 */
class DostAdaptor(var tumDostlar:ArrayList<Dostlar>): RecyclerView.Adapter<DostAdaptor.DostViewHolder>(),Filterable {

    var myFilter = FilterHelper(tumDostlar,this)



    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DostViewHolder {

        var inflater = LayoutInflater.from(parent?.context)
        var myRow = inflater.inflate(R.layout.tek_dost,parent,false)

        return DostViewHolder(myRow)
    }

    override fun getItemCount(): Int {
        return tumDostlar.size
    }

    override fun onBindViewHolder(holder: DostViewHolder?, position: Int) {

        var currentDost = tumDostlar[position]

        holder?.setData(position,currentDost)

    }


    inner class DostViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        var tek_dostBilgisi = itemView as CardView

        var dostBilgi = tek_dostBilgisi.tvDostBilgi

        var dostResim = tek_dostBilgisi.imgDostResim


        fun setData(position:Int,currentDost:Dostlar)
        {
            dostBilgi.text = currentDost.isim
            dostResim.setImageResource(currentDost.resim)

            tek_dostBilgisi.setOnClickListener {

                var intent = Intent(it.context,DetayActivity::class.java)
                intent.putExtra("bilgi",currentDost)
                it.context.startActivity(intent)
            }

        }

    }

    fun setFilter(query:ArrayList<Dostlar>)
    {
        tumDostlar = query
    }


    override fun getFilter(): Filter {
        return myFilter
    }

/*    fun setFilter(query:ArrayList<Dostlar>)
    {
        tumDostlar = ArrayList()

        tumDostlar.addAll(query)

        notifyDataSetChanged() *//* notify'ı unutmalaaaaaaaaaa *//*
    }*/
}