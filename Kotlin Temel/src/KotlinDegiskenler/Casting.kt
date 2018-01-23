package KotlinDegiskenler

fun casting(){
    var shortVal:Short = Short.MAX_VALUE
    var intagerVal:Int = 32

    shortVal = intagerVal.toShort()

    println(shortVal)

    var doubleVal : Double = 123123.123231
    var castVal = doubleVal.toInt()
    println(castVal)
}