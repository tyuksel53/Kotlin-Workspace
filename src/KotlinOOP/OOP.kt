package KotlinOOP

class Person{

    var isim:String = "Taha"
    var yas:Int     =  29

    fun selamVer()
    {
        println("Merhaba ben $isim")
    }
    fun dogumYili() = 2017 - yas

}

fun asilFonk()
{
    var ornek:Person = Person()

    print("${ornek.isim}\n")
    ornek.selamVer()
    println(ornek.dogumYili())
}