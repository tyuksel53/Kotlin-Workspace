package KotlinKontrolDongu

fun ifElse()
{
    println("Yaslari Girin:")
    var yas:Int = readLine()!!.toInt()
    if(yas > 17)
    {
        println("yas buyuk la")
    }else
    {
        println("sie go liseli")
    }

    println("Notunuguzu Girin:")
    var not = readLine()!!.toInt()

    /*if(not in 1.rangeTo(44)) *//* bir ile 44 dahil hakeza diğerlerinde de öyle*//*
    {
        println("FF")
    }else if(not in 44..65)
    {
        println("CC")
    }else if(not in 65..80)
    {
        println("BB")
    }else
    {
        println("AA")
    } */

    /*var sonuc = if(not in 1..44) {
        "mundi" *//* retrun deger da *//*
    }else
    {
        1 *//*sen hangi kızdan bahsediyorsun*//*
    }*/
}

fun whenCase()
{
    println("Not girin: ")
    var not = readLine()!!.toInt()

    var sonuc = when(not)
    {
        in 0 .. 44 -> {
            println("Sıçtın Bu kadar dusuk not olur mu")
            1
        }
        in 45 .. 54 ->{
            println("Götü kurtardın gene iyisin")
            2
        }
        in 55 .. 70 ->{
            print("Helal sana lan")
            3
        }
        in 71 .. 100 -> 4
        else ->
        {
            -1
        }

    }
}

fun Dongu(){

   /* for(x in dizi,liste,range,string)
    {
        // calistirilacak kodlar
    }*/

    var intDizisi:Array<Int> = arrayOf(1,2,3,4,5)
    var myString:String = "Taha"
    for(x in intDizisi)
    {
        print("$x ")
    }
    println()
    for(x in myString.toCharArray().indices )
    {
        print("${myString.toCharArray()[x] } ")
        if(x == myString.toCharArray().size-1)
        {
            println();
        }
    }

    for(x in 1..20)
    {
        print("$x ")
    }
    println("")
    for(x in 1..myString.length)
    {
        print(x)
    }

    var sayilar = arrayOf(1,10,23,4,5)

    var zundi = sayilar.sum()

    println("$zundi ----------")

    var topalm:Int = 0

    for(x in sayilar)
    {
        topalm = topalm + x
    }
    println(topalm)

}

fun doWhile()
{
    /*Do while da , Do en az bir kere çalışmaktadır*/
    for(i in 1..10)
    {
        println(i)
    }
    var i:Int = 1

    while(i<=10)
    {
        println(i)
        i++
    }

    for(char in "xcvtaha@hotmail.com")
    {
        if(char == '@')
        {

        }
        println("$char")
    }
}

fun breakContinue(){
    for(char in "xcvtaha@hotmail.com")
    {
        if(char == '@')
        {
            break;
        }
        print("$char")

    }
    var toplam = 0;
    for(sayi in 1 .. 10)
    {
        if( sayi == 7)
            continue    /*alttaki satirları yapma demek 8 den devam et*/

        toplam += sayi
    }
    println(toplam)
}