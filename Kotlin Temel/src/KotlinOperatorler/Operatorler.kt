package KotlinOperatorler

fun aritmetikOperator(){
    var sayi:Int = 1
    var sayi2:Int = 2

    println("Sayilarin Toplami: $sayi + $sayi2")

    println("Sayiların Bölme: ${ sayi.toDouble() / sayi2}")
}
fun atama(){
    var sayi = 5

    sayi += 2 /* sayi = sayi + 1*/

    sayi -= 1

    sayi /= 3

    println(message = "İlk Hali:$sayi Son hali:${sayi++} $sayi")

}
fun Mantiksal()
{
    var booleanVal1 = true
    var booleanVal2 = false

    println(booleanVal1 && booleanVal2)
}