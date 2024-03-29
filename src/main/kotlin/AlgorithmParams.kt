class AlgorithmParams(
    val populationSize: Int = 100,
    val numberOfGenerations: Int = 100,
    val tournamentSize: Int = 5,
    val crossingOdds: Float = 0.7f,
    val mutationOdds: Float = 0.01f
) {

    override fun toString(): String =
        "pop_size=$populationSize, Px=$crossingOdds, Pm=$mutationOdds" +
                if (tournamentSize != 0) ", Tour=$tournamentSize" else ""
}
