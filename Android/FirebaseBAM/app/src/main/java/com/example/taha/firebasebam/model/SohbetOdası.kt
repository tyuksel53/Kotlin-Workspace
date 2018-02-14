package com.example.taha.firebasebam.model

class SohbetOdası {

    var sohbetOdasiAdi: String? = null
    var olusturanAdi: String? = null
    var seviye: String? = null
    var sohbetOdasi_Id: String? = null
    var sohbetOdasiMesajlari:List<SohbetMesajlar>? = null

    constructor() {

    }


    constructor(sohbetOdasiAdi: String, olusturanAdi: String, seviye: String, sohbetOdasi_ıd: String,sohbetOdasiMesajlari:List<SohbetMesajlar>) {
        this.sohbetOdasiAdi = sohbetOdasiAdi
        this.olusturanAdi = olusturanAdi
        this.seviye = seviye
        this.sohbetOdasi_Id = sohbetOdasi_ıd
        this.sohbetOdasiMesajlari = sohbetOdasiMesajlari
    }

}
