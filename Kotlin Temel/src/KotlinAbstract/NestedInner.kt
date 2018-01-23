package KotlinAbstract

class disSinif()
{
    var sinifAdi = "Dis Sinif"

    fun disSinifSelamVer()
    {
        println("Dış sınıfdan merhaba")
    }

    inner class icSinif{
        var sinifAdi = "Iç Sinif"

        fun içSinifSelamVer(){
            println("İç Sınıf Selam Verdi")
        }

        inner class Binner{
            var sinifAdi = "InnerBinner"
        }
    }


}

fun main(args: Array<String>) {
    var dis = disSinif()

    dis.icSinif().içSinifSelamVer()

    var icsınif = disSinif().icSinif()

    var binner = disSinif().icSinif().Binner()
}