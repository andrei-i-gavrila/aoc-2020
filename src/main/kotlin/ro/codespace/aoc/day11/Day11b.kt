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

private fun inBounds(area: List<List<Char>>, y: Int, x: Int): Boolean {
    return y in 0..area.lastIndex && x in 0..area[0].lastIndex

}

private fun countNeighbours(area: List<List<Char>>, y: Int, x: Int): Int {
    val dX = listOf(0, 1, 1, 1, 0, -1, -1, -1)
    val dY = listOf(-1, -1, 0, 1, 1, 1, 0, -1)

    return dX.indices.count {
        val oX = dX[it]
        val oY = dY[it]
        var nX = x + oX
        var nY = y + oY
        while (inBounds(area, nY, nX)) {
            if (area[nY][nX] == '#') return@count true
            if (area[nY][nX] == 'L') return@count false
            nX += oX
            nY += oY
        }
        return@count false
    }
}

private fun step(area: List<List<Char>>): List<List<Char>> {
    val newArea = area.map { it.toMutableList() }

    for (y in area.indices) {
        for (x in area[y].indices) {
            if (area[y][x] != '.') {
                val countNeighbours = countNeighbours(area, y, x)
                if (countNeighbours >= 5) {
                    newArea[y][x] = 'L'
                } else if (area[y][x] == 'L' && countNeighbours == 0) {
                    newArea[y][x] = '#'
                }
            }
        }
    }
    return newArea
}
