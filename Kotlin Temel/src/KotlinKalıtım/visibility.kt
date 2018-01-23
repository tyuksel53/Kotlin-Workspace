package KotlinKalıtım

open class ulus{

    private var a = 1
    protected  var b = 2
    internal  var c = 3 /*modulun içerisinde tanımlı */
    public var  z = 4

    open fun test()
    {
        println(a)
        println(b)
        println(c)
        println(z)

    }
}

class turk: ulus(){

    override fun test()
    {

    }

}