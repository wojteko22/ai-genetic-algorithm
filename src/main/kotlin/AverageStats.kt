class AverageStats(private val bestCost: Mean, private val averageCost: Mean, private val worstCost: Mean) {

    val averageBest = bestCost.mean
    val averageAverage = averageCost.mean
    val averageWorst = worstCost.mean

    override fun toString() =
        "${bestCost.mean},${averageCost.mean},${worstCost.mean},${bestCost.sd},${averageCost.sd},${worstCost.sd}"
}