package KotlinOverloading

fun overloading()
{
    var sayilar = arrayOf(1,2,3,4,1,5,6,9,7,10)
    println("${toplam(sayilar)}")

}

fun toplam(sayilar:Array<Int>):Int
{
    var toplam:Int = 0
    for(i in sayilar)
    {
        toplam += i
    }
    return toplam
}


fun toplaminiBul(sayi1:Int,sayi2:Int):Unit
{
    println("Toplamlari: ${sayi1 + sayi2}")
}

fun toplaminiBul(sayi1:Int,sayi2:Int,sayi3:Int) /* metod overload */
{
    println("Toplamlari:${sayi1+sayi2+sayi3}")
}

fun toplamlariniBul(vararg sayilar:Int)
{
    var toplam = 0
    for(i in sayilar)
    {
        toplam += i
    }
    println(toplam)
}

fun matPlaygorund()
{
    println(Math.E)
    println(Math.PI)
    println(Math.abs(-9))
    println(Math.ceil(8.9))
    println(Math.floor(8.9))
    println(Math.sqrt(16.0))
    println(Math.round(8.9))
    println(Math.pow(2.0,5.0))
    println( (Math.random()* 100).toInt()) /* math.random -> 0,1 arasında random sayi üretmektedir*/

    var sayi1 = Math.random() * 50
    var sayi2 = Math.random()*50
    println("Sayi1:$sayi1 , Sayi2:$sayi2 , Max:${Math.max(sayi1,sayi2)}")
}
fun stringPlaygorund()
{
    var isim = "Taha Yüksel"
    for(i in 0 .. isim.length-1)
    {
        print("${isim[i]} ")
    }
    var takim = "Galatasaray"
    println(takim.lastIndexOf('a'))
    println(takim.replace('a','G'))
}

fun diziler()
{
    var dizi = Array<Int>(10){0}
    for(i in dizi.indices)
    {
        dizi[i] = readLine()!!.toInt()
    }
    for(i in dizi)
    {
        print("$i ")
    }

    var anyDizi:Array<Any> = arrayOf("asd",1,true,false,3,"hasan")

    for(i in anyDizi)
    {
        println("$i")
    }
}