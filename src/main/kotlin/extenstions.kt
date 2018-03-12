import kotlin.math.roundToInt

fun PeersStats.average(): AverageStats {
    val averageBest = map { it.bestCost }.mean
    val averageAverage = map { it.averageCost }.mean
    val averageWorst = map { it.worstCost }.mean
    return AverageStats(averageBest, averageAverage, averageWorst)
}

val List<Cost>.mean: Mean
    get() {
        val mean = average()
        val sum = fold(0.0, { accumulator, next -> accumulator + Math.pow(next - mean, 2.0) })
        val sd: Cost =  Math.sqrt(sum / size).roundToInt()
        return Mean(mean.roundToInt(), sd)
    }