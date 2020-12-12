package ro.codespace.aoc.day12

import ro.codespace.aoc.Vector2
import ro.codespace.aoc.day
import ro.codespace.aoc.log

fun main() {
    listOf(day("12t"), day(12)).forEach {
        var pos = Vector2(0, 0)
        var waypoint = Vector2(10, -1)
        it.readLines().forEach {
            val moveType = it.first()
            val argument = it.substring(1).toInt()

            when (moveType) {
                'F' -> pos += waypoint * argument
                'N' -> waypoint += Vector2.UP * argument
                'S' -> waypoint += Vector2.DOWN * argument
                'E' -> waypoint += Vector2.RIGHT * argument
                'W' -> waypoint += Vector2.LEFT * argument
                'R' -> repeat((360 - argument) / 90) { waypoint = waypoint.rotateClockwise90() }
                'L' -> repeat(argument / 90) { waypoint = waypoint.rotateClockwise90() }
            }
        }
        pos.manhattanScale.log()
    }
}