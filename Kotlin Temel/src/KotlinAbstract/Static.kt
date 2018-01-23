package KotlinAbstract

object StaticKavram{ /* singleton pattern stattik class sen ne kadar üretirsen üret hepsi aynı yeri gösteriyor*/
    /* değişimlerde sabit */
    var sinifAdi = "Mundi"

    @JvmStatic
    fun main(){

        println("Main")

    }

}

internal class YokEdici(private val id:Int,private val isim:String) /* parantezler primary constractor */
{
    init{
        toplamOgrenciSayisi++
    }

    fun bilgiler()
    {
        println("bilgiler")
    }
    companion object { /* static değişkenler buraya yazılıyor */
        var toplamOgrenciSayisi = 0
    }
}