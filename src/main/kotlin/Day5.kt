fun main() {
    val input = getInputLines("day5.txt")

    println()
    println("Part 1")
    input.map { computeRow(it.substring(0, 7)) to computeSeat(it.substring(7)) }
        .map { it.first * 8 + it.second }.maxOrNull().also(::println)

    println()
    println("Part 2")
    val seatIds = input.map { computeRow(it.substring(0, 7)) to computeSeat(it.substring(7)) }
        .map { it.first * 8 + it.second }.sorted()

    (seatIds.minOrNull()!!..seatIds.maxOrNull()!!).minus(seatIds).forEach(::println)
}

fun computeRow(rowString: String): Int {
    var range = (0..127).toList()
    for(c in rowString) {
        range = when(c) {
            'F' -> range.subList(0, range.size / 2)
            'B' -> range.subList(range.size / 2, range.size)
            else -> throw Exception("Invalid input $c")
        }
    }
    return range.first()
}

fun computeSeat(seatString: String): Int {
    var range = (0..7).toList()
    for(c in seatString) {
        range = when(c) {
            'L' -> range.subList(0, range.size / 2)
            'R' -> range.subList(range.size / 2, range.size)
            else -> throw Exception("Invalid input $c")
        }
    }
    return range.first()
}