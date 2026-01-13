package net.redcrafter.pokemon

open class Trainer(n: String, p: ArrayList<Pokemon>,b: ArrayList<Item>) {
    val pokemon: ArrayList<Pokemon> = p
    val name: String = n
    val bag: ArrayList<Item> = b
    
    constructor(n: String):this(n,arrayListOf<Pokemon>(),arrayListOf<Item>())
    
}

class Player(n: String, p: ArrayList<Pokemon>,b: ArrayList<Item>) : Trainer(n,p,b) {
    
}