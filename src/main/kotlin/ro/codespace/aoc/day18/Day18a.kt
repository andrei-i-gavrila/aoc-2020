package ro.codespace.aoc.day18

import ro.codespace.aoc.day
import ro.codespace.aoc.log
import java.util.*


private fun evaluate(tokens: Queue<String>): Long {
    var currentOp: Char? = null
    var current: Long? = null

    while (tokens.isNotEmpty()) {
        val nextToken = tokens.poll()
        when {
            nextToken == "(" -> {
                current = evaluate(tokens).let {
                    if (current == null) {
                        it
                    } else {
                        (if (currentOp == '*') current!! * it else current!! + it).also { currentOp = null }

                    }
                }
            }
            nextToken == ")" -> {
                return current!!
            }
            current == null -> {
                current = nextToken.toLong()
            }
            currentOp == null -> {
                currentOp = nextToken.first()
            }
            else -> {
                current = if (currentOp == '*') current * nextToken.toLong() else current + nextToken.toLong()
                currentOp = null
            }
        }
    }
    return current!!
}


fun main() {
    day(18) {
        it.readLines().map {
            it
                    .replace("(", "( ")
                    .replace(")", " )")
                    .split(" ")
                    .let {
                        evaluate(LinkedList(it))
                    }
        }.sum().log()
    }
}