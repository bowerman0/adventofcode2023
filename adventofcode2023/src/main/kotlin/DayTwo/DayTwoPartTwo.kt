package DayTwo

import java.lang.Integer.max

class DayTwoPartTwo (gamesList : List<String>) {
    private val games = gamesList
    val RED_COLOR = "red"
    val GREEN_COLOR = "green"
    val BLUE_COLOR = "blue"

    fun totalPossible() : Int {
        var count = 0
        for (game in games) {
            val begin = game.indexOf(':')
            val picks = game.substring(begin+2)
            //println(picks)

            val (redCount, greenCount, blueCount) = gameColorCounts(picks)
            count += redCount * greenCount * blueCount
        }
        return count
    }

    private fun gameColorCounts(game : String) : Triple<Int, Int, Int>  {
        var redCount = 0
        var blueCount = 0
        var greenCount = 0
        var i = 0
        while (i < game.length) {
            val end = game.indexOf(';', i)
            val pick = if (end == -1) game.substring(i) else game.substring(i, end)
            //println(pick)
            val (pickRedCount, pickGreenCount, pickBlueCount) = pickColorCounts(pick)
            redCount = max(redCount, pickRedCount)
            greenCount = max(greenCount, pickGreenCount)
            blueCount = max(blueCount, pickBlueCount)
            i = if (end == -1) game.length else end + 2
        }

        return Triple(redCount, greenCount, blueCount)
    }

    private fun pickColorCounts(pick : String) : Triple<Int, Int, Int> {
        var redCount = 0
        var blueCount = 0
        var greenCount = 0
        val groups = pick.split(',')
        for (group in groups) {
            val countAndColor = group.trim().split(' ')
            val count = countAndColor[0].toInt()
            when(countAndColor[1]) {
                RED_COLOR -> redCount = count
                BLUE_COLOR -> blueCount = count
                GREEN_COLOR -> greenCount = count
                else -> {
                    throw IllegalStateException("$countAndColor not found!")
                }
            }
        }
        //println("possible: $pick")
        return Triple(redCount, greenCount, blueCount)
    }
}