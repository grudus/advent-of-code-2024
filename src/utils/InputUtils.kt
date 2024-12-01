package utils

import java.io.File

object InputUtils {

    fun readDayInput(day: String): List<String> =
        File("resources/day$day/input.txt").readLines()

}
