package KotlinDegiskenler

fun BolumSonuCanavari(boy:Int,kilo:Int)
{
    val boyCarpimi = boy * boy
    var vucutKitleEndeksi = kilo / boyCarpimi

    if(vucutKitleEndeksi < 18.5 )
    {
        println("Zayif")
    }else if( vucutKitleEndeksi > 18.5 || vucutKitleEndeksi < 24.9)
    {
        println("normal")
    }else
    {
        println("sıçtın")
    }
}