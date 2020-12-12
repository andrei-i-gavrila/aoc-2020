package ro.codespace.aoc.day12

import ro.codespace.aoc.Vector2
import ro.codespace.aoc.day
import ro.codespace.aoc.log

fun main() {
    listOf(day("12t"), day(12)).forEach {
        var pos = Vector2(0, 0)
        var dir = Vector2.RIGHT

        it.readLines().forEach {
            val moveType = it.first()
            val argument = it.substring(1).toInt()

            when (moveType) {
                'F' -> pos += dir * argument
                'N' -> pos += Vector2.UP * argument
                'S' -> pos += Vector2.DOWN * argument
                'E' -> pos += Vector2.RIGHT * argument
                'W' -> pos += Vector2.LEFT * argument
                'R' -> repeat((360 - argument) / 90) { dir = dir.rotateClockwise90() }
                'L' -> repeat(argument / 90) { dir = dir.rotateClockwise90() }
            }
        }
        pos.manhattanScale.log()
    }
}