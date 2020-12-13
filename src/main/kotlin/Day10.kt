fun main() {
    val input = getInputLines("day10.txt").map(String::toInt)

    val joltages = listOf(0) + input.sorted() + listOf(input.maxOrNull()!! + 3)
    val differences = mutableMapOf<Int, Int>()

    joltages
        .windowed(2, 1)
        .forEach {
            val diff = it[1] - it[0]
            differences[diff] = differences.computeIfAbsent(diff) { 0 } + 1
        }

    println("Part 1: ${differences[1]} * ${differences[3]} = ${differences[1]!! * differences[3]!!}")

    val combinations = mutableMapOf<Int, Long>()
    joltages.forEach { joltage ->
        combinations[joltage] = if (joltage == 0) {
            1
        } else {
            joltages
                .filter { it < joltage && it >= joltage - 3 }
                .map { combinations[it] ?: 1 }
                .sum()
        }
    }
    println(combinations[joltages.last()])
}