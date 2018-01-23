package KotlinException

fun main(args: Array<String>) {

    var asd = 5.ZUNDI()
    println("$asd")
    println("asdasdas     asdsadasds      taha".BoslukDuzenle())
}

fun Int.ZUNDI() : Int
{
    var toplam = 1
    for(i in 1 until this)
    {
        toplam *=i
    }
    return toplam
}

fun String.BoslukDuzenle():String
{

    var regex = Regex("\\s+")
    return regex.replace(this," ")

}

/*fun<T:Number> Toplama(param1:T,param2:T) : T
{

    return param1.plus(param2)
}

private operator fun <T> T.plus(param2: T): T {

    return this + param2
}*/
