package ro.codespace.aoc.day3

import ro.codespace.aoc.day

fun main() {
    val pattern = day(3).readLines().map { it.toList() }

    listOf(1 to 1, 3 to 1, 5 to 1, 7 to 1, 1 to 2).foldRight(1L) { pair, acc ->
        acc * checkSlope(pattern, pair.first, pair.second)
    }.let {
        println(it)
    }
}

private fun checkSlope(pattern: List<List<Char>>, incX: Int, incY: Int): Long {
    var posY = 0
    var posX = 0
    var treeshit = 0L
    while (posY <= pattern.size) {
        posX += incX
        posY += incY
        if (posY >= pattern.size) break
        if (posX >= pattern[posY].size) {
            posX %= pattern[posY].size
        }
        if (pattern[posY][posX] == '#') treeshit++
    }
    return treeshit
}