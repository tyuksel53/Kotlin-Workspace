package KotlinBeforeNesne

import java.sql.Date
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

fun mundi(a:String = "asd"):Int
{
    var zundi:Array<Any> = arrayOf(2,1,"asd",2)
    return 1
}

fun anaFonk()
{

    while(true)
    {
        var input = menuGoster()
        var sayilar = inputlar()
        when(input)
        {
            1 -> toplama(sayilar)
            2 -> cikarma(sayilar)
            3 -> bolme(sayilar)
            4 -> carpma(sayilar)
        }
    }
}
fun toplama(sayi:Array<Int>)
{
    println("Toplamalari: ${sayi[0] + sayi[1]}")
}fun cikarma(sayi:Array<Int>)
{
    println("Farklari: ${sayi[0] - sayi[1]}")
}fun carpma(sayi:Array<Int>)
{
    println("Carpımları: ${sayi[0] * sayi[1]}")
}fun bolme(sayi:Array<Int>)
{
    println("Bölümleri: ${sayi[0] + sayi[1]}")
}
fun menuGoster() : Int
{
    var format = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    println("---------MENU  ${Date().toString()}  ----------")
    println("1 - Toplama")
    println("2 - Çıkarma")
    println("3 - Bölme")
    println("4 - Çarpma")
    var result = readLine()!!.toInt()
    return result
}

fun inputlar():Array<Int>
{
    val zundi = readLine()!!.split(' ')
    val inputlar = zundi.map { it.toInt() }
    val yeni = inputlar.toTypedArray()

    return yeni
}