package KotlinAbstract

abstract class Sporcu()
{
    abstract fun sporYap()
}

open class BuzPateni:Sporcu(){

    override fun sporYap() {
        println("Buz pateni yapıyor")
    }

}

open class Basketbol:Sporcu(){

    override fun sporYap() {
        println("Basketbol yapıyor")
    }

}

interface BuzUstundeKayabilme{

    fun buzUstundeKay(){}

}
interface BasketAtabilme{

    fun basketAt(){}

}

class Sportmenler:BuzUstundeKayabilme,BasketAtabilme{

    var sporcu = Sportmenler()

    /* sporcu.sporYap -> hangisini çağıracak aq bilemedi  */

}