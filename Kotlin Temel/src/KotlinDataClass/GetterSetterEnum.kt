package KotlinDataClass

class Kitap(private var id:Int,var ad:String?)
{

    fun getId():Int
    {
        return id
    }

    fun setId()
    {
        if(id < 0)
        {
            this.id = 1
        }else
        {
            this.id = id
        }
    }

}

class KitapKotlin{

    var id:Int
        get() = field   /* getter setter bitch */
        set(value) {
            if(value < 0 ) field = 1 else field = value
        }
    var isim:String

    constructor(id:Int,isim:String,Rengi:KapakRengi)
    {
        this.id = id
        this.isim = isim
        this.renk = Rengi
    }

    var renk:KapakRengi

}
enum class KapakRengi{

    KIRMIZI,
    MAVİ,
    YESİL

}


fun main(args: Array<String>) {

    var kitap = KitapKotlin(1,"asd",KapakRengi.KIRMIZI)

    println(kitap.id)

    println(kitap.renk)

}

