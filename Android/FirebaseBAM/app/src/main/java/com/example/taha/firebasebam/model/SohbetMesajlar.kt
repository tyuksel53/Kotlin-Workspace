package com.example.taha.firebasebam.model

import java.io.Serializable


class SohbetMesajlar:Serializable {

    var mesaj: String? = null
    var kullanici_id: String? = null
    var timestamp: String? = null
    var profil_resim: String? = null
    var adi: String? = null

    constructor() {

    }

    constructor(mesaj: String, kullanici_id: String, timestamp: String, profil_resim: String, adi: String) {
        this.mesaj = mesaj
        this.kullanici_id = kullanici_id
        this.timestamp = timestamp
        this.profil_resim = profil_resim
        this.adi = adi
    }
}
