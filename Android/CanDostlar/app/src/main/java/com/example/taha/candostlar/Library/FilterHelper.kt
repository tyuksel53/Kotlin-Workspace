package com.example.taha.candostlar.Library

import android.widget.Filter


/**
 * Created by Taha on 03-Feb-18.
 */
class FilterHelper(var tumDostlar:ArrayList<Dostlar>,var adaptor:DostAdaptor): Filter() {
    override fun performFiltering(constraint: CharSequence?): FilterResults {

        var sonuc = FilterResults()

        var query = ArrayList<Dostlar>()

        if(constraint != null && !constraint.isNullOrBlank() && constraint.isNotEmpty())
        {
            var text = constraint!!.toString().toLowerCase()

            query = tumDostlar.filter {  it.isim.toLowerCase().contains(text)} as ArrayList<Dostlar>

            sonuc.count = query.size
            sonuc.values = query

        }else
        {
            sonuc.count = tumDostlar.size
            sonuc.values = tumDostlar
        }

        return sonuc

    }

    override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

        adaptor.setFilter(results?.values as ArrayList<Dostlar>)
        adaptor.notifyDataSetChanged()
    }


}