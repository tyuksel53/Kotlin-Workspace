package KotlinAbstract

interface Okuyabilme{  /* iki farklı nesne, iki farklı sınıf  */
    fun oku()
}

class Tarayici : Okuyabilme
{
    override fun oku() {

    }

}

fun main(args: Array<String>) {

    var tarayicim = Tarayici()

    tarayicim.oku()

    /*var isimsizSinif =  Okuyabilme -----> hata*/

    var isimsizSinif = object : Okuyabilme{

        override fun oku() {
            println("ezdik")
        }

    }

}