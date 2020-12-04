package ro.codespace.aoc.day4


fun main() {
    val passports = readPassports()

    val fields = mapOf<String, (String) -> Boolean>(
            "byr" to { it.toInt() in 1920..2002 },
            "iyr" to { it.toInt() in 2010..2020 },
            "eyr" to { it.toInt() in 2020..2030 },
            "hgt" to {
                val height = it.substring(0..(it.length - 3))
                val unit = it.substring(it.length - 2)
                when (unit) {
                    "cm" -> height.toInt() in 150..193
                    "in" -> height.toInt() in 59..76
                    else -> false
                }
            },
            "hcl" to { it.matches(Regex("#[0-9a-f]{6}")) },
            "ecl" to { it in setOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth") },
            "pid" to { it.matches(Regex("\\d{9}")) },
    )

    val valid = passports.count {
        val passportData = it.flatMap { it.split(" ") }.map { it.split(":") }.map { it[0] to it[1] }.toMap()
        fields.all {
            if (it.key !in passportData) false
            else try {
                it.value(passportData[it.key]!!)
            } catch (e: Exception) {
                false
            }
        }
    }


    println(valid)
}