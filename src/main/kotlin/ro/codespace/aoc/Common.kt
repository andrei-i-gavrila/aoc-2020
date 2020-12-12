package ro.codespace.aoc

import com.google.common.io.Resources
import com.google.common.math.IntMath
import kotlin.math.abs

data class Vector2(val x: Int, val y: Int) {
    operator fun plus(vector2: Vector2) = Vector2(x + vector2.x, y + vector2.y)
    operator fun minus(vector2: Vector2) = Vector2(x - vector2.x, y - vector2.y)
    operator fun times(scale: Int) = Vector2(x * scale, y * scale)
    operator fun div(scale: Int) = Vector2(x / scale, y / scale)


    val neighbours get() = listOf(this + LEFT, this + UP, this + RIGHT, this + DOWN)

    val manhattanScale get() = abs(x) + abs(y)

    fun rotateClockwise90(): Vector2 {
        return Vector2(y, -x)
    }

    fun inBounds(topLeft: Vector2, bottomRight: Vector2): Boolean {
        return x in topLeft.x until bottomRight.x && y in topLeft.y until bottomRight.y
    }

    fun inBounds(bottomRight: Vector2): Boolean {
        return inBounds(Vector2(0, 0), bottomRight)
    }

    companion object {
        val LEFT = Vector2(-1, 0)
        val RIGHT = Vector2(1, 0)
        val UP = Vector2(0, -1)
        val DOWN = Vector2(0, 1)
    }
}

fun read(file: String) = Resources.getResource(file).openStream().bufferedReader()

fun day(number: Int) = read("day$number.txt")
fun day(number: String) = read("day$number.txt")


fun Int.pow(n: Int): Int {
    return IntMath.pow(this, n)
}

fun Any.log() {
    println(this)
}