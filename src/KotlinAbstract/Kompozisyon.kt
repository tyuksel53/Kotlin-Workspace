package KotlinAbstract

class ArabaMotoru
{
    fun calistri(){}
    fun durdur(){}
}

class Pencere()
{
    fun asagiCek(){}
    fun yukariCek(){}
}

class Kapi()
{
    var pencere = Pencere()

    fun ac(){}
    fun kapa(){}

}
class Tekerlek(){

    fun havaPompala(miktar:Int){}
}

class Araba() {
    var motor = ArabaMotoru()
    var SagKapi = Kapi()
    var SolKapi = Kapi()
    var Tekerlek = Tekerlek()

    var tekerlekler = Array<Tekerlek>(4) { Tekerlek }

}

fun main(args: Array<String>) {
    var car = Araba()

    car.SagKapi.pencere.asagiCek()
    car.SolKapi.ac()
    car.motor.calistri()
    car.Tekerlek.havaPompala(1)
}