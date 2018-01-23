package KotlinOverloading

import java.text.DateFormat
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*

fun soru1()
{
    var dizi = Array<Int>(10){0}
    for(i in 0..9)
    {
        if(i == 0) dizi[i] = 1 else dizi[i] = faktoriel(i)
        println(dizi[i])
    }

}

fun faktoriel(sayi:Int):Int
{
    var toplam = 1
    for(i in 1..sayi)
    {
        toplam *= i
    }
    return toplam
}

fun ayGetir()
{
    var date = Date()
    var format = SimpleDateFormat("MM")

    println(format.format(date))

}

fun soru4()
{
    var boyut = readLine()!!.toInt()
    var dizi = Array<Int>(boyut){0}
    var toplam = 0
    for(i in 0 until boyut) {
        dizi[i] = readLine()!!.toInt()
        toplam += dizi[i]
    }
    println(toplam)

}

fun diziIslemler()
{
    var dizi1 = arrayOf(5,1,3,90,5)
    var dizi2 = Array<Int>(5){0}

    System.arraycopy(dizi1,3,dizi2,3,2)

    for(i in dizi2)
    {
        print("$i ")
    }

    println()

    /*Dizi Sıralama*/

    Arrays.sort(dizi1)

    for(i in dizi1)
    {
        print("$i ")
    }

    println(Arrays.equals(dizi1,dizi2))

    /*Listeler*/

    var liste = arrayListOf<String>("hasan","hasan","huseyin","ayse")

    liste.add("mundi")
    liste.add(0,"taha")
    liste.set(0,"zundi")
    liste.remove("hasan")
    for(i in liste)
        print("$i ")


}