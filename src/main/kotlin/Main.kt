import kotlin.system.measureTimeMillis

typealias Millis = Long

fun main(args: Array<String>) {
    val time: Millis = measureTimeMillis {
        val params = AlgorithmParams()
        val facilitiesData = FacilitiesData.readFrom(12)
        StatsPicker(params, facilitiesData).writeToCsv()
    }
    println(time / 1000.0f)
}

