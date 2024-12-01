package days

import utils.InputUtils
import kotlin.math.abs

object Day01 {

    fun firstStar(input: List<String>): Int {
        val (left, right) = parseLocationIdLists(input)

        return left.indices.sumOf { i -> abs(left[i] - right[i]) }
    }

    fun secondStar(input: List<String>): Int {
        val (left, right) = parseLocationIdLists(input)
        val similarityCache = mutableMapOf<Int, Int>()

        return left.sumOf { locationId ->
            val similarity = similarityCache.getOrPut(locationId) {
                right.count { it == locationId }
            }
            similarity * locationId
        }
    }

    private fun parseLocationIdLists(rawInputLines: List<String>): Pair<List<Int>, List<Int>> =
        rawInputLines
            .map { line -> line.split(Regex("\\s+")).map { locationId -> locationId.toInt() } }
            .fold(listOf<Int>() to listOf<Int>()) { (leftList, rightList), (leftItem, rightItem) ->
                leftList + leftItem to rightList + rightItem
            }.let { (leftList, rightList) -> leftList.sorted() to rightList.sorted() }

}


fun main() {
    val input = InputUtils.readDayInput("01")

    println("First star - ${Day01.firstStar(input)}")
    println("Second star - ${Day01.secondStar(input)}")
}
