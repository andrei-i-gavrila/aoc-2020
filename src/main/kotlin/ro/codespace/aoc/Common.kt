package ro.codespace.aoc

import com.google.common.io.Resources
import com.google.common.math.IntMath
import java.io.BufferedReader
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

data class Vector3(val x: Int, val y: Int, val z: Int) {
    operator fun plus(vector3: Vector3) = Vector3(x + vector3.x, y + vector3.y, z + vector3.z)
    operator fun minus(vector3: Vector3) = Vector3(x - vector3.x, y - vector3.y, z - vector3.z)
    operator fun times(scale: Int) = Vector3(x * scale, y * scale, z * scale)
    operator fun div(scale: Int) = Vector3(x / scale, y / scale, z / scale)


    val neighboursL1 get() = listOf(this + LEFT, this + UP, this + RIGHT, this + DOWN, this + FRONT, this + BACK)

    val neighboursL2
        get() = (-1..1).flatMap { dx ->
            (-1..1).flatMap { dy ->
                (-1..1).map { dz ->
                    Vector3(dx, dy, dz)
                }
            }
        }.filter { it.manhattanScale != 0 }.map { this + it }

    val manhattanScale get() = abs(x) + abs(y) + abs(z)

    fun inBounds(topLeft: Vector3, bottomRight: Vector3): Boolean {
        return x in topLeft.x until bottomRight.x && y in topLeft.y until bottomRight.y && x in topLeft.z until bottomRight.z
    }

    fun inBounds(bottomRight: Vector3): Boolean {
        return inBounds(Vector3(0, 0, 0), bottomRight)
    }

    companion object {
        val LEFT = Vector3(-1, 0, 0)
        val RIGHT = Vector3(1, 0, 0)
        val FRONT = Vector3(0, 1, 0)
        val BACK = Vector3(0, -1, 0)
        val UP = Vector3(0, 0, 1)
        val DOWN = Vector3(0, 0, -1)
    }
}


data class Vector4(val x: Int, val y: Int, val z: Int, val w: Int) {
    operator fun plus(vector4: Vector4) = Vector4(x + vector4.x, y + vector4.y, z + vector4.z, w + vector4.w)
    operator fun minus(vector4: Vector4) = Vector4(x - vector4.x, y - vector4.y, z - vector4.z, w - vector4.w)
    operator fun times(scale: Int) = Vector4(x * scale, y * scale, z * scale, w * scale)
    operator fun div(scale: Int) = Vector4(x / scale, y / scale, z / scale, w / scale)


    val neighboursL2
        get() = (-1..1).flatMap { dx ->
            (-1..1).flatMap { dy ->
                (-1..1).flatMap { dz ->
                    (-1..1).map { dw ->
                        Vector4(dx, dy, dz, dw)
                    }
                }
            }
        }.filter { it.manhattanScale != 0 }.map { this + it }

    val manhattanScale get() = abs(x) + abs(y) + abs(z) + abs(w)

}


fun read(file: String) = Resources.getResource(file).openStream().bufferedReader()

fun day(number: Int) = read("day$number.txt")
fun day(number: String) = read("day$number.txt")
fun day(number: Int, testSuffix: String = "t", solver: (BufferedReader) -> Unit) = listOf(day("$number$testSuffix"), day(number)).forEach(solver)

fun Int.pow(n: Int): Int {
    return IntMath.pow(this, n)
}

fun Any.log(message: String = "") {
    if (message.isNotBlank()) println("$message: $this")
    else println(this)
}