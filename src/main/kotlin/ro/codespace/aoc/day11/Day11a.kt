package ro.codespace.aoc.day11

import ro.codespace.aoc.day
import ro.codespace.aoc.log


fun main() {
    listOf(day("11t"), day(11)).forEach {
        var area = it.readLines().map { it.toList() }
        val configurations = mutableSetOf<Int>()
        while (area.hashCode() !in configurations) {
            configurations.add(area.hashCode())
            area = step(area)
        }
        area.flatMap { it }.count { it == '#' }.log()
    }
}

private fun countNeighbours(area: List<List<Char>>, y: Int, x: Int): Int {
    val dX = listOf(0, 1, 1, 1, 0, -1, -1, -1)
    val dY = listOf(-1, -1, 0, 1, 1, 1, 0, -1)

    return dX.indices.filter {
        (y + dY[it]) in 0..area.lastIndex && (x + dX[it]) in 0..area[0].lastIndex
    }.count {area[y + dY[it]][x + dX[it]] == '#'}
}

private fun step(area: List<List<Char>>): List<List<Char>> {
    val newArea = area.map { it.toMutableList() }

    for (y in area.indices) {
        for (x in area[y].indices) {
            if (area[y][x] != '.') {
                val countNeighbours = countNeighbours(area, y, x)
                if (countNeighbours >= 4) {
                    newArea[y][x] = 'L'
                } else if (area[y][x] == 'L' && countNeighbours == 0) {
                    newArea[y][x] = '#'
                }
            }
        }
    }
    return newArea
}
