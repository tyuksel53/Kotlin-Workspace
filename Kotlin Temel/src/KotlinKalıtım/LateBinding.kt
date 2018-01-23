package KotlinKalıtım

/* Çalışma anında neyi çalışrcağını anlaşılıyorsa, late binding deniyor*/


open class Hayvan{

    open fun avYakala()
    {
        println("Hayvan av yakala çalıştı")
    }

}

class Kartal:Hayvan()
{
    override fun avYakala() {
        println("Kartal av yakala çalıştı")
    }
}
class Timsah:Hayvan()
{
    override fun avYakala() {
        println("Timah av yakala çalıştı")
    }
}

fun main(args: Array<String>) {

    var hayvanlar = Array<Hayvan>(3) { Hayvan() }

    for(i in 0 until hayvanlar.size -1)
    {
        hayvanlar[i] = rastegleSec()
    }

    for(i in hayvanlar)
    {
        i.avYakala()
    }

}

fun rastegleSec(): Hayvan {

    var rastgeleSayi = (Math.random() * 3).toInt()

    var currentAnimal = Hayvan()

    when(rastgeleSayi)
    {
        0 -> currentAnimal = Hayvan()
        1 -> currentAnimal = Kartal()
        2 -> currentAnimal = Timsah()
    }

    return currentAnimal
}
