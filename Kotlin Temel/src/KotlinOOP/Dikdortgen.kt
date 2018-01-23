package KotlinOOP

class Dikdortgen(var Boy: Int = 0, var En: Int = 0) {


    fun alanHesapla() = Boy * En
}

fun main(args: Array<String>) {

    var d1 = Dikdortgen(10, 20)

    println(d1.alanHesapla())

    var d2 = Dikdortgen(10,20)

    var d3 = Dikdortgen(Boy = 1,En = 20)

    println(d3.alanHesapla())

    var d4 = Dikdortgen()

    println(d4.alanHesapla())



}