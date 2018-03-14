class GreedyQapFinder(dataNumber: Int) {
    private val facilitiesData = FacilitiesData.readFrom(dataNumber)
    private val numberOfFacilitiesData = facilitiesData.numberOfFacilities
    private val allLocations = List(numberOfFacilitiesData) { it + 1 }
    private val firstLocation = 1

    fun print() {
        val result = find()
        println("result: $result")
        println("cost: ${calculateCost(result)}")
    }

    fun find(): List<Location> {
        var currentResult: List<Location> = listOf(firstLocation)
        while (currentResult.size != numberOfFacilitiesData) {
            currentResult = findBestNextLocation(currentResult)
        }
        return currentResult
    }

    private fun findBestNextLocation(currentResult: List<Location>): List<Location> {
        val availableLocations = allLocations - currentResult
        return availableLocations.map { currentResult + it }.minBy { calculateCost(it) } ?: error("No available Location")
    }

    private fun calculateCost(locations: List<Location>): Int {
        var cost = 0
        for (firstFacility in 0 until locations.size) {
            for (secondFacility in (firstFacility + 1) until locations.size) {
                cost += facilitiesData.flowMatrix[firstFacility][secondFacility] *
                        facilitiesData.distanceMatrix[locations[firstFacility] - 1][locations[secondFacility] - 1] +
                        facilitiesData.flowMatrix[secondFacility][firstFacility] *
                        facilitiesData.distanceMatrix[locations[secondFacility] - 1][locations[firstFacility] - 1]
            }
        }
        return cost
    }
}

fun main(args: Array<String>) {
    GreedyQapFinder(12).print()
}

