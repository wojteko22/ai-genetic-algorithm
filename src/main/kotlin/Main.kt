fun main(args: Array<String>) {
    val params = AlgorithmParams()
    FacilitiesData.all.forEach { prepareStats(params, it) }
}

fun prepareStats(params: AlgorithmParams, facilitiesData: FacilitiesData) {
    val numberOfFacilities = facilitiesData.size
    AvgFamilyStats(params, facilitiesData).writeToCsv(numberOfFacilities)
}
