import java.io.File

fun main(args: Array<String>) {
    val inputData = readDataFrom("src/main/resources/had12.dat")
    println(inputData)
}

fun readDataFrom(pathname: String): InputData {
    val file = File(pathname)
    val content = file.readText().trim().split(Regex("\\n\\s*\\n"))
    val size = content[0].toInt()
    val flowMatrix = content[1]
    val distanceMatrix = content[2]
    return InputData(size, flowMatrix, distanceMatrix)
}

data class InputData(val size: Int, val flowMatrix: String, val distanceMatrix: String)