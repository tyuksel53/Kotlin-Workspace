package KotlinAbstract

open abstract class Calisan()
{
    open var  pozisyon:String = "Calisan"

     open fun Calis() /* abstract metodun içeriğini yazamasın  bitch */
    {
        println("$pozisyon calismaya basladi")
    }

    open fun zundi(){}
}

class Mudur:Calisan(){

    override  var pozisyon: String = "Mudur"

    override fun Calis() {
        println("$pozisyon calismaya basladi")
    }

}
class Programci:Calisan(){

    override  var pozisyon: String = "Programci"

    override fun Calis() { /* Eger bir bile abscrat olsa bile class abstract olmalı */
        println("$pozisyon calismaya basladi")
    }

}
class SatisSorumlusu:Calisan(){

    override  var pozisyon: String = "SatisSorumlusu"

    override fun Calis() {
        println("$pozisyon calismaya basladi")
    }

}

fun main(args: Array<String>) {

    /*var kisi1:Calisan()*/
    var kisi2 = Programci()
    var kisi3 = Mudur()
    var kisi4 = SatisSorumlusu()

    var arr = arrayOf(kisi2,kisi3,kisi4)

    ekranaYaz(arr)
}

fun ekranaYaz(kendi:Array<Calisan>)
{
    for(i in kendi)
    {
        println(i.Calis())
    }
}