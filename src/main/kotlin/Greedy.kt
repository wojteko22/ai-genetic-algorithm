import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val data = FacilitiesData.readFrom(20)
    print("${data.numberOfFacilities} fabryk: ")
    val time = measureTimeMillis {
        GreedyQapFinder(data).print()
    }
    println("czas: $time")
}

class GreedyQapFinder(private val facilitiesData: FacilitiesData) {

    private val numberOfFacilitiesData = facilitiesData.numberOfFacilities
    private val allLocations = List(numberOfFacilitiesData) { it + 1 }

    fun print() {
        val result = find()
        val cost = calculateCost(result)
        println("koszt $cost")
    }

    private fun find(): List<Location> {
        val firstResult: List<Location> = findTwoFirstLocations()
        return findBestLocation(firstResult)
    }

    private fun findTwoFirstLocations(): List<Location> = allPossiblePairs(allLocations)
        .map { it.toList() }
        .minBy { calculateCost(it) } ?: error("Not enough locations")

    private tailrec fun findBestLocation(currentResult: List<Location>): List<Location> =
        if (currentResult.size == numberOfFacilitiesData) {
            currentResult
        } else {
            val nextResult = findBestNextLocation(currentResult)
            findBestLocation(nextResult)
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

    private fun allPossiblePairs(elements: Iterable<Int>): List<Pair<Int, Int>> = elements.flatMap { firstItem ->
        (elements - firstItem).map { secondItem ->
            Pair(firstItem, secondItem)
        }
    }
}
