package net.redcrafter.pokemon

import co.pokeapi.pokekotlin.model.*

class Pokemon(species: PokemonVariety,l: Int) {
    val name: String = species.name
    var level: Int = l
    var exp: Int = species.baseExperience
    val moves: List<PokemonMove> = species.moves
    val base_stats: Map<String,Int> = mapOf("hp" to species.stats.get(0).baseStat,"attack" to species.stats.get(1).baseStat,"defense" to species.stats.get(2).baseStat,"sp_attack" to species.stats.get(3).baseStat,"sp_defense" to species.stats.get(4).baseStat,"speed" to species.stats.get(5).baseStat)
    val stats: MutableMap<String,Int>
    val evs: MutableMap<String,Int> = mutableMapOf("hp" to 0,"attack" to 0,"defense" to 0,"sp_attack" to 0,"sp_defense" to 0,"speed" to 0)
    val ivs: MutableMap<String,Int> = mutableMapOf("hp" to (0..31).random(),"attack" to (0..31).random(),"defense" to (0..31).random(),"sp_attack" to (0..31).random(),"sp_defense" to (0..31).random(),"speed" to (0..31).random())
    val types: List<PokemonType> = species.types
    
    init{
        stats = calculateStats(base_stats,ivs,evs,level) as MutableMap<String,Int>
    }
    
    companion object{
        fun calculateStats(
            baseStats: Map<String, Int>,
            ivs: Map<String, Int>,
            evs: Map<String, Int>,
            level: Int
        ): Map<String, Int> {
            
            val stats = mutableMapOf<String, Int>()
            
            // HP
            val baseHp = baseStats["hp"] ?: 0
            val ivHp = ivs["hp"] ?: 0
            val evHp = evs["hp"] ?: 0
            
            stats["hp"] = (
                ((2 * baseHp + ivHp + evHp / 4) * level) / 100.0
            ).toInt() + level + 10
            
            // Andere Stats
            val otherStats = listOf("attack", "defense", "sp_attack", "sp_defense", "speed")
            
            for (stat in otherStats) {
                val base = baseStats[stat] ?: 0
                val iv = ivs[stat] ?: 0
                val ev = evs[stat] ?: 0
                
                stats[stat] = (
                    ((2 * base + iv + ev / 4) * level) / 100.0
                ).toInt() + 5
            }
            
            return stats
        }
    }
    
    override fun toString(): String{
        return "Name:$name,Level:$level,\n,Types:$types,Stats:$stats"
    }
}