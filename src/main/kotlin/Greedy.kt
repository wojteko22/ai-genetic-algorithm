fun main(args: Array<String>) {
    GreedyQapFinder(20).print()
}

class GreedyQapFinder(dataNumber: Int) {
    private val facilitiesData = FacilitiesData.readFrom(dataNumber)
    private val numberOfFacilitiesData = facilitiesData.numberOfFacilities
    private val allLocations = List(numberOfFacilitiesData) { it + 1 }

    fun print() {
        val result = find()
        println("result: $result")
        println("cost: ${calculateCost(result)}")
    }

    private fun find(): List<Location> {
        var currentResult: List<Location> = findTwoFirstLocations()
        while (currentResult.size != numberOfFacilitiesData) {
            currentResult = findBestNextLocation(currentResult)
        }
        return currentResult
    }

    private fun findTwoFirstLocations(): List<Location> = allPossiblePairs(allLocations)
        .map { it.toList() }
        .minBy { calculateCost(it) } ?: error("Not enough locations")


    private fun allPossiblePairs(list: Iterable<Int>): List<Pair<Int, Int>> = list.flatMap { firstItem ->
        (list - firstItem).map { secondItem ->
            Pair(firstItem, secondItem)
        }
    }

    private fun findBestNextLocation(currentResult: List<Location>): List<Location> {
        val availableLocations = allLocations - currentResult
        return availableLocations
            .map { currentResult + it }
            .minBy { calculateCost(it) } ?: error("No available Location")
    }

    private fun calculateCost(locations: List<Location>): Int = allPossiblePairs(locations.indices)
        .sumBy { (firstFacility, secondFacility) ->
            facilitiesData.flowMatrix[firstFacility][secondFacility] *
                    facilitiesData.distanceMatrix[locations[firstFacility] - 1][locations[secondFacility] - 1]
        }
}
