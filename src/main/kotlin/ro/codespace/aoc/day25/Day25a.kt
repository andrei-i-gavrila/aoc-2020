package ro.codespace.aoc.day25

import ro.codespace.aoc.log
import java.math.BigInteger

fun doLoop(value: BigInteger, subject: BigInteger): BigInteger {
    return value.multiply(subject).mod(BigInteger.valueOf(20201227))
}

fun findLoopSize(target: BigInteger): Int {
    var value = 1L.toBigInteger()
    var loop = 0
    while (value != target) {
        value = doLoop(value, 7.toBigInteger())
        loop++
    }
    return loop
}


fun main() {
    val keyDoor = 9093927L.toBigInteger()
    val keyCard = 11001876L.toBigInteger()

    val cardLoop = findLoopSize(keyCard)
    val doorLoop = findLoopSize(keyDoor)

    cardLoop.log()
    doorLoop.log()

    var valueDoor = 1.toBigInteger()
    repeat(doorLoop) {
        valueDoor = doLoop(valueDoor, keyCard)
    }

    var valueCard = 1.toBigInteger()
    repeat(cardLoop) {
        valueCard = doLoop(valueCard, keyDoor)
    }

    valueDoor.log()
    valueCard.log()
}