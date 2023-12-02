package InputReader

import java.io.File

class StringFileInputReader(fileName: String) {
    val fileData : List<String> by lazy {
        val file = File(fileName)
        if (file.exists()) {
            File(fileName).bufferedReader().readLines()
        } else {
            println("$fileName not found")
            emptyList()
        }
    }

}