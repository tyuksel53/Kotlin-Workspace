package KotlinAbstract

interface Hayvan{ /*Interfaceler Sınıf değildir*/

    fun avlan() /*Interfacelerde metodlarımız abstract ve public dir*/
    fun test(){
        println("Interfazce Test Metdou")
    }
    /*Interfacelerden nesne üretilemez*/
}


abstract class KediGiller : Hayvan
{

    abstract fun takipEt()

}

open class Kedi:KediGiller(){
    override fun avlan() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun takipEt() {
        println("Kedi sınıfı takipEt Çalıştı")
    }

}

class Kaplan:Kedi()
{
    override fun takipEt() {
        println("Kaplan Sınıfı takipEt Çalıştı")
    }

    override fun avlan() {
        super.avlan()
    }
}