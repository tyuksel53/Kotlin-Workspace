package KotlinKalıtım

open class Insan {

    var isim:String
    var yas: Int
    var isMan:Boolean = false

    constructor(isim:String,yas:Int)
    {
        this.isim = isim
        this.yas = yas
    }

    constructor(isim:String,yas:Int,isMan:Boolean):this(isim,yas)
    {
        this.isMan = isMan
    }
}
class  Ogretmen2:Insan{

    var brans:String

    constructor(isim: String,yas: Int,isMan: Boolean,brans:String):super(isim, yas, isMan)
    {
        this.brans = brans
    }

    override fun toString(): String { /*Any isimli sınıftan türüyorda*/
        var cinsiyet = if(isMan) "Erkek" else "Kadın"
        return "İsim:$isim , Yas:$yas ,  Cinsiyet:$cinsiyet Brans:$brans"
    }
}


fun main(args: Array<String>) {
    var i1 = Insan("Taha",22)
    var og2 = Ogretmen2("taha",2,false,"turkce")
    println(og2)

}