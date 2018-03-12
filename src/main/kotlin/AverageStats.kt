class AverageStats(private val bestCost: Mean, private val averageCost: Mean, private val worstCost: Mean) {

    override fun toString() =
        "${bestCost.mean},${averageCost.mean},${worstCost.mean},${bestCost.sd},${averageCost.sd},${worstCost.sd}"
}