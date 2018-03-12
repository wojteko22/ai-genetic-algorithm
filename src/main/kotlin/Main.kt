import kotlin.system.measureTimeMillis

typealias Millis = Long

fun main(args: Array<String>) {
    val time: Millis = measureTimeMillis {
        val params = AlgorithmParams()
        FacilitiesData.all.forEach { prepareStats(params, it) }
    }
    println(time / 1000.0f)
}

fun prepareStats(params: AlgorithmParams, facilitiesData: FacilitiesData) {
    val numberOfFacilities = facilitiesData.size
    AvgFamilyStats(params, facilitiesData).writeToCsv(numberOfFacilities)
}
