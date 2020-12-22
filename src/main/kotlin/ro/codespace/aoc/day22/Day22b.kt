package ro.codespace.aoc.day22

import ro.codespace.aoc.day
import ro.codespace.aoc.log
import ro.codespace.aoc.readGroups

fun playGame(deckA: ArrayDeque<Int>, deckB: ArrayDeque<Int>): Boolean {

    val configurations = hashSetOf<String>()
    while (true) {
        val configuration = deckA.joinToString(",") + "#" + deckB.joinToString(",")
        if (configuration in configurations) {
            return true
        }
        configurations.add(configuration)

        val topA = deckA.removeFirst()
        val topB = deckB.removeFirst()

        val aWins = if (topA <= deckA.size && topB <= deckB.size) {
//            println("subgame")
//            deckA.log()
//            deckB.log()
            playGame(ArrayDeque(deckA.subList(0, topA)), ArrayDeque(deckB.subList(0, topB)))
        } else {
            topA > topB
        }

        if (aWins) {
            deckA.addLast(topA)
            deckA.addLast(topB)
        } else {
            deckB.addLast(topB)
            deckB.addLast(topA)
        }



        if (deckA.isEmpty()) {
            return false
        }
        if (deckB.isEmpty()) {
            return true
        }
    }
}


fun main() {
    day(22, "t2") {
        val (playerA, playerB) = it.readLines().readGroups().map {
            it.drop(1).map { it.toInt() }.let {
                ArrayDeque(it)
            }
        }

        playGame(playerA, playerB).let {
            if (it) {
                playerA.log()
                calcScore(playerA).log()
            } else {
                playerB.log()
                calcScore(playerB).log()
            }
        }

    }
}