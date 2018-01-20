package KotlinOOP

class Daire(yaricap:Int){

    var yaricap:Int

    init
    {
        this.yaricap = yaricap
    }

    fun alanHesapla() = yaricap * yaricap * Math.PI
}
class Cokgen(var Kenar:Int){}

fun main(args: Array<String>) {
    var d1 = Daire(5)
    println(d1.alanHesapla())
    kareAl(d1)
    var c1 = Cokgen(2)
    print(c1.Kenar * c1.Kenar)
}

fun kareAl(daire:Daire)
{
    println(Math.pow(daire.alanHesapla(),2.0))
}