package DayThree

class DayThree (private val schematic: List<String>)  {
    val schematicTotal : Int by lazy {
        var total = 0
        for ((i, row) in schematic.withIndex()) {
            //println("row: $row")
            var begin = 0
            while (begin < row.length) {
                val c = row[begin]
                if (c.isDigit()) {
                    var end = begin
                    while (end < row.length && row[end].isDigit()) {
                        ++end
                    }

                    if (isPartNumber(i, begin, end)) {
                        val part = row.substring(begin, end)
                        //println("part $part")
                        total += part.toInt()
                    }
                    begin = end
                } else {
                    ++begin
                }
            }
        }
        total
    }

    fun isPartNumber(row : Int, begin : Int, end : Int) : Boolean {
        val frontEdge = if (0 <= begin-1) begin-1 else begin
        val backEdge = if (end < schematic[row].length) end else end-1
        if(charIsSymbol(schematic[row][frontEdge])) {
            return true
        }

        if(charIsSymbol(schematic[row][backEdge])) {
            return true
        }

        if (0 <= row-1) {
            for (i in frontEdge..backEdge) {
                if(charIsSymbol(schematic[row-1][i])) {
                    return true
                }
            }
        }

        if (row+1 < schematic.size) {
            for (i in frontEdge..backEdge) {
                if(charIsSymbol(schematic[row+1][i])) {
                    return true
                }
            }
        }

        return false
    }

    fun charIsSymbol(c : Char) : Boolean {
        return when (c) {
            '/', '`', '~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '=', '+' -> true
            '.', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> false
            else -> {
                println("missing char: $c")
                false
            }
        }
    }
}