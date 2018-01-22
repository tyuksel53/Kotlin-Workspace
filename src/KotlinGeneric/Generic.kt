package KotlinGeneric

class StringDiziYaz<T>(var arr:Array<T>) { /* var dersek elaman oluyor, var demessek paremetre oluyor*/

    fun diziYazdir(){

        for(gececi in arr)
        {
            println(gececi)
        }

    }

}

fun main(args: Array<String>) {
    var stringDizi = arrayOf<String>("Taha","Hasan","Lahmi")
    var intergarDizi = arrayOf<Int>(1,2,3,5)
    var stringDiziYazSinif = StringDiziYaz(stringDizi)
    var intagerSinif = StringDiziYaz(intergarDizi)
    stringDiziYazSinif.diziYazdir()
    intagerSinif.diziYazdir()

}