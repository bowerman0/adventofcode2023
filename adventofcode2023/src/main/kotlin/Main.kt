import dayOne.DayOne

fun main(args: Array<String>) {
    when(val day: Int? = getOptions(args)["-day"]?.getOrNull(0)?.toInt()) {
        1 -> println("Day 1: ${DayOne().totalCalibrations}")
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