package KotlinKalıtım

open class Asker
{

    open fun selamVer()
    {
        println("Asker Selam Verdi")
    }
}

class Er:Asker()
{
    override fun selamVer()
    {
        println("Er Selam verdi")
    }

    fun zundi()
    {

    }
}

fun main(args: Array<String>) {

    var kisi:Asker = Asker()

    var kisi2:Asker = Er()

    var kisi3:Asker = Er()



    hazirOl(kisi)
    hazirOl(kisi2) /* özel olan er sınıfı, daha genel olan asker sınıfına çevrildi upcasting*/
}

fun hazirOl(kisi:Asker) /* polymorfizim, asker tipi bekliyor er gönderdik er selam verdi  */
{
    kisi.selamVer() /* er'in diğer özelliklerine erişememekteyiz */
}