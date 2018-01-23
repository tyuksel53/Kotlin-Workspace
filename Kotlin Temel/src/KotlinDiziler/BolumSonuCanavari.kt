package KotlinDiziler

fun DiziBolumSonuCanavari()
{
    soru1()
    soru2()
    soru3()
    soru4()
}

fun soru1(){
    print("Birinci Sayiyi Gir:")
    var input1 = readLine()!!.toInt()
    print("İkinci Sayiyi Gir:")
    var input2 = readLine()!!.toInt()

    println(input1 + input2)
    println(input1 - input2)
    println(input1.toDouble() / input2)
    println(input1 * input2)
}

fun soru2(){
    var i:Int = 5

    i = i++
    println(i) /*6*/
    i= ++i
    println(i) /*7*/
}

fun soru3(){ //correct
    var sonuc: Int = 2 + + 8 - - 2 + + 8 - - 2 + + 8
    println(sonuc) /*30*/
}

fun soru4(){ // correct
   var i:Int = 5
   i = i++ + ++i  - i-- - --i
    println(i) /*0*/
}