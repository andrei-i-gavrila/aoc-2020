package ro.codespace.aoc.day24

import ro.codespace.aoc.Vector2
import ro.codespace.aoc.day
import ro.codespace.aoc.log
import java.util.*
import kotlin.collections.ArrayDeque

fun Vector2.hexNeighbours(): List<Vector2> {
    return listOf(
            this + Vector2(2, 0), // e
            this + Vector2(-2, 0), // w
            this + Vector2(1, 1), // se
            this + Vector2(-1, 1), // sw
            this + Vector2(1, -1), // ne
            this + Vector2(-1, -1), // nw
    )
}


fun runCycle(activated: Set<Vector2>): Set<Vector2> {
    val count = mutableMapOf<Vector2, Int>()
    activated.forEach {
        it.hexNeighbours().forEach {
            count.merge(it, 1, Int::plus)
        }
    }

    val newlyActivated = count.filter { it.value == 2 }.keys
    val deactivated = activated.filter { it !in count || count[it]!! > 2 }.toSet()

    return activated - deactivated + newlyActivated
}


fun getTile(hexDirections: String): Vector2 {
    var position = Vector2(0, 0)
    val directionQueue = ArrayDeque(hexDirections.toList())

    while (directionQueue.isNotEmpty()) {
        when (directionQueue.removeFirst()) {
            'e' -> position += Vector2(2, 0)
            'w' -> position += Vector2(-2, 0)
            's' -> when (directionQueue.removeFirst()) {
                'e' -> position += Vector2(1, 1)
                'w' -> position += Vector2(-1, 1)
            }
            'n' -> when (directionQueue.removeFirst()) {
                'e' -> position += Vector2(1, -1)
                'w' -> position += Vector2(-1, -1)
            }
        }
    }
    return position
}


/*
*  nw ne
* w  c  e
*  sw se
* */

fun main() {
    day(24) {
        val blackTiles = mutableSetOf<Vector2>()
        it.readLines().map { getTile(it) }.forEach {
            if (it in blackTiles) {
                blackTiles.remove(it)
            } else {
                blackTiles.add(it)
            }
        }
        blackTiles.size.log()
//        blackTiles.forEach{it.log()}
        var state = blackTiles.toSet()
//
//        var state = setOf(Vector2(1, 0), Vector2(-1, 0))
        repeat(100) {
            state = runCycle(state)
//            state.forEach { it.log() }
        }
        state.size.log()
    }
}