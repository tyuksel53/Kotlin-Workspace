package KotlinOOP

class Kare {

    var Kenar : Int = 0

    constructor()
    {
        this.Kenar = 0
    }

    fun alanHesapla() = Kenar * Kenar

    constructor(Kenar: Int)
    {
        this.Kenar = Kenar
    }
}

fun main(args: Array<String>) {

    var kare1 = Kare()
    var kare2 = Kare(1)


}

