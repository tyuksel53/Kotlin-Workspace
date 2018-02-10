package com.example.taha.firebasebam

/**
 * Created by Taha on 09-Feb-18.
 */
class Kullanici{

    var isim:String? = null
    var kullanici_Id:String? = null
    var profil_resim:String? = null
    var seviye:Int? = null
    var telefon:String? = null

    constructor(isim:String,profil_resim:String,kullanici_Id:String,seviye:Int,telefon:String)
    {
        this.isim = isim
        this.kullanici_Id = kullanici_Id
        this.telefon = telefon
        this.profil_resim = profil_resim
        this.seviye = seviye
    }
    constructor()
    {

    }


}