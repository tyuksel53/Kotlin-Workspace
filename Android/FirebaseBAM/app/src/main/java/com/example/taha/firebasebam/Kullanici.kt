package com.example.taha.firebasebam

/**
 * Created by Taha on 09-Feb-18.
 */
class Kullanici{

    var isim:String? = null
    var telefon:Long? = null
    var profil_resim:String? = null
    var seviye:Int? = null
    var kullanici_Id:String? = null

    constructor(isim:String,telefon:Long,profil_resim:String,seviye:Int,kullanici_Id:String)
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