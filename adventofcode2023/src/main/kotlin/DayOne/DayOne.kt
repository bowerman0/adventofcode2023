package DayOne

class DayOne (map : List<String>) {
    val totalCalibrations : Int by lazy {
        calibrations.reduce {acc, calibration -> acc + calibration}
    }

    private val calibrations : List<Int> by lazy {
        map.map {
            val first = firstDigit(it)
            val last = lastDigit(it)
            //println("$first $last")
            10 * first + last
        }
    }

    private fun firstDigit (x : String) : Int {
        for (c in x) {
            val d = c.digitToIntOrNull()
            if (d != null) {
                return d
            }
        }
        throw IllegalStateException("No first digit in string $x")
    }

    private fun lastDigit (x : String) : Int {
        for (c in x.reversed()) {
            val d = c.digitToIntOrNull()
            if (d != null) {
                return d
            }
        }
        throw IllegalStateException("No last digit in string $x")
    }
}