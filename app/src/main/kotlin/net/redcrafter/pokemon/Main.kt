package net.redcrafter.pokemon

import kotlinx.coroutines.runBlocking
import kotlin.system.exitProcess
import co.pokeapi.pokekotlin.PokeApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import java.io.File

fun main(args: Array<String>) {
  runBlocking {
    with(PokeApi) {
      runCatching {
        val bulb = getPokemonVariety(1)
        val pokemon = Pokemon(bulb,50)
        println("Species: $pokemon \n\n\n")
        val json = Json.encodeToString(bulb)
        println("JSON: $json")
        File("1.json").writeText(json)
      }.onFailure { e ->
        println("Error: ${e.message}")
      }
    }
  }
  exitProcess(0)
}