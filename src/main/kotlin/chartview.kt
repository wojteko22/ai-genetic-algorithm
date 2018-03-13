import javafx.scene.chart.NumberAxis
import javafx.scene.layout.GridPane
import tornadofx.View
import tornadofx.linechart
import tornadofx.multiseries

class chartview : View("Algorytm genetyczny") {
    override val root = GridPane()

    init {
        val params = AlgorithmParams()
        val facilitiesData = FacilitiesData.readFrom(12)

        val fullStats = StatsPicker(params, facilitiesData).fullStats
        val chartSize = ChartSize(facilitiesData.numberOfFacilities)

        linechart(
            "$params\n${fullStats.averageSdsString}",
            NumberAxis("nr pokolenia", 0.0, 100.0, 5.0),
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
