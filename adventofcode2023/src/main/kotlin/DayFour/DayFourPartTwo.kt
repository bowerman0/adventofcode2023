package DayFour

import java.lang.StringBuilder

class DayFourPartTwo (private val cards: List<String>, private val numberOfCandidates : Int, private val numberOfWinners : Int)  {
    val totalValue : Int by lazy {
        val cardsRemaining = MutableList(cards.size) { 1 }
        for (i in cards.indices) {
            var cardCount = 0
            val winnerSet = winners[i].toSet()
            for (candidate in candidates[i]) {
                if (winnerSet.contains(candidate)) {
                    ++cardCount
                }
            }
            for (j in i+1..i+cardCount) {
                cardsRemaining[j] += cardsRemaining[i]
            }
        }
        cardsRemaining.reduce{ acc, count -> acc + count }
    }

    private val winners : List<List<Int>> by lazy {
        val sb = StringBuilder()
        sb.append("^.*\\|")
        for (i in 0 until numberOfWinners) {
            sb.append("\\s+(\\d+)")
        }
        sb.append("\\s*$")
        //val pattern = "^.*\\|\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s*$".toRegex()
        parse(sb.toString().toRegex(), numberOfWinners)
    }

    private val candidates : List<List<Int>> by lazy {
        val sb = StringBuilder()
        sb.append("^Card\\s+\\d+:")
        for (i in 0 until numberOfCandidates) {
            sb.append("\\s+(\\d+)")
        }
        sb.append("\\s*\\|.*$")
        //val pattern = "^Card \\d+:\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+\\|.*$".toRegex()
        parse(sb.toString().toRegex(), numberOfCandidates)
    }

    private fun parse(regex: Regex, count: Int) : List<List<Int>> {
        val candidateList = mutableListOf<List<Int>>()
        for (line in cards) {
            val match = regex.matchEntire(line) ?: throw IllegalStateException("line didn't match:\n$line\n$regex")
            val candidates = mutableListOf<Int>()
            for (groupId in 1..count) {
                candidates.add(match.groupValues[groupId].toInt())
            }
            candidateList.add(candidates)
        }
        return candidateList
    }
}