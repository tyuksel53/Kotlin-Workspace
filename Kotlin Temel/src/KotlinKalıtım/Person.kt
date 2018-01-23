package KotlinKalıtım

open class Person(isim:String,yas:Int,isMan:Boolean){

    var isim:String
    open var yas:Int = 0
    var isMan:Boolean

    init{
        this.isMan = isMan
        this.isim = isim
        this.yas = yas
    }

    override fun toString(): String { /*Any isimli sınıftan türüyorda*/
        var cinsiyet = if(isMan) "Erkek" else "Kadın"
        return "İsim:$isim , Yas:$yas ,  Cinsiyet:$cinsiyet"
    }
}

class Ogretmen(isim:String,override var yas:Int,isMan:Boolean,brans:String) : Person(isim, yas, isMan){

    var Brans:String
    init{
        if(yas < 0)
        {
            yas = 999
        }
        this.Brans = brans
    }

    override fun toString(): String {
        return super.toString() + " brans:$Brans"
    }
}

fun main(args: Array<String>) {
    var p1 = Person(isim = "Taha",yas = 10,isMan = true)
    println(p1)

    var ogretmen1:Ogretmen = Ogretmen("Fatma",20,false,"Turkce")

    println(ogretmen1)

}