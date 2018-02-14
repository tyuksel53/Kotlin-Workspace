package com.example.taha.firebasebam.model

class SohbetOdası {

    var sohbetOdasiAdi: String? = null
    var olusturanAdi: String? = null
    var seviye: String? = null
    var sohbetOdasi_Id: String? = null


    constructor() {

    }


    constructor(sohbetOdasiAdi: String, olusturanAdi: String, seviye: String, sohbetOdasi_ıd: String) {
        this.sohbetOdasiAdi = sohbetOdasiAdi

        this.olusturanAdi = olusturanAdi
        this.seviye = seviye
        sohbetOdasi_Id = sohbetOdasi_ıd
    }

}
