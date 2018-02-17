package com.example.taha.firebasebam.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FCMModel {


    @Expose
    @SerializedName("data")
    var data: Data? = null

    @Expose
    @SerializedName("to")
    var to: String? = null

    constructor(data:Data,to:String)
    {
        this.data = data
        this.to = to
    }

    class Data {


        @Expose
        @SerializedName("baslik")
        var baslik: String? = null

        @Expose
        @SerializedName("icerik")
        var icerik: String? = null

        @Expose
        @SerializedName("bildirim_turu")
        var bildirim_turu: String? = null

        @Expose
        @SerializedName("sohbet_odasi_id")
        var sohbet_odasi_id: String? = null


        constructor(baslik:String,icerik:String,bildirim_turu:String,sohbetOdasi:String)
        {
            this.baslik = baslik
            this.icerik = icerik
            this.bildirim_turu = bildirim_turu
            this.sohbet_odasi_id = sohbetOdasi
        }

    }
}
