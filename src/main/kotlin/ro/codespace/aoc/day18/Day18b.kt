package ro.codespace.aoc.day18

import ro.codespace.aoc.day
import ro.codespace.aoc.log
import java.util.*

val OPS = mapOf(
        "*" to 0, "+" to 1
)

val revOPS = mapOf(
        0 to "*",
        1 to "+",
)

fun infixToPostfix(tokens: List<String>): List<String> {
    val pfn = mutableListOf<String>()
    val opStack = Stack<String>()
    for (token in tokens) {
        if (token.isEmpty()) continue
//        token.log()
        // check for operator
        if (token in OPS) {
            if (opStack.isEmpty()) {
                opStack.push(token)
            } else {
                val prec1 = OPS[token]!!
                while (!opStack.isEmpty()) {
                    val prec2 = OPS[opStack.peek()] ?: -1
                    if (prec2 >= prec1) {
                        pfn.add(opStack.pop())
                    } else break
                }
                opStack.push(token)
            }
        } else if (token == "(") {
            opStack.push("(")
        } else if (token == ")") {
            while (opStack.peek() != "(") pfn.add(opStack.pop())
            opStack.pop()
        } else {
            pfn.add(token)
        }
    }
    while (!opStack.isEmpty()) {
        val op = opStack.pop()
        if (op != "(") pfn.add(op)
    }
    return pfn
}

fun processPost(expr: List<String>): Long {
    val stack = Stack<Long>()
    for (token in expr) {
        when (token) {
            "+" -> stack.add(stack.pop() + stack.pop())
            "*" -> stack.add(stack.pop() * stack.pop())
            else -> stack.add(token.toLong())
        }
    }
    return stack.pop()
}

fun main() {
    day(18) {
        it.readLines().map {
            it
                    .replace("(", "( ")
                    .replace(")", " )")
                    .let {
                        infixToPostfix(it.split(" "))
                    }.let {
                        processPost(it)
                    }
        }.sum().log()
    }
}