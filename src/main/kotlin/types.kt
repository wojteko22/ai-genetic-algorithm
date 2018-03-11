import kotlin.math.roundToInt

typealias Matrix = List<List<Int>>
typealias FamilyStats = List<PopulationStats>

typealias PeersStats = Collection<PopulationStats>

fun PeersStats.average(): PopulationStats {
    val averageBest = map { it.bestCost }.average().roundToInt()
    val averageAverage =  map { it.averageCost }.average().roundToInt()
    val averageWorst = map { it.worstCost }.average().roundToInt()
    return PopulationStats(averageBest, averageAverage, averageWorst)
}
