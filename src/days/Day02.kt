package days

import utils.InputUtils
import kotlin.math.abs

object Day02 {

    fun firstStar(input: List<String>): Int {
        return input.map { line -> line.split(Regex("\\s+")).map { level -> level.toInt() } }
            .filter { report -> isIncreasingOrDecreasing(report) }
            .count { report ->
                val maxDifferenceInLevels = report.zipWithNext().maxOfOrNull { (a, b) -> abs(a - b) } ?: 0
                maxDifferenceInLevels <= 3
            }
    }

    fun secondStar(input: List<String>): Int {
        return input.asSequence().map { line -> line.split(Regex("\\s+")).map { level -> level.toInt() } }
            .map { report -> report.indices.map { report.withoutItemAt(it) } }
            .map { possibleReports -> possibleReports.filter { isIncreasingOrDecreasing(it) } }
            .filter { possibleReports -> possibleReports.isNotEmpty() }
            .count { possibleReports ->
                possibleReports.any { levels ->
                    val maxDifferenceInLevels = levels.zipWithNext().maxOfOrNull { (a, b) -> abs(a - b) } ?: 0
                    maxDifferenceInLevels <= 3
                }
            }
    }

    private fun isIncreasingOrDecreasing(list: List<Int>): Boolean {
        val isIncreasing = (0 until list.size - 1).fold(true) { result, i -> result && list[i] < list[i + 1] }
        val isDecreasing = (0 until list.size - 1).fold(true) { result, i -> result && list[i] > list[i + 1] }

        return isIncreasing || isDecreasing
    }

    private fun <T> Iterable<T>.withoutItemAt(index: Int): List<T> =
        filterIndexed { i, _ -> i != index }

}


fun main() {
    val input = InputUtils.readDayInput("02")

    println("First star - ${Day02.firstStar(input)}")
    println("Second star - ${Day02.secondStar(input)}")
}
