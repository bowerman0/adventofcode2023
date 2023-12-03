package DayOne

class DayOnePartTwo  (map : List<String>) {
    val totalCalibrations : Long by lazy {
        calibrations.fold(0L) {acc, calibration -> acc + calibration}
    }

    private val calibrations : List<Int> by lazy {
        map.map {
            val first = firstDigit(it)
            val last = lastDigit(it)
            println("$it: $first $last")
            10 * first + last
        }
    }

    private fun firstDigit (x : String) : Int {
        val pattern = "(?:one|two|three|four|five|six|seven|eight|nine|zero)\\s*?".toRegex()
        val found = pattern.find(x)
        return if (found != null) {
            val m = found.value
            val idx = found.range
            val (numIndex, numVal) = firstDigitNumber(x)
            if (numIndex < idx.first) {
                numVal
            } else {
                //println("'$m' found at indexes $idx in '$x'")
                digitFromWord(m)
            }
        } else {
            firstDigitNumber(x).second
        }
    }

    private fun firstDigitNumber (x : String) : Pair<Int, Int> {
        for ((idx, c) in x.withIndex()) {
            val d = c.digitToIntOrNull()
            if (d != null) {
                return Pair(idx, d)
            }
        }
        return Pair(Int.MAX_VALUE, 0)
    }

    private fun lastDigit (x : String) : Int {
        val pattern = "(?:one|two|three|four|five|six|seven|eight|nine|zero)\\s*?".toRegex()
        val matches = pattern.findAll(x)
        val found = matches.lastOrNull()
        return if (found != null) {
            val m = found.value
            val idx = found.range
            val (numIndex, numVal) = lastDigitNumber(x)
            return if (idx.first < numIndex) {
                //println("'$m' found at indexes $idx in '$x'")
                numVal
            } else {
                val doublePattern = "(?:oneight|twone|threeight|fiveight|sevenine|eightwo|eighthree|nineight|zerone)\\s*?".toRegex()
                val doubleMatches = doublePattern.findAll(x)
                val doubleFound = doubleMatches.lastOrNull()

                if (doubleFound == null || doubleFound.range.first < found.range.first) {
                    digitFromWord(m)
                } else {
                    digitFromWordDouble(doubleFound.value)
                }
            }
        } else {
            lastDigitNumber(x).second
        }
    }

    private fun lastDigitNumber (x : String) : Pair<Int, Int> {
        for ((idx, c) in x.reversed().withIndex()) {
            val d = c.digitToIntOrNull()
            if (d != null) {
                //println("index $idx for $c")
                return Pair(x.length - idx, d)
            }
        }
        return Pair(Int.MIN_VALUE, 0)
    }

    private fun digitFromWordDouble(x : String) : Int {
        return when(x) {
            "oneight" -> 8
            "twone" -> 1
            "threeight" -> 8
            "fiveight" -> 8
            "sevenine" -> 9
            "eightwo" -> 2
            "eighthree" -> 3
            "nineight" -> 8
            "zerone" -> 1
            else -> {
                throw IllegalStateException("No digit for $x")
            }
        }
    }

    private fun digitFromWord (x : String) : Int {
        return when (x) {
            "zero" -> 0
            "one" -> 1
            "two" -> 2
            "three" -> 3
            "four" -> 4
            "five" -> 5
            "six" -> 6
            "seven" -> 7
            "eight" -> 8
            "nine" -> 9
            else -> {
                throw IllegalStateException("No digit for $x")
            }
        }
    }
}