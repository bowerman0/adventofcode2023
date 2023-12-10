package DayThree

class DayThreePartTwo (private val schematic: List<String>)  {
    val schematicTotal : Int by lazy {
        var total = 0
        for ((i, row) in schematic.withIndex()) {
            //println("row: $row")
            for ((j, _) in row.withIndex()) {
                if (isGear(i, j)) {
                    total += computeGearRatio(i, j) ?: 0
                }
            }
        }
        total
    }

    private fun isGear(row : Int, col : Int) : Boolean {
        return schematic[row][col] == '*'
    }

    private fun computeGearRatio(row : Int, col : Int) : Int? {
        val parts = mutableListOf<Int>()
        if (col+1 < schematic[row].length && schematic[row][col+1].isDigit()) {
            parts.add(getNumber(row, col+1))
        }
        if (0 <= col-1 && schematic[row][col-1].isDigit()) {
            parts.add(getNumber(row, col-1))
        }

        if (row+1 < schematic.size) {
            if (schematic[row+1][col].isDigit()) {
                parts.add(getNumber(row+1, col))
            } else {
                if (col+1 < schematic[row+1].length && schematic[row+1][col+1].isDigit()) {
                    parts.add(getNumber(row+1, col+1))
                }
                if (0 <= col-1 && schematic[row+1][col-1].isDigit()) {
                    parts.add(getNumber(row+1, col-1))
                }
            }
        }

        if (0 <= row-1) {
            if (schematic[row-1][col].isDigit()) {
                parts.add(getNumber(row-1, col))
            } else {
                if (col+1 < schematic[row-1].length && schematic[row-1][col+1].isDigit()) {
                    parts.add(getNumber(row-1, col+1))
                }
                if (0 <= col-1 && schematic[row-1][col-1].isDigit()) {
                    parts.add(getNumber(row-1, col-1))
                }
            }
        }

        return when (parts.size) {
            0, 1 -> null
            2 -> parts[0] * parts[1]
            else -> throw IllegalStateException("too many numbers: $parts")
        }
    }

    private fun getNumber(row : Int, col : Int) : Int {
        var left = col
        while (0 <= left && schematic[row][left].isDigit()) {
            --left
        }
        ++left
        var right = left
        while (right < schematic[row].length && schematic[row][right].isDigit()) {
            ++right
        }
        return schematic[row].substring(left, right).toInt()
    }
}