class AverageStats(private val bestCost: Mean, private val averageCost: Mean, private val worstCost: Mean) {

    val averageBest = bestCost.mean
    val averageAverage = averageCost.mean
    val averageWorst = worstCost.mean

    val bestSd = bestCost.sd
    val averageSd = averageCost.sd
    val worstSd = worstCost.sd

    override fun toString() =
        "${bestCost.mean},${averageCost.mean},${worstCost.mean},${bestCost.sd},${averageCost.sd},${worstCost.sd}"
}