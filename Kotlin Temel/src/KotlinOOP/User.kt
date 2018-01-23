package KotlinOOP

class User(name:String,id:Int) {

    var Name:String
    var Id:Int
    var Adres:String = ""

    init {
        this.Name = name
        this.Id = id
    }
    constructor(id:Int,name:String,adres:String):this(name,id) /*Secondery Constroctor*/
    {
        this.Adres = adres
    }
}

fun main(args: Array<String>) {
    var taha = User("hasana",1)
    var hasan = User(1,"zundi","asd")
    println(hasan.Name +" " + hasan.Id + " " + hasan.Adres)
}