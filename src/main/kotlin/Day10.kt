fun main() {
    val input = getInputLines("day10.txt").map(String::toInt)

    val candidates = input.sorted()

    var joltage = 0
    val target = candidates.maxOrNull()!! + 3

    val differences = mutableMapOf<Int, Int>()

    differences[candidates.minOrNull()!!] = 1
    differences[3] = 1

    candidates
        .windowed(2, 1)
        .forEach {
            val diff = it[1] - it[0]
            differences[diff] = differences.computeIfAbsent(diff) { 0 } + 1
        }

    println("Part 1: ${differences.get(1)} * ${differences.get(3)} = ${differences[1]!! * differences[3]!!}")
}