package KotlinKoleksiyonlar.Koleksiyon

/*Listler*/

/*muteable ->  değişebilir*/

/*immutable -> değiştirilemez listof*/


fun main(args: Array<String>) {

    /*var list = listOf(1,2,3,4,"asdas",true) *//* güncelleme olmuyor *//*

    for(i in list)
    {
        println(i)
    }
    println(list[2])

    var s1 = list[0] as Int

    var degisebilenListe = ArrayList<Int>(10) *//*initialCapacity zorunlu degil*//*

    var degisListe = ArrayList<String>()

    degisListe.add("ahahah")

    degisListe[0] = "asdads"
    degisListe.set(0,"Dart Vader")

    println("Dart" in "Dart Vader")

    for(i in degisListe)
    {
        println(i)
    }

    map()*/
    setler()
}

fun map()
{
    var myMap =  mapOf<Int,String>(1 to "taha",2 to "zundi",3 to "kundi") /* degistirilmiyor*/
    /* map anahatar değer ikilisi, */
    /* key value pairs */
    for( i in myMap)
    {
        println("${i.value}")
    }
}

fun mapDegisen(){

    var degisenMap = hashMapOf<Int,String>(1 to "taha",-1 to "ahhaha")
    degisenMap.put(1,"asd")
    degisenMap.set(1,"mundi")

    for(i in degisenMap)
    {
        println("$i")
    }

}

fun setler()
{
    var mySet = setOf<Any>("aTaha","hasan",1,2,3,"zundi")
    /* Degistirelemiyon  */
    /*mySet.put*/

    var degisenSet = mutableSetOf<Any>("adsadas","taha",123,123,"taha")

    println(degisenSet.size)
}