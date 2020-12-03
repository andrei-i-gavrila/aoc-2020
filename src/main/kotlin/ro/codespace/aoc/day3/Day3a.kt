package ro.codespace.aoc.day3

import ro.codespace.aoc.day

//kotlin sucks "123".split("") == ["1", "2", "3", ""] :\

fun main() {
    val pattern = day(3).readLines().map { it.toList() }
    var posX = 0
    var posY = 0
    val incX = 3
    val incY = 1
    var treeshit = 0
    while (posY <= pattern.size) {
        posX += incX
        posY += incY
        if (posY == pattern.size) break
        if (posX >= pattern[posY].size) {
            posX %= pattern[posY].size
        }
        if (pattern[posY][posX] == '#') treeshit++
    }
    println(treeshit)
}