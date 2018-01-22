package KotlinOzelFonksiyonlar

class Baslik(var baslik:String){}

fun main(args: Array<String>) {

    var b1 = Baslik("Hello World")
    var b2 = Baslik("Taha")

    var b3 = b1 plus b2
    var b4 = b1 + b2

    println(b3.baslik)
    println(b4.baslik)


}

infix operator fun Baslik.plus(b2:Baslik) : Baslik{ /* operator overloading */

    var gecici = Baslik(this.baslik + " " + b2.baslik)

    return gecici

}