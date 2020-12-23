package ro.codespace.aoc.day23

import ro.codespace.aoc.day
import ro.codespace.aoc.log


class Node(val value: Int, var next: Node?)


fun move(current: Node, refMap: Map<Int, Node>, max: Int): Node {
    val pick1 = current.next!!
    val pick2 = pick1.next!!
    val pick3 = pick2.next!!

    var destination = current.value
    while (destination in setOf(current.value, pick1.value, pick2.value, pick3.value)) {
        destination--
        if (destination == 0) destination = max
    }

    val destinationNode = refMap[destination] ?: error("${destination} not in refmap")


    current.next = pick3.next
    pick3.next = destinationNode.next
    destinationNode.next = pick1

    return current.next!!
}


fun makeLL(cups: List<Int>): Pair<Node, Map<Int, Node>> {
    var current = Node(cups.last(), null)
    val refMap = mutableMapOf(cups.last() to current)

    for (i in cups.lastIndex - 1 downTo 0) {
        val newNode = Node(cups[i], current)
        refMap[cups[i]] = newNode
        current = newNode
    }

    refMap[cups.last()]!!.next = current

    return current to refMap
}


fun getTo1(current: Node): Node {
    if (current.value == 1) return current
    return getTo1(current.next!!)
}

fun getConfigString(current: Node): String {
    if (current.value == 1) {
        return ""
    }
    return "${current.value}${getConfigString(current.next!!)}"
}

fun main() {
    day(23) {
        val initialState = it.readLine().toList().map { it.toString().toInt() }

        var (state, ref) = makeLL(initialState)
        val stateMax = 9
        repeat(100) {
            state = move(state, ref, stateMax)
        }
        getConfigString(ref[1]!!.next!!).log()



        var (extendedState, extendedRef) = makeLL(initialState + (10..1000000))
        val extendedStateMax = 1000000
        repeat(10000000) {
            extendedState = move(extendedState, extendedRef, extendedStateMax)
        }
        extendedRef[1]!!.next!!.let {
            it.value.toLong() * it.next!!.value
        }.log()
    }
}