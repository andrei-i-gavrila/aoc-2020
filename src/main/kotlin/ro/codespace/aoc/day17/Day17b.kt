package ro.codespace.aoc.day17

import ro.codespace.aoc.Vector3
import ro.codespace.aoc.Vector4
import ro.codespace.aoc.day

fun runCycle(activated: Set<Vector4>): Set<Vector4> {
    val count = mutableMapOf<Vector4, Int>()
    activated.forEach {
        it.neighboursL2.forEach {
            count.merge(it, 1, Int::plus)
        }
    }

    val newlyActivated = count.filter { it.value == 3 }.keys
    val deactivated = activated.filter { count[it] != 2 }.toSet()

    return activated - deactivated + newlyActivated
}

fun main() {
    day(17) {
        val start = it.readLines().map { it.toList() }

        var configuration = start.mapIndexed { x, row ->
            row.mapIndexed { y, element ->
                Vector4(x, y, 0, 0) to element
            }
        }.flatten().filter { it.second == '#' }.map { it.first }.toSet()

        repeat(6) {
            println(configuration.size)
            configuration = runCycle(configuration)
        }
        println(configuration.size)
        println()
    }
}