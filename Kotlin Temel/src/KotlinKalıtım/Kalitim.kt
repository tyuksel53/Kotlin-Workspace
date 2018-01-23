package KotlinKalıtım

open class GeometrikSekil() /* default olarak bütün classlar public ve finaldır*/
{
    var Boy:Int = 0
    var En:Int = 0

    open fun alanHesapla() = Boy * En

    override fun toString():String
    {
        return "En :$En , Boy: $Boy"
    }
}

class Kare :GeometrikSekil(){

    override fun alanHesapla(): Int {
        /*return super.alanHesapla()*/ /*super (babasına kalıtım oldugu sınıfa ) git alanHesaplayı çalıştır */
        return En*En
    }
}

fun main(args: Array<String>) {
    var geo1 = GeometrikSekil()
    geo1.En = 10
    geo1.Boy = 6

    var kare1 = Kare()
    kare1.Boy = 10
    kare1.En = 6

    println(geo1.toString())

    println(geo1.alanHesapla())
    println(kare1.alanHesapla())
}
