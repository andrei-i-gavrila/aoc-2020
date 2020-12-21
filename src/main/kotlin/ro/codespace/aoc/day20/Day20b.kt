package ro.codespace.aoc.day20

import ro.codespace.aoc.day
import ro.codespace.aoc.log
import ro.codespace.aoc.readGroups

fun main() {
    day(20) {
        val neighbours = mutableMapOf<Long, MutableSet<Long>>()

        it.readLines().readGroups().map {
            val tileId = it.first().substring(5, it.first().length - 1).toLong()
            val content = it.drop(1).map { it.toList() }
            Tile(tileId, content)
        }.flatMap { tile ->
            tile.borders.map {
                it to tile.id
            }
        }.groupBy({ it.first }, { it.second }).forEach { (t, u) ->
            if (u.size > 1) {
                if (u[0] !in neighbours) neighbours[u[0]] = mutableSetOf()
                if (u[1] !in neighbours) neighbours[u[1]] = mutableSetOf()

                neighbours[u[0]]!!.add(u[1])
                neighbours[u[1]]!!.add(u[0])
            }
        }

        neighbours.entries.sortedBy { it.value.size }.forEach {
            "${it.key} -> ${it.value.joinToString(", ")}".log()


        }
        repeat(4) { println() }
    }
}