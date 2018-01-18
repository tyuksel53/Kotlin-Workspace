package KotlinValType

fun byteDegerAralik() // 8 bit
{
    //Number
    //byte, short,int,long,float,double
    var byte:Byte = Byte.MIN_VALUE
    println("Bytenin en kucuk deger:" + byte)
    var byteMax : Byte = Byte.MAX_VALUE
    println("Byte en buyuk degeri:" + byteMax)
}

fun shortDegerAralik() // 16 bit
{
    var shortMin:Short = Short.MIN_VALUE
    var shortMax:Short = Short.MAX_VALUE

    println("Short En Kucuk Deger:" + shortMin)
    println("Short En Buyuk Deger:" + shortMax)
}

fun doubleDegerAralik() // 32 bit
{
    var doubleMin:Double = Double.MIN_VALUE
    var doubleMax:Double = Double.MAX_VALUE

    println("Double En Buyuk Deger:" + doubleMax)
    println("Double En Kucuk Deger:" + doubleMin)
}

fun longDegerAralik()
{
    var longMin:Long = Long.MIN_VALUE
    var longMax:Long = Long.MAX_VALUE

    println("Long En Buyuk Deger:$longMax")
    println("Long En Kucuk Deger:$longMin")
}

fun floatDegerAralik()
{
    var floatMin:Float = Float.MAX_VALUE
    var floatMax:Float = Float.MAX_VALUE

    print("Float En Kucuk Deger${floatMin}\n")
    print("Float En Buyuk Deger $floatMax \n")
}