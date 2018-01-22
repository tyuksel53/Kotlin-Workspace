package KotlinOzelFonksiyonlar

fun main(args: Array<String>) {

    var sayilar = arrayOf(1,23,4,5,67,7)

    sayilar.forEach { sayi -> println(sayi) }

    var nesne = Toplam()

    nesne.ikiSayiyiTopla(1,2,object:ToplamlariniGoster{
        override fun toplamGosteren(toplam: Int) {
            println(toplam)
        }

    })

    var exresssion = {sayi:Int -> println(sayi)} /* lambda expression , isimsiz fonksiyon*/

    nesne.ikiSayiyiTopla(1,4,exresssion)

    var expression = {a:Int,b:Int -> a + b}

    var test = TestLambda()

    var terstenYaz = { isim:String ->
        var gecici:CharArray = CharArray(isim.toCharArray().size)

        for(i in 0 until isim.toCharArray().size )
        {
            gecici[i] = isim[ isim.toCharArray().size-1 -i ]
        }

         println(gecici)
        var sonuc:String = String(gecici)
        sonuc
    }

    test.topla(1,5,expression)

    println(test.terstenYaz("Taha",terstenYaz))

}

class Toplam{

    fun ikiSayiyiTopla(a:Int,b:Int)
    {
        var toplam = a + b
    }
    fun ikiSayiyiTopla(a:Int,b:Int,action:ToplamlariniGoster)
    {
        var toplam = a + b
        action.toplamGosteren(toplam)

    }
    fun ikiSayiyiTopla(a:Int,b:Int,action:(Int) -> Unit)
    {
        var toplam = a + b
        action(toplam)
    }

}

class TestLambda{

    fun topla(a:Int,b:Int,action:(Int,Int) -> Int)
    {
        var toplam =  action(a,b)
        println(toplam)
    }

    fun terstenYaz(isim:String,myFunc:(String) -> String):String
    {
       var result =  myFunc(isim)

        return result
    }

}
interface ToplamlariniGoster{

    fun toplamGosteren(toplam:Int)

}