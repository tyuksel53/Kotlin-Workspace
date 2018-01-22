package KotlinOzelFonksiyonlar

fun main(args: Array<String>) {

    println(fak(5,1))
}

fun fak(a:Int,current:Int):Int
{
    var toplam = 0
    if( a != 1)
    {
        toplam = fak(a-1,current * a)

    }else
    {
        return current
    }

    return  toplam
}