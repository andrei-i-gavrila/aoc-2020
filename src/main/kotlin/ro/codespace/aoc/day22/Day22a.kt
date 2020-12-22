package ro.codespace.aoc.day22

import ro.codespace.aoc.day
import ro.codespace.aoc.log
import ro.codespace.aoc.readGroups


fun calcScore(deck: ArrayDeque<Int>): Int {
    return deck.mapIndexed { index, i -> i * (deck.size - index) }.also { it.log() }.sum()
}

fun main() {
    day(22) {
        val (playerA, playerB) = it.readLines().readGroups().map {
            it.drop(1).map { it.toInt() }.let {
                ArrayDeque(it)
            }
        }
        while (true) {
            val topA = playerA.removeFirst()
            val topB = playerB.removeFirst()

            if (topA > topB) {
                playerA.addLast(topA)
                playerA.addLast(topB)
            } else {
                playerB.addLast(topB)
                playerB.addLast(topA)
            }

            if (playerA.isEmpty()) {
                playerB.log()
                calcScore(playerB).log()
                break
            }
            if (playerB.isEmpty()) {
                playerA.log()
                calcScore(playerA).log()
                break
            }
        }
    }
}