package ro.codespace.aoc.day4

import ro.codespace.aoc.day


fun main() {
    val passports = readPassports()

    val fields = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

    val valid = passports.count {
        val passportData = it.flatMap { it.split(" ") }.map { it.split(":") }.map { it[0] to it[1] }.toMap()
        fields.all { it in passportData }
    }


    println(valid)
}

fun readPassports(): MutableList<MutableList<String>> {
    val passports = mutableListOf<MutableList<String>>(mutableListOf())
    day(4).readLines().forEach {
        if (it.isEmpty()) {
            passports.add(mutableListOf())
        } else {
            passports.last().add(it)
        }
    }
    return passports
}