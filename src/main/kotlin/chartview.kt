import javafx.scene.chart.NumberAxis
import javafx.scene.layout.GridPane
import tornadofx.View
import tornadofx.linechart
import tornadofx.multiseries

class chartview : View("Algorytm genetyczny") {
    override val root = GridPane()

    init {
        val numberOfFacilities = 20
        val params = AlgorithmParams(tournamentSize = 100)

        val facilitiesData = FacilitiesData.readFrom(numberOfFacilities)
        val fullStats = StatsPicker(params, facilitiesData).fullStats
        val chartSize = ChartSize(facilitiesData.numberOfFacilities)

        linechart(
            "liczba fabryk: $numberOfFacilities\n$params\n${fullStats.averageSdsString}",
            NumberAxis(
                "nr pokolenia",
                0.0,
                params.numberOfGenerations.toDouble(),
                params.numberOfGenerations / 20.0),
            NumberAxis("koszt", chartSize.bottom, chartSize.top, 25.0)
        ) {
            createSymbols = false

            multiseries("najlepszy", "średni", "najgorszy") {
                fullStats.forEachIndexed { index, averageStats ->
                    with(averageStats) {
                        data(index, averageBest, averageAverage, averageWorst)
                    }
                }
            }
        }
    }
}
