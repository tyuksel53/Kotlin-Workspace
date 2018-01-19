package KotlinDiziler

fun diziFunc()
{
    var myArry:Array<Int> = arrayOf(1,2,3,4,5,6,7)

    println("${myArry[3]} ${myArry.get(myArry.size -1)}")

    var myStringAr: Array<String> = arrayOf("hasan","taha","emre")

    var dinamicArr: Array<Any> = arrayOf("asd",1,1.5,true)

    println(dinamicArr.get(2))

    var gunler = arrayOf("asd","mundi",1)

    println(gunler.get(gunler.size -1 ))
}

fun RangeAndIn(){
    /*1 ile 20 arasındaki sayılar*/

    var myArr:IntRange =  1..20
    var myCharRange:CharRange = 'a'..'z'

    var tersten = 20..1
    var terstenChar = 'z'..'a'

    var oneToTwenty = 1.rangeTo(20)
    var twentyToOne = 20.downTo(1)

    var berserBerser = 1.rangeTo(20).step(5)

    var beserBerIn = 20.downTo(1).step(5)

    var varMi = 6 in berserBerser

    println(berserBerser.last)

}

fun myNull(){

    var myString: String? = null

    println(myString?.length)

    /*println(myString!!.length) *//* -> nullsa null cak geç */

    var myInt:Int?

    myInt = null

    print( myInt.toString().length ) // prints 4

}
var sayi:Int = 10

fun kullanicidanVeriAlma()
{
    println("İsminizi Girin:")
    var isim:String? = readLine()
    println("Yasinizi Girin:")
    var yas = readLine()!!.toInt()
    println("İsim:${isim?.length} , Yas:$yas")

    println(sayi)

}