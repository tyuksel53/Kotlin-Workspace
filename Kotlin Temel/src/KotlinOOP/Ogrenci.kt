package KotlinOOP

class Ogrenci (Isim:String,Yas:Int){

    var Isim:String
    var Yas:Int

    init {
        this.Isim = Isim
        this.Yas = Yas

    }
    fun bilgileriGoser()
    {
        println("Ben $Isim , Yasim: $Yas")
    }

}

fun zundi(){
    var ogerenci = Ogrenci("taha",12)

    ogerenci.bilgileriGoser()
}