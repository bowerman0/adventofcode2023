package DayTwo


class DayTwo (gamesList : List<String>) {
    private val games = gamesList
    val RED_COLOR = "red"
    val GREEN_COLOR = "green"
    val BLUE_COLOR = "blue"

    val RED_COUNT = 12
    val GREEN_COUNT = 13
    val BLUE_COUNT = 14
    fun totalPossible() : Int {
        var count = 0
        for (game in games) {
            val begin = game.indexOf(':')
            val gameId = game.substring(5,begin).toInt()
            val picks = game.substring(begin+2)
            //println(picks)

            if (isGamePossible(picks)) {
//                println("    possible: $game")
//                println("$gameId")
                count += gameId
            }
        }
        return count
    }

    private fun isGamePossible(game : String) : Boolean {
        var i = 0
        while (i < game.length) {
            val end = game.indexOf(';', i)
            val pick = if (end == -1) game.substring(i) else game.substring(i, end)
            //println(pick)
            if (!isPickPossible(pick)) {
//                println("not possible: $game")
                return false
            }
            i = if (end == -1) game.length else end + 2
        }

        return true
    }

    private fun isPickPossible(pick : String) : Boolean {
        val groups = pick.split(',')
        for (group in groups) {
            val countAndColor = group.trim().split(' ')
            val count = countAndColor[0].toInt()
            when(countAndColor[1]) {
                RED_COLOR -> if (RED_COUNT < count) {
//                    println("red $RED_COUNT < $count")
                    return false
                }
                BLUE_COLOR -> if (BLUE_COUNT < count) {
//                    println("blue $BLUE_COUNT < $count")
                    return false
                }
                GREEN_COLOR -> if (GREEN_COUNT < count) {
//                    println("green $GREEN_COUNT < $count")
                    return false
                }
                else -> {
                    throw IllegalStateException("$countAndColor not found!")
                }
            }
        }
        //println("possible: $pick")
        return true
    }
}