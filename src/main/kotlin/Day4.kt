import java.util.*

fun main() {
    val input = getInputLines("day4.txt")
    val queue = ArrayDeque(input)
    var entryString = String()
    val passports = mutableListOf<Map<PassportField, String>>()
    while (!queue.isEmpty()) {
        val elem = queue.pop()
        if (elem.isNotBlank()) {
            entryString += " $elem"
        } else {
            passports.add(
                entryString.trim().split(" ")
                    .map { PassportField.valueOf(it.split(":")[0]) to it.split(":")[1] }.toMap()
            )
            entryString = ""
        }
    }
    passports.add(
        entryString.trim().split(" ")
            .map { PassportField.valueOf(it.split(":")[0]) to it.split(":")[1] }.toMap()
    )

    val candidates = passports.filter { it.keys.containsAll(PassportField.values().toList().minus(PassportField.cid)) }

    // Part 1
    println(candidates.count())

    // Part 2
    println(candidates.filter { passportValid(it) }.count())
}

fun passportValid(passport: Map<PassportField, String>): Boolean {
    for ((key, value) in passport.entries) {
        when (key) {
            PassportField.byr -> if (value.toInt() < 1920 || value.toInt() > 2002) return false
            PassportField.iyr -> if (value.toInt() < 2010 || value.toInt() > 2020) return false
            PassportField.eyr -> if (value.toInt() < 2020 || value.toInt() > 2030) return false
            PassportField.hgt -> if (!heightValid(value)) return false
            PassportField.hcl -> if (!"#([a-f\\d]{6})".toRegex().matches(value)) return false
            PassportField.ecl -> if (!EyeColor.values().map { it.toString() }.contains(value)) return false
            PassportField.pid -> if (!"\\d{9}".toRegex().matches(value)) return false
            else -> {}
        }
    }
    return true
}

fun heightValid(height: String): Boolean {
    return when (height.substring(height.lastIndex - 1)) {
        "in" -> height.substring(0, height.lastIndex - 1).toInt() in 59..76
        "cm" -> height.substring(0, height.lastIndex - 1).toInt() in 150..193
        else -> false
    }
}

enum class PassportField {
    byr,
    iyr,
    eyr,
    hgt,
    hcl,
    ecl,
    pid,
    cid
}

enum class EyeColor {
    amb,
    blu,
    brn,
    gry,
    grn,
    hzl,
    oth
}