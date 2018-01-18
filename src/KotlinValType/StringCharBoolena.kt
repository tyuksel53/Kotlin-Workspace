package KotlinValType

fun booleanType()
{
    var isTrue:Boolean = true

    print("${isTrue.toString()} degerini\n")
}

fun charType()
{
    var charVal:Char = 'a'
    var charVal2:Char = 'a'
    println("$charVal$charVal birlesti")
}

fun stringType()
{
    var stringVal:String = "Taha Yuksel"
    println("Bos yapma laaaan $stringVal ${stringVal.length}")
    println("${stringVal.indexOf('a')}")
    println("""Escape karakterler gg oldu \n \n \n""")
}