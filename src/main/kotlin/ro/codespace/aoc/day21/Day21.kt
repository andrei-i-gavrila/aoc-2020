package ro.codespace.aoc.day21

import ro.codespace.aoc.day
import ro.codespace.aoc.log

fun main() {
    day(21) {
        val allergenMap = mutableMapOf<String, MutableList<Set<String>>>()
        val ingredientTotals = mutableListOf<String>()
        it.readLines().forEach {
            val (ingredients, allergens) = it.split(" (").let {
                it[0].split(" ").toSet() to it[1].substring(9, it[1].length - 1).split(", ").toSet()
            }

            ingredientTotals.addAll(ingredients)
            allergens.forEach {
                if (it !in allergenMap) allergenMap[it] = mutableListOf()
                allergenMap[it]!!.add(ingredients)
            }
        }
        val knownIngredients = mutableSetOf<String>()
        val allergens = mutableMapOf<String, String>()

        while (allergens.size != allergenMap.size) {
            allergenMap.forEach { entry ->
                entry.value.reduce { acc, set ->
                    acc.intersect(set)
                }.minus(knownIngredients).let {
                    if (it.size == 1) {
                        allergens[it.first()] = entry.key
                        knownIngredients.add(it.first())
                    }
                }
            }

        }
        val ingredientCount = mutableMapOf<String, Int>()
        ingredientTotals.forEach {
            ingredientCount.merge(it, 1, Int::plus)
        }

        //part 1
        ingredientCount.filter { it.key !in allergens }.map { it.value }.sum().log()

        //part 2
        allergens.entries.sortedBy { it.value }.map { it.key }.joinToString(",").log()

    }
}