import DayOne.DayOne
import DayOne.DayOnePartTwo
import DayTwo.DayTwo
import InputReader.StringFileInputReader

fun main(args: Array<String>) {
    val options = getOptions(args)
    val dayOptions = options["-day"]
    when(val day: Int? = dayOptions?.getOrNull(0)?.toInt()) {
        0 -> println("Day ?: ${DayOnePartTwo(StringFileInputReader("out/production/adventofcode2023/test.txt").fileData).totalCalibrations}")
        1 -> {
            when(val dayPart: Int? = dayOptions.getOrNull(1)?.toInt()) {
                0 -> println("Day 1: ${DayOne(StringFileInputReader("out/production/adventofcode2023/day1example.txt").fileData).totalCalibrations}")
                1 -> println("Day 1: ${DayOne(StringFileInputReader("out/production/adventofcode2023/day1.txt").fileData).totalCalibrations}")
                2 -> println("Day 1: ${DayOnePartTwo(StringFileInputReader("out/production/adventofcode2023/day1part2example.txt").fileData).totalCalibrations}")
                3 -> println("Day 1: ${DayOnePartTwo(StringFileInputReader("out/production/adventofcode2023/day1part2.txt").fileData).totalCalibrations}")
                else -> {
                    println("day $day part? $dayPart")
                }
            }
        }
        2 -> {
            when(val dayPart: Int? = dayOptions.getOrNull(1)?.toInt()) {
                0 -> println("Day 2: ${DayTwo(StringFileInputReader("out/production/adventofcode2023/day2example.txt").fileData).totalPossible()}")
                1 -> println("Day 2: ${DayTwo(StringFileInputReader("out/production/adventofcode2023/day2.txt").fileData).totalPossible()}")
                else -> {
                    println("day $day part? $dayPart")
                }
            }
        }
        else -> {
            println("what day is it? $day")
        }
    }
}

fun getOptions(args: Array<String>): Map<String, List<String>> = args.fold(mutableListOf()) {
        acc: MutableList<MutableList<String>>, s: String ->
    acc.apply {
        if (s.startsWith('-')) add(mutableListOf(s))
        else last().add(s)
    }
}.associate { it[0] to it.drop(1) }