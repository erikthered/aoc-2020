fun main() {
    val input = getInputLines("day9.txt").map(String::toLong)
    var cursor = 24
    var exit = false

    do {
        cursor += 1
        val sum = input[cursor]
        val candidates = input.subList(cursor - 25, cursor)
        var foundCandidates = false

        loop@ for ((i1, c1) in candidates.withIndex()) {
            for ((i2, c2) in candidates.withIndex()) {
                if (i1 != i2 && c1 + c2 == sum) {
                    foundCandidates = true
                    break@loop
                }
            }
        }

        if (!foundCandidates) {
            exit = true
        }
    } while (!exit)

    println("Part 1: ${input[cursor]}")

    val sum = input[cursor]

    loop@ for (i1 in input.indices) {
        for (i2 in (i1+1)..input.size) {
            val slice = input.slice(i1..i2)
            if (slice.sum() == sum) {
                println("Part 2: Min: ${slice.minOrNull()}, Max: ${slice.maxOrNull()}, Sum: ${(slice.minOrNull() ?: 0) + (slice.maxOrNull() ?: 0)}")
                break@loop
            }
            if (slice.sum() > sum) {
                break
            }
        }
    }
}