package KotlinKontrolDongu

fun cevaplar()
{
    soru4()
}

fun soru1(){
    var input:String? = readLine()
    var inputSplit = input!!.split(' ')

    var toplam:Double = 0.0
    for(sayi in inputSplit)
    {
        toplam += sayi.toDouble()

    }
    println("Ortlama: ${toplam / inputSplit.size}")
}

fun soru2()
{

    var input:String? = readLine()

    var inputSplit = input!!.split(' ')

    var filteredTriagle = inputSplit.distinct()
    when(filteredTriagle.size)
    {
        1 -> println("Eşkenar Üçgen")
        2 -> println("İkizkenar Üçgen")
        else ->
        {
            println("Çok Kenar Üçgen")
        }
    }
}

fun soru3()
{
    print("Notlari gir:")
    var notlar = readLine()!!.split(' ')
    var vize:Double = notlar[0].toDouble() * 40 / 100
    var final:Double = notlar[1].toDouble() * 60 / 100

    if(vize + final >= 50)  println("Geçtin") else println("kaldın")

}

fun soru4(){
    for(i in 1 .. 10)
    {
        for(j in 1 .. 10)
        {
            var tmp = "$i x $j ="
            print("${tmp.toString().padStart(6,' ')}  ${i*j} | ")
        }
        println()
    }
}