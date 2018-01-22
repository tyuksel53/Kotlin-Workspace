package KotlinDataClass

class Kisi(var id:Int,var isim:String)
{

}

data class KisiData(val id:Int,val isim:String)
{

}

fun main(args: Array<String>) {

    var emre = Kisi(1,"Emre")
    var hasan = Kisi(2,"Hasan")
    var emreKopya = emre /* artık ikiside ram de aynı yeri gösteriyor */

    emreKopya.id = 2

    println(emre.id.toString())

    println(emre.equals(emreKopya))
    println(emre.hashCode())
    println(emre.toString())

    var dEmre = KisiData(1,"Emre")
    var dHasan = KisiData(2,"Hasan")
    var dKopyaEmre = dEmre
    var taha = KisiData(1,"Emre")

    println(dEmre.equals(emreKopya))
    println(dEmre.hashCode())
    println(dEmre.toString())
    println(dEmre.equals(taha))

    println(taha.toString())

    var kopyaHasan = dHasan.copy()

    println(dHasan.equals(kopyaHasan))
    println(dHasan.toString())
}