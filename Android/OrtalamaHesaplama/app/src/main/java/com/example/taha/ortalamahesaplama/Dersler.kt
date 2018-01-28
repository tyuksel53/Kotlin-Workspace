package com.example.taha.ortalamahesaplama

/**
 * Created by Taha on 28-Jan-18.
 */
data class Dersler(var dersAdi:String,var dersKredi:String,var dersNot:String) {

    init {
        dersKredi = dersKredi.replace(" Kredi","")

        dersNot = when(dersNot)
        {
            "AA" -> "4.0"
            "BA" -> "3.5"
            "BB" -> "3.0"
            "CB" -> "2.5"
            "CC" -> "2.0"
            "DC" -> "1.5"
            "DD" -> "1.0"
            "FD" -> "0.5"
            "FF" -> "0.0"
            else -> "0.0"
        }
    }

}