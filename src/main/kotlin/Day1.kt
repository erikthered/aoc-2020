import java.io.File

fun getInputLines(fileName: String) = File(ClassLoader.getSystemResource(fileName).toURI()).readLines()

fun findTwoAddends(candidates: List<Int>, sum: Int): Pair<Int, Int>? {
    for ((idx, entry) in candidates.withIndex()) {
        for (entry2 in candidates.subList(idx + 1, candidates.size)) {
            if (entry + entry2 == sum) {
                return entry to entry2
            }
        }
    }
    return null
}

fun findThreeAddends(candidates: List<Int>, sum: Int): Triple<Int, Int, Int>? {
    for ((idx, entry) in candidates.withIndex()) {
        for ((idx2, entry2) in candidates.subList(idx + 1, candidates.size).withIndex()) {
            for (entry3 in candidates.subList(idx + 1 + idx2, candidates.size)) {
                if (entry + entry2 + entry3 == sum) {
                    return Triple(entry, entry2, entry3)
                }
            }
        }
    }
    return null
}

fun main() {
    val entries = getInputLines("day1.txt").map { it.toInt() }

    findTwoAddends(entries, 2020)?.also { println(it.first * it.second) }

    findThreeAddends(entries, 2020)?.also { println(it.first * it.second * it.third) }
}